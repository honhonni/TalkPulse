package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.account.dao.AccountDao;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.dto.ValidationReceiverDTO;
import cn.edu.ncu.talkpulse.dto.ValidationSenderDTO;
import cn.edu.ncu.talkpulse.group.dao.CorreDao;
import cn.edu.ncu.talkpulse.group.dao.CreateDao;
import cn.edu.ncu.talkpulse.group.dao.InviteDao;
import cn.edu.ncu.talkpulse.group.entity.Corre;
import cn.edu.ncu.talkpulse.group.entity.Groupapply;
import cn.edu.ncu.talkpulse.group.service.InviteService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("invite")
public class InviteServiceImpl implements InviteService {
    @Autowired
    private InviteDao inviteDao;
    @Autowired
    private CorreDao correDao;
    @Autowired
    private CreateDao createDao;
    @Resource
    private AccountDao accountDao;

    //发送添加群聊申请

    @Override
    public Result sendGroupapply(Integer gid, Integer groupId) {
        Corre corre1 = correDao.ingroup(gid, groupId);
        if (corre1 != null) return Result.fail("非法请求，已在群聊里面！");
        Groupapply oldgroupapply = inviteDao.getgroupapplyByUserId(gid, groupId);
        int res;
        if (oldgroupapply != null) {
            oldgroupapply.setGroupapply_status(false);//重置状态
            oldgroupapply.setGroupapply_readstatus(false);
            oldgroupapply.setGroupapply_time(LocalDateTime.now());
            res = inviteDao.updategroupapply(oldgroupapply);
        } else {
            Groupapply groupapply1 = new Groupapply();
            groupapply1.setGroupapply_senderid(gid);
            groupapply1.setGroupapply_groupid(groupId);
            groupapply1.setGroupapply_status(false);
            groupapply1.setGroupapply_readstatus(false);
            groupapply1.setGroupapply_time(LocalDateTime.now());
            res = inviteDao.addgroupapply(groupapply1);
        }
        return null;
    }
    //获取当前用户接受到的好友申请
    @Override
    public List<ValidationSenderDTO> getGroupapply(Integer gid){
        List<Groupapply> groupapplies=inviteDao.getGroupapplyByReceiverId(gid);
        List<ValidationSenderDTO> result=new ArrayList<>(groupapplies.size());
        inviteDao.markGroupapplyAsRead(gid);
        return result;
    }
    //获取用户发送的好友请求
    @Override
    public List<ValidationReceiverDTO>getMyGroupapply(Integer gid){
        List<Groupapply> groupapplies=inviteDao.getGroupapplyBySenderId(gid);
        List<ValidationReceiverDTO> result=new ArrayList<>(groupapplies.size());
        return result;
    }
    //处理好友申请请求
    @Override
    @Transactional
    public Result handleGroupapply(Integer gid,Integer groupapplyId,Boolean agree){
        Groupapply groupapply1=inviteDao.getgroupapplyById(groupapplyId);
        if(groupapply1==null) return Result.fail("群聊申请不存在");
        if(!gid.equals(groupapply1.getGroupapply_groupid())) return Result.fail("非法请求");
        if(groupapply1.isGroupapply_status()!=false)return Result.fail("已经处理过请求");
        groupapply1.isGroupapply_status();
        inviteDao.updategroupapply(groupapply1);
        if(agree){
            Integer senderid=groupapply1.getGroupapply_senderid();
            Integer receiverid=groupapply1.getGroupapply_groupid();
            int res1=correDao.addcorre(senderid,receiverid);
            return Result.success();
        }
        return Result.success();
    }
}







