package cn.edu.ncu.talkpulse.friends.service;

//import cn.edu.ncu.talkpulse.entity.Friendship;
//import cn.edu.ncu.talkpulse.entity.Group;
//import cn.edu.ncu.talkpulse.entity.Record;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;

import java.util.List;

public interface FriendService {
    List<Record> searchRecords(String keyword, Integer userId);
    List<UserInfo> getFriendsList(Integer userId);
//    List<Group> getGroupList(Integer userId);
//    List<Friendship> getFriendshipList();
//    void updateUserInfo(UserInfo user);
//    Friendship createFriendship(Friendship friendship);
}
