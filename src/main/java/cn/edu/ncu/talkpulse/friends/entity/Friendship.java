package cn.edu.ncu.talkpulse.friends.entity;

// 好友分组表
public class Friendship {
    // 分组id
    private Integer friendship_id;

    // 分组名
    private String friendship_name;

    // 创建者
    private Integer creat_id;

    @Override
    public String toString() {
        return "Friendship{" +
                "friendship_id=" + friendship_id +
                ", friendship_name='" + friendship_name + '\'' +
                ", creat_id=" + creat_id +
                '}';
    }

    public Friendship(Integer friendship_id, String friendship_name, Integer creat_id) {
        this.friendship_id = friendship_id;
        this.friendship_name = friendship_name;
        this.creat_id = creat_id;
    }

    public Integer getCreat_id() {
        return creat_id;
    }

    public void setCreat_id(Integer creat_id) {
        this.creat_id = creat_id;
    }

    public Friendship() {
    }

    public Friendship(Integer friendshipId, String friendshipName) {
        this.friendship_id = friendshipId;
        this.friendship_name = friendshipName;
    }

    public Integer getFriendship_id() {
        return friendship_id;
    }

    public void setFriendship_id(Integer friendship_id) {
        this.friendship_id = friendship_id;
    }

    public String getFriendship_name() {
        return friendship_name;
    }

    public void setFriendship_name(String friendship_name) {
        this.friendship_name = friendship_name;
    }


}
