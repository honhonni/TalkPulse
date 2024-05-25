package cn.edu.ncu.talkpulse.group.service;

import cn.edu.ncu.talkpulse.dto.Result;
import org.springframework.web.multipart.MultipartFile;

public interface GroupMessageService {

    // 设置已读群消息
    Result readGroupMessage(Integer uid, Integer groupId);

    // 发送文本消息
    Result sendMessage(Integer uid, Integer gid, String content, Integer type);

    // 发送文件消息
    Result sendFileMessage(Integer uid, Integer gid, MultipartFile content, Integer type);
}
