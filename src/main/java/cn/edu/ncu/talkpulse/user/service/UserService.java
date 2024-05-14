package cn.edu.ncu.talkpulse.user.service;

import cn.edu.ncu.talkpulse.user.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
//import cn.edu.ncu.talkpulse.entity.Friendship;
//import cn.edu.ncu.talkpulse.entity.Group;
//import cn.edu.ncu.talkpulse.entity.Record;

import java.util.List;

public interface UserService {
    UserInfo findById(Integer userId);
    List<Record> searchRecords(String keyword, Integer userId);
    List<UserInfo> getFriendsList(Integer userId);
//    List<Group> getGroupList(Integer userId);
//    List<Friendship> getFriendshipList();
//    void updateUserInfo(UserInfo user);
//    Friendship createFriendship(Friendship friendship);
}
