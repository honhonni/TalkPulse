package cn.edu.ncu.talkpulse.friends.entity;

// 好友关系表
public class Friend {
    // 一号用户id
    private Integer firstuserId;

    // 二号用户id
    private Integer seconduserId;

    // 1给2设置的分组id
    private Integer givenfriendshipId;

    public Friend(Integer firstuserId, Integer seconduserId, Integer givenfriendshipId) {
        this.firstuserId = firstuserId;
        this.seconduserId = seconduserId;
        this.givenfriendshipId = givenfriendshipId;
    }

    public Friend() {
    }

    @Override
    public String toString() {
        return "Friend{" +
                "firstuserId=" + firstuserId +
                ", seconduserId=" + seconduserId +
                ", givenfriendshipId=" + givenfriendshipId +
                '}';
    }

    public Integer getFirstuserId() {
        return firstuserId;
    }

    public void setFirstuserId(Integer firstuserId) {
        this.firstuserId = firstuserId;
    }

    public Integer getSeconduserId() {
        return seconduserId;
    }

    public void setSeconduserId(Integer seconduserId) {
        this.seconduserId = seconduserId;
    }

    public Integer getGivenfriendshipId() {
        return givenfriendshipId;
    }

    public void setGivenfriendshipId(Integer givenfriendshipId) {
        this.givenfriendshipId = givenfriendshipId;
    }
}
