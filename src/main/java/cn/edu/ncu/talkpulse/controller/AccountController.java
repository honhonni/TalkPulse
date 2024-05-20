package cn.edu.ncu.talkpulse.controller;

import cn.edu.ncu.talkpulse.account.dao.AccountDao;
import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.account.service.UpdateAvatarService;
import cn.edu.ncu.talkpulse.account.service.UpdateInfoService;
import cn.edu.ncu.talkpulse.account.service.UpdatePwdService;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.account.service.AccountService;
import com.alibaba.fastjson2.JSONObject;
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
    private UpdateAvatarService updateAvatarService;
    @Autowired
    private UpdateInfoService updateInfoService;
    @Autowired
    private UpdatePwdService updatePwdService;
    @Autowired
    private AccountDao accountDao;

    @PostMapping("/register")
    public Result register(@RequestParam("user_id") Integer userId,
                           @RequestParam("user_name") String userName,
                           @RequestParam("user_pwd") String userPwd){
        return accountService.register(userId, userName, userPwd);
    }

    @PostMapping( "/login")
    public Result login(@RequestParam("user_id") Integer userId,
                        @RequestParam("user_pwd") String userPwd,
                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        return accountService.login(userId, userPwd, session);
    }


    @GetMapping( "/get")
    public Result get(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserInfo userInfo = accountService.get(session);
        if(userInfo!=null) return Result.success(userInfo);
        else return Result.fail();
    }

    @PostMapping( "/updateAvatar")
    public Result updateAvatar(@RequestParam("uphoto") String uphoto,
                               HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) request.getSession().getAttribute("user_id");
        if(userId==null) return Result.fail("非法请求，请先登录");
        Boolean flag = updateAvatarService.updatephoto(userId,  uphoto);
        if(flag) return Result.success();
        else return Result.fail();
    }
    @PostMapping( "/updatetonew")
    public Result updatetonew(@RequestParam("uname") String uname,
                              @RequestParam("ugender") String ugender,
                              @RequestParam("uage") Integer uage,
                              @RequestParam("uintroduce") String uintroduce,
                              HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) request.getSession().getAttribute("user_id");
        if(userId==null) return Result.fail("非法请求，请先登录");
        Boolean flag = updateInfoService.updatetonew(userId, uname, ugender, uage, uintroduce);
        if(flag) return Result.success();
        else return Result.fail();
    }

    @PostMapping( "/updatepwd")
    public Result updatepwd(@RequestParam("old_pwd") String old_pwd,
                            @RequestParam("new_pwd") String new_pwd,
                            HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) request.getSession().getAttribute("user_id");
        if(userId==null) return Result.fail("非法请求，请先登录");
        Boolean flag = updatePwdService.newpwd(userId, old_pwd, new_pwd );
        if(flag) return Result.success();
        else return Result.fail();
    }

}
