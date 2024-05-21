package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.dto.WebSocketDTO;
import cn.edu.ncu.talkpulse.group.dao.UserApplyIntoDao;
import cn.edu.ncu.talkpulse.group.service.UserApplyIntoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("userapplyinto")
public class UserApplyIntoServiceImpl implements UserApplyIntoService {
    @Autowired
    private UserApplyIntoDao userApplyIntoDao;
    @Override
    public Boolean UserApplyInto(HttpSession session, LocalDateTime groupapply_time, Integer groupapply_groupid,String groupapply_introduce){
        Integer groupapply_senderid=(Integer) session.getAttribute("user_id");
        int res= userApplyIntoDao.UserApplyinto(groupapply_senderid,groupapply_time,groupapply_groupid,groupapply_introduce);
        if(res==1){
            return true;
        }else return false;
    }
}
