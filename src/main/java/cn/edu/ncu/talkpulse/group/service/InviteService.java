package cn.edu.ncu.talkpulse.group.service;

import cn.edu.ncu.talkpulse.group.entity.groupvalidation;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;

public interface InviteService {
    Boolean invite(Integer groupvalidation_receiverid, Integer groupvalidation_groupid, HttpSession session);

    JSONObject getgroupapply(Integer groupapply_senderid,HttpSession session,Integer groupapply_hostid,Integer groupapply_groupid);
}
