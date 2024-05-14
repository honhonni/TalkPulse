package cn.edu.ncu.talkpulse.user.service.impl;



import cn.edu.ncu.talkpulse.user.dao.UserDao;
//import cn.edu.ncu.talkpulse.user.dao.FriendDao;
import cn.edu.ncu.talkpulse.group.dao.GroupDao;
//import cn.edu.ncu.talkpulse.user.dao.FriendshipDao;
//import cn.edu.ncu.talkpulse.user.entity.Friendship;
//import cn.edu.ncu.talkpulse.user.entity.Record;
import cn.edu.ncu.talkpulse.user.entity.UserInfo;
import cn.edu.ncu.talkpulse.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

//    @Autowired
//    private FriendDao friendDao;

    @Autowired
    private GroupDao groupDao;

//    @Autowired
//    private FriendshipDao friendshipDao;
    @Override
    public UserInfo findById(Integer user_id){
            return userDao.findById(user_id);
    }
    @Override
    public List<Record> searchRecords(String keyword, Integer user_id) {
        return userDao.findRecordsByKeyword(keyword, user_id);
    }

    @Override
    public List<UserInfo> getFriendsList(Integer user_id) {
        return null;
    }

//    @Override
//    public List<UserInfo> getFriendsList(Integer userId) {
//        return friendDao.findAllFriends(userId);
//    }

//    @Override
//    public List<groupinfo> getGroupList(Integer userId) {
//        return GroupDao.findGroupsByUserId(userId);
//    }

//    @Override
//    public List<Friendship> getFriendshipList() {
//        return friendshipDao.findAllFriendships();
//    }

//    @Override
//    public void updateUserInfo(UserInfo user) {
//        try {
//            userDao.updateUserInfo(user);
//        } catch (Exception e) {
//            // 适当的异常处理逻辑
//            e.printStackTrace();
//            // 可以考虑抛出更具体的异常或者进行日志记录
//        }
//    }

//    @Override
//    public Friendship createFriendship(Friendship friendship) {
//        try {
//            return friendshipDao.addFriendship(friendship);
//        } catch (Exception e) {
//            // 适当的异常处理逻辑
//            e.printStackTrace();
//            // 可以考虑抛出更具体的异常或者进行日志记录
//            return null; // 或者根据业务逻辑决定返回什么
//        }
//    }
}