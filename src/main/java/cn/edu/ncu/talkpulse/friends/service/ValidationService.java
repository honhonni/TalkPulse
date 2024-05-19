package cn.edu.ncu.talkpulse.friends.service;


import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.friends.entity.Validation;

import java.util.List;

public interface ValidationService {

    // 发送添加好友申请
    Result sendValidation(Integer uid, Integer friendId);

    // 获取用户收到的好友申请
    List<Validation> getValidation(Integer uid);

    // 获取用户发送的好友申请
    List<Validation> getMyValidation(Integer uid);

    // 处理好友申请请求
    Result handleValidation(Integer uid, Integer validationId, Boolean agree);

    // 移动分组
    Result removeFriend(Integer uid,Integer friendId,Integer friendshipId);
}
