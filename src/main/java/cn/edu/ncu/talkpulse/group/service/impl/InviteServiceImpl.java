package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.account.dao.AccountDao;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.group.dao.CorreDao;
import cn.edu.ncu.talkpulse.group.dao.CreateDao;
import cn.edu.ncu.talkpulse.group.dao.InviteDao;
import cn.edu.ncu.talkpulse.group.entity.Groupapply;
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
    public Boolean sendGroupapply(HttpSession session ,LocalDateTime groupapply_time,Integer groupapply_groupid,Integer groupapply_hostid,String groupapply_introduce) {
        Integer groupapply_senderid=(Integer) session.getAttribute("user_id");
        int res=inviteDao.addgroupapply(groupapply_senderid,groupapply_time,groupapply_groupid,groupapply_hostid,groupapply_introduce);
        if(res==1){
            return true;
        }
        else return false;
    }
    //获取当前用户接受到的群聊申请
    @Override
    public List<Groupapply> getMyGroupapply(HttpSession session){
        Integer groupapply_hostid = (Integer) session.getAttribute("userid");
        List<Groupapply> groupapplies=inviteDao.getGroupapplyByReceiverId(groupapply_hostid);
        List<Groupapply> result=new ArrayList<>(groupapplies.size());
        inviteDao.markGroupapplyAsRead(groupapply_hostid);
        return result;
    }
    //获取用户发送的好友请求
    @Override
    public JSONObject getGroupapply(HttpSession session) {
        Integer groupapply_senderid = (Integer) session.getAttribute("user_id");
        List<Groupapply> groupapplies = inviteDao.getGroupapplyBySenderId(groupapply_senderid);
        JSONObject data = new JSONObject();

        if (groupapplies != null && !groupapplies.isEmpty()) {
            Groupapply firstGroupapply = groupapplies.get(0);
            data.put("groupapply_senderid", firstGroupapply.getGroupapply_senderid());
            data.put("groupapply_time", firstGroupapply.getGroupapply_time());
           data.put("groupapply_groupid",firstGroupapply.getGroupapply_groupid());
           data.put("groupapply_hostid",firstGroupapply.getGroupapply_hostid());
           data.put("groupapply_status",firstGroupapply.isGroupapply_status());
           data.put("groupapply_readstatus",firstGroupapply.isGroupapply_readstatus());
           return data;
       }
        data.put("error", "没有找到对应的groupapply记录");
        return data;
    }

    //处理好友申请请求
    @Override
    @Transactional
    public Result handleGroupapply(Integer gid, Integer senderid, HttpSession session, Boolean status){
        Integer hostid=(Integer) session.getAttribute("user_id");
        Groupapply groupapply1=inviteDao.getgroupapplyById(hostid);
        if(groupapply1==null) return Result.fail("群聊申请不存在");
        if(!gid.equals(groupapply1.getGroupapply_groupid())) return Result.fail("非法请求");
        if(groupapply1.isGroupapply_status()!=false)return Result.fail("已经处理过请求");
        groupapply1.isGroupapply_status();
        inviteDao.updategroupapply(groupapply1);
        if(status){
            senderid=groupapply1.getGroupapply_senderid();
            gid=groupapply1.getGroupapply_groupid();
            int res1=correDao.addcorre(senderid,gid);
            return Result.success();
        }
        return Result.success();
    }
}







