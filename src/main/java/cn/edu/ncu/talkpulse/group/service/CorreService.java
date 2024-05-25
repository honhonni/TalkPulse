package cn.edu.ncu.talkpulse.group.service;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface CorreService {
    List<UserInfo> getgroup(HttpSession session);
}
