package cn.edu.ncu.talkpulse.account.service;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.dto.Result;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Insert;

public interface AccountService {
    Result register(Integer userId, String userName, String userPwd);

    Result login(Integer userId,String userPwd, HttpSession session);

    UserInfo get(HttpSession session);

}
