package cn.edu.ncu.talkpulse.friends.service;


import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface FriendService {
//    List<Record> searchRecords(String keyword, Integer user_id);

    List<UserInfo> getFriendsList(Integer userId);



    // 获取用户的好友分组列表
//    @Override
//    public List<Friendship> getFriendGroups(Integer userId) {
//        return friendDao.getFriendGroups(userId);
//    }
//
    // 获取用户的好友分组列表
    JSONObject getFriendGroups( HttpSession session);

    JSONObject createFriendGroup(String friendshipName, Integer userId);

    JSONObject search(Integer userId, HttpSession session);

}
