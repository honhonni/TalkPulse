package cn.edu.ncu.talkpulse.user.service;

import cn.edu.ncu.talkpulse.user.dao.UserDao;
import cn.edu.ncu.talkpulse.user.entity.UserInfo;

import java.util.List;

public class UserService {
    private UserDao userDao;
//    private FriendDao friendDao;
//    private GroupMemberDao groupMemberDao;
//    private FriendshipDao friendshipDao;

    // 根据关键词查找聊天记录
    public List<Record> searchRecords(String keyword, Integer userId) {
        // 通过DAO获取数据
        return userDao.findRecordsByKeyword(keyword, userId);
    }

//    // 获取好友列表
//    public List<UserInfo> getFriendsList(Integer userId) {
//        return friendDao.findAllFriends(userId);
//    }
//
//    // 获取所在群列表
//    public List<Group> getGroupList(Integer userId) {
//        return groupMemberDao.findGroupsByUserId(userId);
//    }
//
//    // 获取分组信息
//    public List<Friendship> getFriendshipList() {
//        return friendshipDao.findAllFriendships();
//    }
//
//    // 修改个人信息
//    public void updateUserInfo(UserInfo user) {
//        userDao.updateUserInfo(user);
//    }
//
//    // 创建分组
//    public Friendship createFriendship(Friendship friendship) {
//        return friendshipDao.addFriendship(friendship);
//    }
}