package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.group.dao.InviteDao;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.entity.groupapply;
import cn.edu.ncu.talkpulse.group.entity.groupvalidation;
import cn.edu.ncu.talkpulse.group.service.InviteService;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service("invite")
public class InviteServiceImpl implements InviteService {
    @Autowired
    private InviteDao inviteDao;
    @Override
    public Boolean invite( Integer groupvalidation_receiverid, Integer groupvalidation_groupid, HttpSession session){
        Integer groupvalidation_senderid=(Integer) session.getAttribute("user_id");
        int res= inviteDao.invite(groupvalidation_senderid,groupvalidation_receiverid,groupvalidation_groupid,LocalDateTime.now());
        if(res==1){
            return true ;
        }else{
            return false;
        }
    }

    @Override
    public JSONObject getgroupapply( Integer groupapply_senderid,HttpSession session, Integer groupapply_hostid, Integer groupapply_groupid) {
        Groupinfo groupinfo = inviteDao.getgourp(groupapply_groupid);
        JSONObject data = new JSONObject();
        if (groupinfo != null) {
            data.put("group", groupinfo);
            if (Objects.equals(groupapply_hostid, (Integer) session.getAttribute("user_id"))) {
                groupapply groupapply1 = inviteDao.getgroupapply(groupapply_senderid, (Integer) session.getAttribute("user_id"), groupapply_groupid);
                String getuser = inviteDao.getuser(groupapply_senderid);
                if (groupapply1 != null) {
                    data.put("groupapply", groupapply1);
                    data.put("getuser", getuser);
                }
            } else {
                groupapply groupapply1 = inviteDao.getgroupapply((Integer) session.getAttribute("user_id"), groupapply_hostid, groupapply_groupid);
                if (groupapply1 != null) {
                    data.put("groupapply", groupapply1);
                }
            }
                return data;
            }else return null;
        }
    }
