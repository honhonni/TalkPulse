package cn.edu.ncu.talkpulse.user.dao;

import cn.edu.ncu.talkpulse.user.entity.UserInfo;

import java.util.List;

// 用户信息DAO接口
public interface UserDao {
    UserInfo findById(Integer userId);
    List<Record> findRecordsByKeyword(String keyword, Integer userId);
    void updateUserInfo(UserInfo user);
}

//// 好友列表DAO接口
//public interface FriendDao {
//    List<User> findAllFriends(Integer userId);
//}
//
//// 好友分组DAO接口
//public interface FriendshipDao {
//    List<Friendship> findAllFriendships();
//    Friendship addFriendship(Friendship friendship);
//}
//
//// 用户所在群组DAO接口
//public interface GroupMemberDao {
//    List<Group> findGroupsByUserId(Integer userId);
//}
