package cn.edu.ncu.talkpulse.friends.service.impl;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.dto.ValidationReceiverDTO;
import cn.edu.ncu.talkpulse.dto.ValidationSenderDTO;
import cn.edu.ncu.talkpulse.dto.WebSocketDTO;
import cn.edu.ncu.talkpulse.friends.dao.RecordDao;
import cn.edu.ncu.talkpulse.friends.dao.ValidationDao;
import cn.edu.ncu.talkpulse.friends.dao.FriendshipDao;
import cn.edu.ncu.talkpulse.account.dao.AccountDao;
import cn.edu.ncu.talkpulse.friends.entity.Validation;
import cn.edu.ncu.talkpulse.friends.entity.Friend;
import cn.edu.ncu.talkpulse.friends.service.RecordService;
import cn.edu.ncu.talkpulse.friends.service.ValidationService;
import cn.edu.ncu.talkpulse.friends.service.WebSocketServer;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private WebSocketServer webSocketServer;

    @Autowired
    private ValidationDao validationDao;

    @Autowired
    private FriendshipDao friendshipDao;

    @Autowired
    private RecordService recordService;

    @Resource
    private AccountDao accountDao;

    // 发送添加好友申请
    @Override
    public Result sendValidation(Integer uid, Integer friendId) {
        Friend friend = friendshipDao.getFriendByUserId(uid, friendId);
        if(friend!=null) return Result.fail("非法请求，已添加该好友！");
        if(accountDao.countUser(friendId)==0) return Result.fail("非法请求，添加的用户不存在！");
        System.out.println(uid);
        System.out.println(friendId);
        Validation oldValidation = validationDao.getValidationByUserId(uid, friendId);
        int res;
        if(oldValidation!=null){
            oldValidation.setValidation_status(0);// 重置状态
            oldValidation.setValidation_readstatus(0);
            oldValidation.setValidation_time(LocalDateTime.now());
            res = validationDao.updateValidation(oldValidation);// 更新时间
        }else{
            Validation validation = new Validation();
            validation.setValidation_senderid(uid);
            validation.setValidation_receiverid(friendId);
            validation.setValidation_status(0);
            validation.setValidation_readstatus(0);
            validation.setValidation_time(LocalDateTime.now());
            res = validationDao.addValidation(validation);
        }

        if(res==0) return Result.fail();
        else{
            // webSocketServer.sendToUser(friendId,"您有新的好友请求");
            webSocketServer.sendToUser(friendId, WebSocketDTO.FRIEND_REQUEST);
            return Result.success();
        }
    }

    // 获取当前用户接受到的好友申请
    @Override
    public List<ValidationSenderDTO> getValidation(Integer uid) {
        List<Validation> validations = validationDao.getValidationsByReceiverId(uid);
        List<ValidationSenderDTO> result = new ArrayList<>(validations.size());
        validations.forEach(validation -> {
            // 封装好友申请发送者的头像和昵称
            UserInfo sender = accountDao.findUserById(validation.getValidation_senderid());
            ValidationSenderDTO validationSenderDTO = new ValidationSenderDTO(validation, sender.getUser_photo(), sender.getUser_name());
            result.add(validationSenderDTO);
        });
        validationDao.markValidationsAsRead(uid);// 设置已读

        return result;
    }

    // 获取用户发送的好友请求
    @Override
    public List<ValidationReceiverDTO> getMyValidation(Integer uid) {
        List<Validation> validations = validationDao.getValidationsBySenderId(uid);
        List<ValidationReceiverDTO> result = new ArrayList<>(validations.size());
        validations.forEach(validation -> {
            // 封装好友申请接收者的头像和昵称
            UserInfo receiver = accountDao.findUserById(validation.getValidation_receiverid());
            ValidationReceiverDTO validationReceiverDTO = new ValidationReceiverDTO(validation, receiver.getUser_photo(), receiver.getUser_name());
            result.add(validationReceiverDTO);
        });

        return result;
    }

    // 处理好友申请请求
    @Override
    @Transactional
    public Result handleValidation(Integer uid, Integer validationId, Boolean agree) {
        Validation validation = validationDao.getValidationById(validationId);
        if(validation==null) return Result.fail("好友申请不存在");

        if(! uid.equals(validation.getValidation_receiverid())) return Result.fail("非法请求，登录用户不匹配");
        if(validation.getValidation_status() !=0 ) return Result.fail("已经处理过该好友请求");

        validation.setValidation_status(agree?1:-1);// 同意1，拒绝-1
        validationDao.updateValidation(validation);
        if(agree){
            // 同意添加好友申请后：
            Integer senderid = validation.getValidation_senderid();
            Integer receiverid = validation.getValidation_receiverid();
            // 如果是添加自己为好友，只需要添加一次
            if(senderid==receiverid){
                Integer friendShipId = friendshipDao.getFriendShipIdByName(senderid, "我的好友");
                Friend friend = new Friend(senderid,receiverid,friendShipId);
                int res = friendshipDao.addFriend(friend);
                if(res==1) return Result.success();
                else return Result.fail();
            }
            // 否则需要双向添加：
            // 1、先查询用户的默认分组（我的好友）的分组id
            Integer senderFriendShipId = friendshipDao.getFriendShipIdByName(senderid, "我的好友");
            Integer receiverFriendShipId = friendshipDao.getFriendShipIdByName(receiverid, "我的好友");
            // 2、将被申请者添加进入申请者的默认分组
            Friend friend1 = new Friend(senderid,receiverid,senderFriendShipId);
            int res1 = friendshipDao.addFriend(friend1);
            // 3、被申请者也要将申请者添加进入默认分组
            Friend friend2 = new Friend(receiverid,senderid,receiverFriendShipId);
            int res2 = friendshipDao.addFriend(friend2);
            if(res1==1 && res2==1) {
                // 添加成功后，双方互发"你好"
                recordService.sendMessage(senderid,receiverid,"请求添加好友",0);
                recordService.sendMessage(receiverid,senderid,"我已同意你的好友申请",0);
                
                return Result.success();
            }
            else return Result.fail();
        }
        return Result.success();
    }

    // 移动分组
    @Override
    public Result removeFriend(Integer uid, Integer friendId, Integer friendshipId) {
        if(friendshipDao.getFriendShipById(uid, friendshipId)==null) return Result.fail("非法请求，分组不存在");
        if(accountDao.countUser(friendId)==0) return Result.fail("非法请求，移动的分组的好友不存在！");

        Friend friend = new Friend(uid, friendId, friendshipId);
        if(friendshipDao.updateFriendShip(friend)==1){
            return Result.success();
        }else{
            return Result.fail();
        }
    }


}
