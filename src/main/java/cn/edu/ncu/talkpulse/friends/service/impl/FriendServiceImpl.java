package cn.edu.ncu.talkpulse.friends.service.impl;



import cn.edu.ncu.talkpulse.account.dao.AccountDao;
import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.friends.entity.Record;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.friends.dao.FriendDao;
import cn.edu.ncu.talkpulse.friends.dao.FriendshipDao;
import cn.edu.ncu.talkpulse.friends.entity.Friend;
import cn.edu.ncu.talkpulse.friends.entity.Friendship;
import cn.edu.ncu.talkpulse.group.dao.GroupDao;
import cn.edu.ncu.talkpulse.friends.service.FriendService;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.entity.Grouprecord;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            Friendship friendship = friendDao.getFriendshipById(myId,userId);
            JSONObject data = new JSONObject();
            data.put("data", userInfo);
            if(friend != null){
                data.put("isfriend", true );
                if(friendship!=null){
                    data.put("friendship", friendship );
                }
                return data;
            }else{
                data.put("isfriend", false );
                return data;
            }
        } else {
            return null;
        }
    }

//
//    // 根据关键词查找聊天记录及群聊记录
//    @Override
//
//
//
//        public Result searchRecordsByKeyword (String keyword, HttpSession session){
//            Integer userId = (Integer) session.getAttribute("userId");
//
//            List<allRecords> mixedRecords = friendDao.searchRecordsByKeywordAndGroup(keyword, userId);
//            List<Record> personalRecords = new ArrayList<>();
//            List<grouprecord> groupRecords = new ArrayList<>();
//
//            for (allRecords mixedRecord : mixedRecords) {
//                if ("personal".equals(mixedRecord.getRecordType())) {
//                    personalRecords.add(new Record(
//                            mixedRecord.getRecordSenderid(),
//                            mixedRecord.getRecordContent(),
//                            mixedRecord.getRecordTime(),
//                            mixedRecord.getRecordSenderid(),
//                            mixedRecord.getRecordRecipientid(),
//                            null  // 需要传递适当的read_status值
//                    ));
//                } else if ("group".equals(mixedRecord.getRecordType())) {
//                    groupRecords.add(new grouprecord(
//
//                             // 如果存在群ID和需要处理的其他属性
//                    ));
//                }
//            }
//
//            Map<String, Object> dataMap = new HashMap<>();
//            dataMap.put("personalRecords", personalRecords);
//            dataMap.put("groupRecords", groupRecords);
//
//            return Result.success("查询成功", dataMap);
//        }


    //根据传入uid返回用户与该uid聊天记录
    @Override
    public Result getPrivateMessages(Integer otherUserId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("user_id");
        List<Record> records = friendDao.getPrivateMessages( otherUserId,userId);
        if (userId == null) {
            return Result.fail("未登录，无法获取用户ID");
        }

//        List<Record> records = friendDao.getPrivateMessages(userId, otherUserId);
        if (records == null || records.isEmpty()) {
            return Result.fail("没有找到相关的聊天记录");
        }

        return Result.success("获取聊天记录成功", records);
    }

    //根据传入gid返回该群聊天记录
    @Override
    public Result getGroupMessages(Integer groupId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("user_id");
        List<Grouprecord> grouprecords = friendDao.getGroupMessages( groupId,userId);
        if (userId == null) {
            return Result.fail("未登录，无法获取用户ID");
        }


        if (grouprecords == null || grouprecords.isEmpty()) {
            return Result.fail("没有找到相关的聊天记录");
        }

        return Result.success("获取聊天记录成功", grouprecords);
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

    //获取用户所在群列表
    @Override
    public Result getAllUserGroups(HttpSession session) {

        Integer userId = (Integer) session.getAttribute("user_id");
        List<Groupinfo> allGroups = friendDao.getAllUserGroups(userId);

//            JSONObject response = new JSONObject();
        JSONArray createdGroupsArray = new JSONArray();
        JSONArray joinedGroupsArray = new JSONArray();
        for (Groupinfo group : allGroups) {
            JSONObject groupJson = new JSONObject();
            groupJson.put("group_id", group.getGroup_id());
            groupJson.put("group_name", group.getGroup_name());
            groupJson.put("group_introduce", group.getGroup_introduce());
            groupJson.put("group_photo", group.getGroup_photo());

            if (group.getGroup_hostid().equals(userId)) {
                createdGroupsArray.add(groupJson);
            } else {
                joinedGroupsArray.add(groupJson);
            }
        }

        JSONArray dataArray = new JSONArray();
        dataArray.add(createdGroupsArray);
        dataArray.add(joinedGroupsArray);
        return Result.success(dataArray);

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