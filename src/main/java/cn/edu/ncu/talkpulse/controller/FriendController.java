package cn.edu.ncu.talkpulse.controller;

import cn.edu.ncu.talkpulse.account.service.AccountService;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.friends.service.FriendService;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;


    @GetMapping("/search")
    public Result search(@RequestParam("user_id") Integer userId,
                         HttpServletRequest request) {
        HttpSession session = request.getSession();
        JSONObject data  = friendService.search(userId, session);

        if(data!=null) return Result.success(data);
        else return Result.fail();
    }
}
