package cn.edu.ncu.talkpulse.controller;

import cn.edu.ncu.talkpulse.account.dao.AccountDao;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.account.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountDao accountDao;

    @PostMapping("/register")
    public Result register(@RequestParam("user_id") Integer userId,
                           @RequestParam("user_name") String userName,
                           @RequestParam("user_pwd") String userPwd){
        Boolean ok = accountService.register(userId, userName, userPwd);
        if(ok) return Result.success();
        else return Result.fail();
    }

    @PostMapping( "/login")
    public Result login(@RequestParam("user_id") Integer userId,
                        @RequestParam("user_pwd") String userPwd,
                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        Boolean ok = accountService.login(userId, userPwd, session);
        if(ok) return Result.success();
        else return Result.fail();
    }

}
