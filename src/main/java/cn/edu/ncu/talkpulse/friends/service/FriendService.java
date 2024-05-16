package cn.edu.ncu.talkpulse.friends.service;


import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.friends.entity.Friendship;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FriendService {
//    List<Record> searchRecords(String keyword, Integer user_id);

    List<UserInfo> getFriendsList(Integer userId);

    List<Friendship> getFriendGroups(Long userId);

    int createFriendGroup(String friendshipName, Long userId);

    JSONObject search(Integer userId, HttpSession session);

}
