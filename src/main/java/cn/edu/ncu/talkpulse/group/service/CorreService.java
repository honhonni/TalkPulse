package cn.edu.ncu.talkpulse.group.service;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.dto.Result;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface CorreService {
    Result getmember(Integer group_id);
}
