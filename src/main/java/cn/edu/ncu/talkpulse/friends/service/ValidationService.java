package cn.edu.ncu.talkpulse.friends.service;


import cn.edu.ncu.talkpulse.friends.entity.Validation;

import java.util.List;

public interface ValidationService {

    // 发送添加好友申请
    Boolean sendValidation(Integer uid, Integer friendId);

    // 获取用户收到的好友申请
    List<Validation> getValidation(Integer uid);
}
