package cn.edu.ncu.talkpulse.controller;

import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.dto.ValidationReceiverDTO;
import cn.edu.ncu.talkpulse.dto.ValidationSenderDTO;
import cn.edu.ncu.talkpulse.friends.service.FriendService;
import cn.edu.ncu.talkpulse.friends.service.RecordService;
import cn.edu.ncu.talkpulse.friends.service.ValidationService;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    //根据用户号查询信息接口
    @GetMapping("/search")
    public Result search(@RequestParam("user_id") Integer userId,
                         HttpServletRequest request) {
        HttpSession session = request.getSession();
        JSONObject data  = friendService.search(userId, session);

        if(data!=null) return Result.success(data);
        else return Result.fail();
    }

//    //根据关键词查找聊天记录接口
//    @GetMapping("/searchRecords")
//    public Result searchRecords(@RequestParam String keyword, HttpServletRequest request) {
//        HttpSession session = request.getSession();
////        Integer userId = (Integer) session.getAttribute("userId");
//        Result data = friendService.searchRecordsByKeyword(keyword, session);
//
//        if(data!=null) return Result.success(data);
//        else return Result.fail();
//    }

    // 添加好友接口
    @PostMapping("/addFriend")
    public Result addFriend(@RequestParam("friend_id") Integer friendId){
        Integer uid = getUserIdFromSession();
        if(uid==null) return Result.fail("非法请求，请先登录");
        return validationService.sendValidation(uid, friendId);
    }

    // 接收好友申请接口（获取用户发送和接受到的好友申请）
    @GetMapping("/getValidation")
    public Result getValidation(){
        Integer uid = getUserIdFromSession();
        if(uid==null) return Result.fail("非法请求，请先登录");
        List<ValidationSenderDTO> validationList = validationService.getValidation(uid);
        List<ValidationReceiverDTO> applyList = validationService.getMyValidation(uid);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("validationlist", validationList);
        dataMap.put("applylist", applyList);
        return Result.success(dataMap);
    }

    /*@GetMapping("/getMyValidation")
    @Deprecated // 弃用
    public Result getMyValidation(){
        Integer uid = getUserIdFromSession();
        if(uid==null) return Result.fail("非法请求，请先登录");
        List<Validation> validations = validationService.getMyValidation(uid);
        if(validations==null) return Result.fail();
        else return Result.success(validations);
    }*/

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

    //获取好友分组信息接口
    @GetMapping("/getFriendship")
    public Result getFriendship(HttpServletRequest request) {
        HttpSession session = request.getSession();
        JSONObject data  = friendService.getFriendship(session);

        if(data!=null) return Result.success(data);
        else return Result.fail();
    }

    //创建新的好友分组接口
    @PostMapping("/createFriendship")
    public Result createFriendship(@RequestParam("friendship_name") String friendshipName,
                                   HttpServletRequest request) {
        HttpSession session = request.getSession();
        Result result  = friendService.createFriendship(friendshipName, session);

        return result;
    }

    //获取好友列表接口
    @GetMapping("/getFriendList")
    public Result getFriendList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        JSONArray data = friendService.getAllFriendshipsAndFriends(session);

        if (data != null ) {
            return Result.success(data);
        } else {
            return Result.fail();
        }
    }
    //获取用户所在群接口
    @GetMapping("/getUserGroups")
    public Result getUserGroups(HttpServletRequest request) {
        HttpSession session = request.getSession();
        JSONArray data = JSONArray.of(friendService.getAllUserGroups(session));

        if (data != null ) {
            return Result.success(data);
        } else {
            return Result.fail();
        }
    }


    //根据传入uid返回自己与该uid私聊记录
    @GetMapping("/getPrivateMessages")
    public Result getPrivateMessages(@RequestParam("user_id") Integer otherUserId,HttpServletRequest request) {
        HttpSession session = request.getSession();
        return friendService.getPrivateMessages(888888,session);

    }


    //根据传入gid返回群聊记录
    @GetMapping("/getGroupMessages")
    public Result getGroupMessages(@RequestParam("group_id") Integer groupId,HttpServletRequest request) {
        HttpSession session = request.getSession();
        return  friendService.getGroupMessages(88888888,session);
    }
}
