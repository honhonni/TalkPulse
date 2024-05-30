package cn.edu.ncu.talkpulse.group.service;

import jakarta.servlet.http.HttpSession;

public interface ExitService {
    Boolean exitGroup(Integer group_id, HttpSession session);


}
