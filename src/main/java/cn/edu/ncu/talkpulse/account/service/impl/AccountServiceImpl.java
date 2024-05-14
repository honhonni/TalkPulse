package cn.edu.ncu.talkpulse.account.service.impl;

import cn.edu.ncu.talkpulse.account.dao.AccountDao;
import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.account.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service("AccountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    // 用户注册
    @Override
    public Boolean register(Integer userId, String userName, String userPwd) {
        UserInfo user = accountDao.findUserById(userId);
        if(user!=null){
            return false;
        }else{
            String md5Pwd = DigestUtils.md5DigestAsHex((userPwd).getBytes());
            UserInfo userInfo = new UserInfo(userId, userName, md5Pwd);
            int res = accountDao.addUser(userInfo);
            if(res==1) return true;
            else return false;
        }
    }

    // 用户登陆
    @Override
    public Boolean login(Integer userId, String userPwd, HttpSession session) {
        String md5Pwd = DigestUtils.md5DigestAsHex((userPwd).getBytes());
        UserInfo userInfo = accountDao.checkUser(userId, md5Pwd);
        if(userInfo!=null){
            session.setAttribute("user_id", userId);
            return true;
        }
        else return false;
    }
}
