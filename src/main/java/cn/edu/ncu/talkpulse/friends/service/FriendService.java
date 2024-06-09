package cn.edu.ncu.talkpulse.friends.service;


import cn.edu.ncu.talkpulse.dto.Result;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;

public interface FriendService {






    //获取好友列表
    JSONArray getAllFriendshipsAndFriends(HttpSession session);

    //获取所在群列表
    Result getAllUserGroups(HttpSession session);

    // 获取用户的好友分组列表
    JSONObject getFriendship( HttpSession session);

    //创建好友分组
    Result createFriendship(String friendshipName, HttpSession session);

    //查找用户信息
    JSONObject search(Integer userId, HttpSession session);

//    //根据关键词查找聊天记录
//    Result searchRecordsByKeyword(String keyword, HttpSession session);

    //根据传入uid返回自己与该uid的私聊记录
    Result getPrivateMessages(Integer otherUserId, HttpSession session);

    //根据传入gid返回自己与该群的群聊记录
    Result getGroupMessages(Integer groupId, HttpSession session);

    //删除好友
    Result deleteFriend(HttpSession session, Integer secondId);
}
