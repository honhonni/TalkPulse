package cn.edu.ncu.talkpulse.group.service;

import jakarta.servlet.http.HttpSession;

public interface HostapplyService {

    Boolean hostset(Boolean groupapply_status,Boolean groupapply_readstatus,Integer groupapply_groupid,HttpSession session);
}
