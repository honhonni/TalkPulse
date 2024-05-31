package cn.edu.ncu.talkpulse.group.service;

import jakarta.servlet.http.HttpSession;

public interface ExitService {
    Boolean exitGroup(Integer group_id, HttpSession session);

    Boolean kickmember(Integer correuser_id,Integer corregroup_id,HttpSession session);
}
