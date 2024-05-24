package cn.edu.ncu.talkpulse.group.service;

import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.dto.ValidationReceiverDTO;
import cn.edu.ncu.talkpulse.dto.ValidationSenderDTO;
import cn.edu.ncu.talkpulse.group.entity.groupapply;
import cn.edu.ncu.talkpulse.group.entity.groupvalidation;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.List;

public interface InviteService {
//发送添加群聊申请
    Result sendGroupapply(Integer gid,Integer groupId);
    //获取用户收到的好友申请
    List<ValidationSenderDTO> getGroupapply(Integer gid);
    //获取群聊申请
    List<ValidationReceiverDTO>getMyGroupapply(Integer gid);
    //处理群聊申请请求
    Result handleGroupapply(Integer uid,Integer groupapplyId,Boolean agree);
}
