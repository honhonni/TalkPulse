package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.group.dao.UpdateInviteDao;
import cn.edu.ncu.talkpulse.group.service.UpdateInviteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("Updateinvite")
public class UpdateInviteServiceImpl implements UpdateInviteService {
    @Autowired
    private UpdateInviteDao updateInviteDao;
    @Override
    public Boolean updateinvite(Integer groupvalidation_id, Integer groupvalidation_senderid, HttpSession session, Integer groupvalidation_groupid, String groupvalidation_status, String groupvalidation_readstatus, LocalDateTime groupvalidation_time){
        Integer groupvalidation_receiverid=(Integer) session.getAttribute("user_id");
        int res=updateInviteDao.updateinvite(groupvalidation_id,groupvalidation_senderid,groupvalidation_receiverid,groupvalidation_groupid,groupvalidation_status,groupvalidation_readstatus,groupvalidation_time);
        if(res==1){
            return true;
        }else return false;
    }
}
