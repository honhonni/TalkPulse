package cn.edu.ncu.talkpulse.friends.dao;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.friends.entity.Friend;
import cn.edu.ncu.talkpulse.friends.entity.Friendship;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

// 好友信息DAO接口
@Mapper
public interface FriendDao {


//    // 根据关键词和用户ID查找聊天记录
//    @Select("SELECT * FROM Record WHERE (record_content LIKE CONCAT('%', #{keyword}, '%') OR record_senderid = #{userId} OR record_recipientid = #{userId}) AND record_senderid = #{userId}")
//    List<Record> findRecordsByKeyword(@Param("keyword") String keyword, @Param("userId") Integer userId);
//
//    // 更新用户信息
//    @Update("UPDATE UserInfo SET user_name = #{user.userName}, user_pwd = #{user.userPwd}, user_gender = #{user.userGender}, user_age = #{user.userAge}, user_introduce = #{user.userIntroduce}, user_photo = #{user.userPhoto} WHERE user_id = #{user.userId}")
//    void updateUserInfo(@Param("user") UserInfo user);
//
//    // 新增添加好友验证
//    @Select("insert into validation values ()")
//    int addValidation(Validation validation);
//
//
//    // 查询添加好友验证
//    @Select("select * from validation where validation_receiverid = #{uid}")
//    List<Validation> findValidationByUserId(Integer uid);
//



    @Select("select * from friend where firstid = #{myId} and secondid = #{userId}")
    Friend isfriend(@Param("myId") Integer myId, @Param("userId") Integer userId);

    // 获取好友列表
    @Select("SELECT u.user_id,u.user_name , u.user_gender, u.user_age,u.user_introduce, u.user_photo FROM friend f,userinfo u WHERE f.given_friendshipid = #{friendshipid} and f.secondid = u.user_id")
    List<UserInfo> getfriendsid(@Param("friendshipid") Integer friendshipid);


    // 获取用户创建的群组和用户所在的群组（包括加入的群组）
//    @Select("SELECT g.group_id, g.group_name, g.group_introduce, g.group_photo " +
//                "FROM groupinfo g " +
//                "WHERE g.group_hostid = #{userId} " +
//                "UNION " +
//                "SELECT gi.group_id, gi.group_name, gi.group_introduce, gi.group_photo " +
//                "FROM corre c " +
//                "JOIN groupinfo gi ON c.corregroup_id = gi.group_id " +
//                "WHERE c.correuser_id = #{userId}")
//        List<Groupinfo> getAllUserGroups(@Param("userId") Integer userId);
//

    @Select(
            "SELECT gi.group_id, gi.group_name, gi.group_introduce, gi.group_photo " +
            "FROM corre c " +
            "JOIN groupinfo gi ON c.corregroup_id = gi.group_id " +
            "WHERE c.correuser_id = #{userId}")
    List<Groupinfo> getAllUserGroups(@Param("userId") Integer userId);


    // 获取好友分组信息

    @Select("SELECT * FROM Friendship WHERE creat_id = #{userId}")
    List<Friendship> getFriendship(@Param("userId") Integer userId);




}
