package cn.edu.ncu.talkpulse.controller;

import cn.edu.ncu.talkpulse.account.service.AccountService;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.friends.entity.Validation;
import cn.edu.ncu.talkpulse.friends.service.FriendService;
import cn.edu.ncu.talkpulse.friends.service.ValidationService;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/friends")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    HttpServletRequest request;

    private Integer getUserIdFromSession() {
        return (Integer) request.getSession().getAttribute("user_id");
    }


    @GetMapping("/search")
    public Result search(@RequestParam("user_id") Integer userId,
                         HttpServletRequest request) {
        HttpSession session = request.getSession();
        JSONObject data  = friendService.search(userId, session);

        if(data!=null) return Result.success(data);
        else return Result.fail();
    }

    // 添加好友接口
    @PostMapping("/addFriend")
    public Result addFriend(@RequestParam("friend_id") Integer friendId){
        Integer uid = getUserIdFromSession();
        if(uid==null) return Result.fail("非法请求，请先登录");
        return validationService.sendValidation(uid, friendId);
    }

    // 接收好友申请接口
    @GetMapping("/getValidation")
    public Result getValidation(){
        Integer uid = getUserIdFromSession();
        if(uid==null) return Result.fail("非法请求，请先登录");
        List<Validation> validations = validationService.getValidation(uid);
        if(validations==null) return Result.fail();
        else return Result.success(validations);
    }

    @GetMapping("/getMyValidation")
    public Result getMyValidation(){
        Integer uid = getUserIdFromSession();
        if(uid==null) return Result.fail("非法请求，请先登录");
        List<Validation> validations = validationService.getMyValidation(uid);
        if(validations==null) return Result.fail();
        else return Result.success(validations);
    }

    // 处理好友申请接口
    @PostMapping("/handleValidation")
    public Result handleValidation(@RequestParam("validation_id") Integer validationId,
                                   @RequestParam("agree") Boolean agree){
        Integer uid = getUserIdFromSession();
        if(uid==null) return Result.fail("非法请求，请先登录");
        return validationService.handleValidation(uid,validationId, agree);
    }

    // 移动好友分组
    @PostMapping("/removeFriend")
    public Result removeFriend(@RequestParam("friend_id") Integer friendId,
                               @RequestParam("friendship_id") Integer friendshipId){
        Integer uid = getUserIdFromSession();
        if(uid==null) return Result.fail("非法请求，请先登录");
        return validationService.removeFriend(uid, friendId, friendshipId);
    }
}
