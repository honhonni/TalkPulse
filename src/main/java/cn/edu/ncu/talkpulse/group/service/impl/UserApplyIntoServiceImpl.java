package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.dto.WebSocketDTO;
import cn.edu.ncu.talkpulse.friends.service.WebSocketServer;
import cn.edu.ncu.talkpulse.group.dao.GroupDao;
import cn.edu.ncu.talkpulse.group.dao.UserApplyIntoDao;
import cn.edu.ncu.talkpulse.group.service.UserApplyIntoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("userapplyinto")
public class UserApplyIntoServiceImpl implements UserApplyIntoService {
    @Autowired
    private WebSocketServer webSocketServer;
    @Autowired
    private UserApplyIntoDao userApplyIntoDao;
    @Autowired
    private GroupDao groupDao;
    @Override
    public Boolean UserApplyInto(HttpSession session, Integer groupapply_groupid){
        Integer groupapply_senderid=(Integer) session.getAttribute("user_id");
//        群主id
        Integer hostid = groupDao.getGruophostid(groupapply_groupid);
        int res= userApplyIntoDao.UserApplyinto(
                groupapply_senderid,
                LocalDateTime.now(),
                groupapply_groupid,
                hostid);
        if(res==0) return false;
        else{
            // webSocketServer.sendToUser(friendId,"您有新的好友请求");
            webSocketServer.sendToUser(hostid, WebSocketDTO.GROUP_REQUEST);
            return true;
        }
    }
}
