package cn.edu.ncu.talkpulse.friends.dao;

import cn.edu.ncu.talkpulse.friends.entity.Friend;
import cn.edu.ncu.talkpulse.friends.entity.Friendship;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FriendShipDao {

    // 同意添加好友申请后：
    // 1、先查询用户的默认分组（我的好友）的分组id
    @Select("select friendship_id from friendship where creat_id = #{uid} and friendship_name = #{name}")
    Integer getFriendShipIdByName(@Param("uid") Integer uid,@Param("name") String name);

    // 建立好友关系
    @Insert("insert into friend values (#{firstid},#{secondid},#{given_friendshipid})")
    int addFriend(Friend friend);


}
