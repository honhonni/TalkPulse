package cn.edu.ncu.talkpulse.friends.entity;

// 好友分组表
public class Friendship {
    // 分组id
    private Integer friendshipId;

    // 分组名
    private String friendshipName;

    public Friendship() {
    }

    public Friendship(Integer friendshipId, String friendshipName) {
        this.friendshipId = friendshipId;
        this.friendshipName = friendshipName;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "friendshipId=" + friendshipId +
                ", friendshipName='" + friendshipName + '\'' +
                '}';
    }

    public Integer getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(Integer friendshipId) {
        this.friendshipId = friendshipId;
    }

    public String getFriendshipName() {
        return friendshipName;
    }

    public void setFriendshipName(String friendshipName) {
        this.friendshipName = friendshipName;
    }
}
