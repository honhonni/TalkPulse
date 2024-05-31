package cn.edu.ncu.talkpulse.group.service;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;

public interface UpdateGroupInfoService {
    Boolean updateGroupIntroduce(String group_introduce,Integer group_id,HttpSession session);//修改群聊简介
    JSONObject getGroupInfo(Integer group_id, HttpSession session );
}
