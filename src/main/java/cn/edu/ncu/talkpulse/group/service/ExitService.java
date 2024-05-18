package cn.edu.ncu.talkpulse.group.service;

import jakarta.servlet.http.HttpSession;

public interface ExitService {
    Boolean exitGroup(Integer corregroup_id, HttpSession session);
    Boolean deleteGroup(Integer group_id,String group_name,HttpSession session);
}
