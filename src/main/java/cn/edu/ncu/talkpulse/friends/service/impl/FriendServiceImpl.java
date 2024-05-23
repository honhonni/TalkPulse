package cn.edu.ncu.talkpulse.friends.service.impl;



import cn.edu.ncu.talkpulse.account.dao.AccountDao;
import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.friends.dao.FriendDao;
import cn.edu.ncu.talkpulse.friends.dao.FriendshipDao;
import cn.edu.ncu.talkpulse.friends.entity.Friend;
import cn.edu.ncu.talkpulse.friends.entity.Friendship;
import cn.edu.ncu.talkpulse.friends.entity.FriendshipWithFriendsDTO;
import cn.edu.ncu.talkpulse.group.dao.GroupDao;
import cn.edu.ncu.talkpulse.friends.service.FriendService;
import com.alibaba.fastjson2.JSONArray;
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

    // 获取用户的好友列表
    @Override
    public JSONArray getAllFriendshipsAndFriends(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("user_id");

        // 获取分组
        List<Friendship> friendships = friendDao.getFriendship(userId);
        JSONArray friendshipsArray = new JSONArray();
        // 处理数据库查询结果
        if (friendships != null && !friendships.isEmpty()) {
            for (int i = 0; i < friendships.size(); i++) {
                JSONObject friendshipJson = new JSONObject();
                friendshipJson.put("friendship_id", friendships.get(i).getFriendship_id());
                friendshipJson.put("friendship_name", friendships.get(i).getFriendship_name());
                List<UserInfo> userInfos = friendDao.getfriendsid(friendships.get(i).getFriendship_id());
                friendshipJson.put("friendlist", userInfos);
                friendshipsArray.add(friendshipJson);
            }
            return friendshipsArray;
        }else {
            return null;
        }
    }
    // 获取用户的好友分组信息
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
    public Result createFriendship(String friendshipName, HttpSession session) {
        Integer creatId = (Integer) session.getAttribute("user_id");

        // 在数据库中检查是否已经存在该分组名
        int count = friendshipDao.countByFriendshipName(friendshipName);
        if (count > 0) return Result.fail("该分组名已存在，请重新命名分组名");

        // 分组名不存在，执行创建分组操作
        int affectedRows = friendshipDao.createFriendship(friendshipName, creatId);
        if (affectedRows > 0) {
            return Result.success();
        } else {
            return Result.fail("创建失败");
        }
    }
}