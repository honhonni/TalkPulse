package cn.edu.ncu.talkpulse.controller;

import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.friends.service.RecordService;
import cn.edu.ncu.talkpulse.group.service.GroupMessageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MessageController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private GroupMessageService groupMessageService;

    @Autowired
    HttpServletRequest request;

    private Integer getUserIdFromSession() {
        return (Integer) request.getSession().getAttribute("user_id");
    }

    /**
     * 发送私聊文本消息
     * @param receiverId
     * @param content
     * @param type
     * @return
     */
    @PostMapping("/friends/sendTextMessage")
    public Result sendPrivateMessage(@RequestParam("receiverid") Integer receiverId,
                              @RequestParam("content") String content,
                              @RequestParam("type") Integer type){
        Integer uid = getUserIdFromSession();
        if(uid==null) return Result.fail("非法请求，请先登录");
        return recordService.sendMessage(uid,receiverId,content,type);
    }

    /**
     * 发送私聊文件消息（图片/语音）
     * @param receiverId
     * @param content
     * @param type
     * @return
     */
    @PostMapping("/friends/sendFileMessage")
    public Result sendPrivateMessage(@RequestParam("receiverid") Integer receiverId,
                                     @RequestParam("content") MultipartFile content,
                                     @RequestParam("type") Integer type){
        Integer uid = getUserIdFromSession();
        if(uid==null) return Result.fail("非法请求，请先登录");
        return recordService.sendFileMessage(uid,receiverId,content,type);
    }

    /**
     * 发送群聊文本消息
     * @param gid
     * @param content
     * @param type
     * @return
     */
    @PostMapping("/group/sendTextMessage")
    public Result sendGroupMessage(@RequestParam("gid") Integer gid,
                                     @RequestParam("content") String content,
                                     @RequestParam("type") Integer type){
        Integer uid = getUserIdFromSession();
        if(uid==null) return Result.fail("非法请求，请先登录");
        return groupMessageService.sendMessage(uid,gid,content,type);
    }

    /**
     * 发送群聊文件消息（图片/语音）
     * @param gid
     * @param content
     * @param type
     * @return
     */
    @PostMapping("/group/sendFileMessage")
    public Result sendGroupMessage(@RequestParam("gid") Integer gid,
                                   @RequestParam("content") MultipartFile content,
                                   @RequestParam("type") Integer type){
        Integer uid = getUserIdFromSession();
        if(uid==null) return Result.fail("非法请求，请先登录");
        return groupMessageService.sendFileMessage(uid,gid,content,type);
    }

    /**
     * 已读私聊消息
     * @param friendId
     * @return
     */
    @PostMapping("/record/read")
    public Result readPrivateMessage(@RequestParam("uid") Integer friendId){
        Integer uid = getUserIdFromSession();
        if(uid==null) return Result.fail("非法请求，请先登录");
        return recordService.readMessage(uid,friendId);
    }

    /**
     * 已读群聊消息
     * @param groupId
     * @return
     */
    @PostMapping("/grouprecord/read")
    public Result readGroupMessage(@RequestParam("gid") Integer groupId){
        Integer uid = getUserIdFromSession();
        if(uid==null) return Result.fail("非法请求，请先登录");
        return groupMessageService.readGroupMessage(uid,groupId);
    }
}
