package cn.edu.ncu.talkpulse.friends.entity;

import java.util.List;
//
//public class FriendshipWithFriendsDTO {
//    private Integer friendship_id;
//    private String friendship_name;
//    private List<Integer> friendIds;  // 假设朋友列表存储的是朋友的ID列表
//
//    // 构造方法
//    public FriendshipWithFriendsDTO(Integer friendshipId, String friendshipName, List<Integer> friendIds) {
//        this.friendship_id = friendshipId;
//        this.friendship_name = friendshipName;
//        this.friendIds = friendIds;
//    }
//
//    // Getter和Setter
//    public Integer getFriendshipId() {
//        return friendship_id;
//    }
//
//    public void setFriendshipId(Integer friendshipId) {
//        this.friendship_id = friendshipId;
//    }
//
//    public String getFriendshipName() {
//        return friendship_name;
//    }
//
//    public void setFriendshipName(String friendshipName) {
//        this.friendship_name = friendshipName;
//    }
//
//    public List<Integer> getFriendIds() {
//        return friendIds;
//    }
//
//    public void setFriendIds(List<Integer> friendIds) {
//        this.friendIds = friendIds;
//    }
//}
public class FriendshipWithFriendsDTO {
    private Integer friendship_id;
    private String friendship_name;
    private List<Integer> friendIds;

    public FriendshipWithFriendsDTO(Integer friendshipId, String friendshipName, List<Integer> friendIds) {
        this.friendship_id = friendshipId;
        this.friendship_name = friendshipName;
        this.friendIds = friendIds;
    }

    public Integer getFriendshipId() {
        return friendship_id;
    }

    public void setFriendshipId(Integer friendshipId) {
        this.friendship_id = friendshipId;
    }

    public String getFriendshipName() {
        return friendship_name;
    }

    public void setFriendshipName(String friendshipName) {
        this.friendship_name = friendshipName;
    }

    public List<Integer> getFriendIds() {
        return friendIds;
    }

    public void setFriendIds(List<Integer> friendIds) {
        if (friendIds != null) {
            this.friendIds = friendIds;
        } else {
            throw new IllegalArgumentException("Friend IDs list cannot be null");
        }
    }
}