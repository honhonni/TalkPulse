package cn.edu.ncu.talkpulse.account.service;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Insert;

public interface AccountService {
    Boolean register(Integer userId, String userName, String userPwd);

    Boolean login(Integer userId,String userPwd, HttpSession session);

    UserInfo get(HttpSession session);

}
