package cn.edu.ncu.talkpulse.friends.entity;

// 好友关系表
public class Friend {
    // 一号用户id
    private Integer firstid;

    // 二号用户id
    private Integer secondid;

    // 1给2设置的分组id
    private Integer given_friendshipid;

    public Friend(Integer firstuserId, Integer seconduserId, Integer givenfriendshipId) {
        this.firstid = firstuserId;
        this.secondid = seconduserId;
        this.given_friendshipid = givenfriendshipId;
    }

    public Friend() {
    }

    @Override
    public String toString() {
        return "Friend{" +
                "firstuserId=" + firstid +
                ", seconduserId=" + secondid +
                ", givenfriendshipId=" + given_friendshipid +
                '}';
    }

    public Integer getFirstid() {
        return firstid;
    }

    public void setFirstid(Integer firstid) {
        this.firstid = firstid;
    }

    public Integer getSecondid() {
        return secondid;
    }

    public void setSecondid(Integer secondid) {
        this.secondid = secondid;
    }

    public Integer getGiven_friendshipid() {
        return given_friendshipid;
    }

    public void setGiven_friendshipid(Integer given_friendshipid) {
        this.given_friendshipid = given_friendshipid;
    }
}
