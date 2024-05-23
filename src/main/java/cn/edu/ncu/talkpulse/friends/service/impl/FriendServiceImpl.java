package cn.edu.ncu.talkpulse.friends.service.impl;



import cn.edu.ncu.talkpulse.account.dao.AccountDao;
import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.friends.dao.FriendDao;
import cn.edu.ncu.talkpulse.friends.dao.FriendshipDao;
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

    @Autowired
    private FriendshipDao friendshipDao;

    @Override
    public List<UserInfo> getFriendsList(Integer userId) {
        return null;
    }



    // 查询用户
    @Override
    public JSONObject search(Integer userId, HttpSession session) {
        Integer myId = (Integer) session.getAttribute("user_id");

        UserInfo userInfo = accountDao.searchUserById(userId);
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
    public JSONObject getFriendship(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("user_id");

        List<Friendship> getFriendship = friendDao.getFriendship(userId);
        JSONObject data = new JSONObject();
        System.out.println(getFriendship);

        if (getFriendship != null && !getFriendship.isEmpty()) {
            // 如果查询结果非空，就返回这些分组信息
            data.put("status", 200);
            data.put("message", "Success");
            data.put("data", getFriendship);
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
    public JSONObject createFriendship(String friendshipName, HttpSession session) {
        Integer creatId = (Integer) session.getAttribute("user_id");

        // 检查分组名是否为空
        if (friendshipName == null || friendshipName.trim().isEmpty()) {
            JSONObject data = new JSONObject();
            data.put("success", false);
            data.put("message", "分组名不能为空");
            return data;
        }

        // 在数据库中检查是否已经存在该分组名
        int count = friendshipDao.countByFriendshipName(friendshipName);
        if (count > 0) {
            // 如果分组名已存在，直接返回错误信息
            JSONObject data = new JSONObject();
            data.put("success", false);
            data.put("message", "该分组名已存在，请重新命名分组名");
            return data;
        }

        // 分组名不存在，执行创建分组操作
        int affectedRows = friendshipDao.createFriendship(friendshipName, creatId);
        JSONObject data = new JSONObject();
        if (affectedRows > 0) {
            data.put("success", true);
            data.put("message", "好友分组创建成功");
        } else {
            data.put("success", false);
            data.put("message", "好友分组创建失败");
        }
        return data;
    }
}