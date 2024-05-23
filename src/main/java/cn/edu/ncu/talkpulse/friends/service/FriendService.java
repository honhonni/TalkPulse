package cn.edu.ncu.talkpulse.friends.service;


import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.dto.Result;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface FriendService {
//    List<Record> searchRecords(String keyword, Integer user_id);





    //获取好友列表
    JSONArray getAllFriendshipsAndFriends(HttpSession session);




    // 获取用户的好友分组列表
    JSONObject getFriendship( HttpSession session);

    //创建好友分组
    Result createFriendship(String friendshipName, HttpSession session);

    //查找用户信息
    JSONObject search(Integer userId, HttpSession session);

}
