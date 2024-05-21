package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.group.dao.InviteDao;
import cn.edu.ncu.talkpulse.group.service.InviteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("invite")
public class InviteServiceImpl implements InviteService {
    @Autowired
    private InviteDao inviteDao;
    @Override
    public Boolean invite( Integer groupvalidation_receiverid, Integer groupvalidation_groupid, LocalDateTime groupvalidation_time, HttpSession session){
        Integer groupvalidation_senderid=(Integer) session.getAttribute("user_id");
        int res= inviteDao.invite(groupvalidation_senderid,groupvalidation_receiverid,groupvalidation_groupid,groupvalidation_time);
        if(res==1){
            return true ;
        }else{
            return false;
        }
    }
}
