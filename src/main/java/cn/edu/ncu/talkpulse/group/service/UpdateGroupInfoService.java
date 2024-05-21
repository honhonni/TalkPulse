package cn.edu.ncu.talkpulse.group.service;

import jakarta.servlet.http.HttpSession;

public interface UpdateGroupInfoService {
    Boolean updateGroupIntroduce(String group_introduce,Integer group_id,HttpSession session);
}
