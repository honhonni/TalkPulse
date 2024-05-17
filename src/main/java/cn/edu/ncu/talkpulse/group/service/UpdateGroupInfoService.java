package cn.edu.ncu.talkpulse.group.service;

import jakarta.servlet.http.HttpSession;

public interface UpdateGroupInfoService {
    Boolean deleteIntroduce(Integer group_id, String group_introduce, HttpSession session);
    Boolean addIntroduce(Integer group_id,String group_introduce,HttpSession session);
}
