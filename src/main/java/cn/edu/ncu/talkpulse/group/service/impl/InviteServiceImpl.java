package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.account.dao.AccountDao;
import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.dto.ValidationReceiverDTO;
import cn.edu.ncu.talkpulse.dto.ValidationSenderDTO;
import cn.edu.ncu.talkpulse.group.dao.CorreDao;
import cn.edu.ncu.talkpulse.group.dao.CreateDao;
import cn.edu.ncu.talkpulse.group.dao.InviteDao;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.entity.corre;
import cn.edu.ncu.talkpulse.group.entity.groupapply;
import cn.edu.ncu.talkpulse.group.entity.groupvalidation;
import cn.edu.ncu.talkpulse.group.service.InviteService;
import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static sun.security.krb5.internal.rcache.DflCache.uid;

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
        corre corre1 = correDao.ingroup(gid, groupId);
        if (corre1 != null) return Result.fail("非法请求，已在群聊里面！");
        groupapply oldgroupapply = inviteDao.getgroupapplyByUserId(gid, groupId);
        int res;
        if (oldgroupapply != null) {
            oldgroupapply.setGroupapply_status(false);//重置状态
            oldgroupapply.setGroupapply_readstatus(false);
            oldgroupapply.setGroupapply_time(LocalDateTime.now());
            res = inviteDao.updategroupapply(oldgroupapply);
        } else {
            groupapply groupapply1 = new groupapply();
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
        List<groupapply> groupapplies=inviteDao.getGroupapplyByReceiverId(gid);
        List<ValidationSenderDTO> result=new ArrayList<>(groupapplies.size());
        inviteDao.markGroupapplyAsRead(gid);
        return result;
    }
    //获取用户发送的好友请求
    @Override
    public List<ValidationReceiverDTO>getMyGroupapply(Integer gid){
        List<groupapply> groupapplies=inviteDao.getGroupapplyBySenderId(gid);
        List<ValidationReceiverDTO> result=new ArrayList<>(groupapplies.size());
        return result;
    }
    //处理好友申请请求
    @Override
    @Transactional
    public Result handleGroupapply(Integer gid,Integer groupapplyId,Boolean agree){
        groupapply groupapply1=inviteDao.getgroupapplyById(groupapplyId);
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







