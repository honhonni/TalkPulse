package cn.edu.ncu.talkpulse.account.service.impl;

import cn.edu.ncu.talkpulse.account.dao.AccountDao;
import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.account.service.AccountService;
import cn.edu.ncu.talkpulse.dto.Result;
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
    public Result register(Integer userId, String userName, String userPwd) {
        UserInfo user = accountDao.findUserById(userId);
        System.out.println(user);
        if(user!=null){
            return Result.fail("用户id已存在");
        }else{
            String md5Pwd = DigestUtils.md5DigestAsHex((userPwd).getBytes());
            UserInfo userInfo = new UserInfo(userId, userName, md5Pwd);
            int res = accountDao.addUser(userInfo);
            if(res==1) return Result.success();
            else return Result.fail();
        }
    }

    // 用户登陆
    @Override
    public Result login(Integer userId, String userPwd, HttpSession session) {
        String md5Pwd = DigestUtils.md5DigestAsHex((userPwd).getBytes());
        UserInfo userInfo = accountDao.checkUser(userId, md5Pwd);
        if(userInfo!=null){
            session.setAttribute("user_id", userId);
            return Result.success();
        }
        else return Result.fail("id或密码错误");
    }

    // 获取用户信息
    @Override
    public UserInfo get(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("user_id");

        UserInfo userInfo = accountDao.searchUserById(userId);
        System.out.println(userInfo);
        if (userInfo != null) {
           return userInfo;
        } else {
            return null;
        }
    }


}
