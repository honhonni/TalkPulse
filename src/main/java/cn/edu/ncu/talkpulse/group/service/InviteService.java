package cn.edu.ncu.talkpulse.group.service;

import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.dto.ValidationReceiverDTO;
import cn.edu.ncu.talkpulse.group.entity.GroupApplyWithGroupInfo;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

public interface InviteService {
//发送添加群聊申请
    Boolean sendGroupapply(HttpSession session, LocalDateTime groupapply_time, Integer groupapply_groupid, Integer groupapply_hostid, String groupapply_introduce);
    //获取用户发送的群聊申请
    List<GroupApplyWithGroupInfo> getGroupAppliesBySenderId(Integer senderid);
    //群主获取群聊申请
    List<GroupApplyWithGroupInfo> getMyGroupapply(Integer groupapply_hostid);
    //处理群聊申请请求
    Result handleGroupapply(Byte status, HttpSession session);
}
