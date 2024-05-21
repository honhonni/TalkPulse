package cn.edu.ncu.talkpulse.friends.dao;

import cn.edu.ncu.talkpulse.friends.entity.Friend;
import cn.edu.ncu.talkpulse.friends.entity.Friendship;
import com.alibaba.fastjson2.JSONObject;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FriendshipDao {

    // 根据uid和分组名查询分组id
    @Select("select friendship_id from friendship where creat_id = #{uid} and friendship_name = #{name}")
    Integer getFriendShipIdByName(@Param("uid") Integer uid,@Param("name") String name);

    // 建立好友关系
    @Insert("insert into friend values (#{firstid},#{secondid},#{given_friendshipid})")
    int addFriend(Friend friend);

    // 更新好友分组
    @Update("update friend set given_friendshipid = #{given_friendshipid} where firstid = #{firstid} and secondid = #{secondid}")
    int updateFriendShip(Friend friend);

    // 根据好友id查询关系是否存在
    @Select("select * from friend where firstid = #{uid} and secondid = #{friendId}")
    Friend getFriendByUserId(@Param("uid") Integer uid, @Param("friendId") Integer friendId);

    // 根据uid查询和分组id查询是否存在
    @Select("select * from friendship where creat_id = #{uid} and friendship_id = #{friendShipId}")
    Friendship getFriendShipById(@Param("uid") Integer uid, @Param("friendShipId") Integer friendShipId);

    //创建好友分组
    @Insert("INSERT INTO friendship (friendship_name, creat_id) VALUES (#{friendshipName}, #{creatId})")
    int createFriendship(@Param("friendshipName") String friendshipName, @Param("creatId") Integer creatId);
    @Select("SELECT COUNT(*) FROM friendship WHERE friendship_name = #{friendshipName}")
    int countByFriendshipName(@Param("friendshipName") String friendshipName);
}
