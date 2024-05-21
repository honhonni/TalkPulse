package cn.edu.ncu.talkpulse.friends.service.impl;



import cn.edu.ncu.talkpulse.account.dao.AccountDao;
import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.friends.dao.FriendDao;
import cn.edu.ncu.talkpulse.friends.entity.Friend;
import cn.edu.ncu.talkpulse.friends.entity.Friendship;
import cn.edu.ncu.talkpulse.group.dao.GroupDao;
import cn.edu.ncu.talkpulse.friends.service.FriendService;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private AccountDao accountDao;

    @Override
    public List<UserInfo> getFriendsList(Integer userId) {
        return null;
    }



    // 查询用户
    @Override
    public JSONObject search(Integer userId, HttpSession session) {
        Integer myId = (Integer) session.getAttribute("user_id");

        UserInfo userInfo = accountDao.searchUserById(userId);
        System.out.println(userInfo);
        if (userInfo != null) {
            Friend friend = friendDao.isfriend(myId, userId);
            JSONObject data = new JSONObject();
            data.put("data", userInfo);
            if(friend != null){
                data.put("isfriend", true );
                return data;

            }else{
                data.put("isfriend", false );
                return data;
            }
        } else {
            return null;
        }
    }


    // 获取用户的好友分组列表
//    @Override
//    public List<Friendship> getFriendGroups(Integer userId) {
//        return friendDao.getFriendGroups(userId);
//    }
//
    // 获取用户的好友分组列表
    @Override
    public JSONObject getFriendGroups(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("user_id");

        List<Friendship> friendGroups = friendDao.getFriendGroups(userId);
        JSONObject data = new JSONObject();
        System.out.println(friendGroups);

        if (friendGroups != null && !friendGroups.isEmpty()) {
            // 如果查询结果非空，就返回这些分组信息
            data.put("status", 200);
            data.put("message", "Success");
            data.put("data", friendGroups);
        } else {
            // 如果查询结果为空，说明该用户没有好友分组，或查询失败
            data.put("status", 201);
            data.put("message", "No Content");
            data.put("data", "没有找到好友分组或用户不存在");
        }
        return data;
    }

    //创建好友分组
    @Override
    public JSONObject createFriendGroup(String friendshipName, Integer userId) {
        int groupId = friendDao.createFriendGroup(friendshipName, userId);
        JSONObject data = new JSONObject();

        if (groupId > 0) {
            // 如果创建分组成功，返回分组的ID
            data.put("status", 200);
            data.put("message", "Success");
            data.put("data", groupId);
        } else {
            // 如果创建分组失败，返回错误信息
            data.put("status", 500);
            data.put("message", "Internal Server Error");
            data.put("data", "创建分组失败");
        }
        return data;
    }




}