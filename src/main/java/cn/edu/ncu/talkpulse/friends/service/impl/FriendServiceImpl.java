package cn.edu.ncu.talkpulse.friends.service.impl;



import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.friends.dao.FriendDao;
//import cn.edu.ncu.talkpulse.user.dao.FriendDao;
import cn.edu.ncu.talkpulse.friends.entity.Friend;
import cn.edu.ncu.talkpulse.friends.entity.Friendship;
import cn.edu.ncu.talkpulse.group.dao.GroupDao;
//import cn.edu.ncu.talkpulse.user.dao.FriendshipDao;
//import cn.edu.ncu.talkpulse.user.entity.Friendship;
//import cn.edu.ncu.talkpulse.user.entity.Record;
import cn.edu.ncu.talkpulse.friends.service.FriendService;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private GroupDao groupDao;


    @Override
    public List<UserInfo> getFriendsList(Integer userId) {
        return null;
    }

    // 获取用户的好友分组列表
    public List<Friendship> getFriendGroups(Long userId) {
        return friendDao.getFriendGroups(userId);
    }

    // 创建好友分组
    public int createFriendGroup(String friendshipName, Long userId) {
        return friendDao.createFriendGroup(friendshipName, userId);
    }

//
//    @Override
//    public List<Record> searchRecords(String keyword, Integer userId) {
//        return null;
//    }




}