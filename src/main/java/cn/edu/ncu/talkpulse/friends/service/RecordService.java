package cn.edu.ncu.talkpulse.friends.service;

import cn.edu.ncu.talkpulse.dto.Result;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface RecordService {

    // 给好友发送私聊文本消息
    Result sendMessage(Integer senderId,Integer receiverId,String content,Integer type);

    // 发送文件消息
    Result sendFileMessage(Integer senderId, Integer receiverId, MultipartFile content, Integer type);

    // 已读好友消息
    Result readMessage(Integer uid, Integer friendId);
}
