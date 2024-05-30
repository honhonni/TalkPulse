package cn.edu.ncu.talkpulse.friends.dao;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.friends.entity.Friend;
import cn.edu.ncu.talkpulse.friends.entity.Friendship;
import cn.edu.ncu.talkpulse.friends.entity.Record;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.entity.Grouprecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

// 好友信息DAO接口
@Mapper
public interface FriendDao {

//    // 根据关键词查找聊天记录
//    @Select({
//            "(SELECT 'personal' AS record_type,",
//            "    r.record_content,",
//            "    r.record_time,",
//            "    r.record_senderid,",
//            "    r.record_recipientid",
//            " FROM",
//            "    Record r",
//            " WHERE",
//            "    (r.record_content LIKE CONCAT('%', #{keyword}, '%')",
//            "    AND r.record_senderid = #{userId}",
//            "    AND r.record_recipientid = #{userId})",
//            "LIMIT 10 ",
//            "UNION ALL ",
//            "(SELECT 'group' AS record_type,",
//            "    gr.grouprecord_content AS record_content,",
//            "    gr.grouprecord_time AS record_time,",
//            "    gr.grouprecord_senderid AS record_senderid,",
//            "    NULL AS record_recipientid",
//            " FROM",
//            "    GroupRecord gr",
//            " WHERE",
//            "    gr.grouprecord_content LIKE CONCAT('%', #{keyword}, '%')",
//            "    AND gr.grouprecord_senderid = #{userId}",
//            "LIMIT 10"
//
//    })
//    List<allRecords> searchRecordsByKeywordAndGroup(
//            @Param("keyword") String keyword,
//            @Param("userId") Integer userId
//    );

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


    @Select(
            "SELECT gi.* " +
                    " FROM corre c ,groupinfo gi" +
                    " WHERE c.corregroup_id = gi.group_id " +
                    " AND c.correuser_id = #{userId}")
    List<Groupinfo> getAllUserGroups(@Param("userId") Integer userId);


    // 获取好友分组信息

    @Select("SELECT * FROM Friendship WHERE creat_id = #{userId}")
    List<Friendship> getFriendship(@Param("userId") Integer userId);

    //根据传入uid返回自己与该uid私聊消息

    // 获取a用户给b用户设置的好友分组信息
    @Select("SELECT s.* FROM friendship s, friend f WHERE f.given_friendshipid = s.friendship_id AND f.firstid = #{myId} AND f.secondid = #{userId}")
    Friendship getFriendshipById(@Param("myId") Integer myId, @Param("userId") Integer userId);

    
    @Select( "SELECT * FROM record WHERE (record_senderid = #{userId} AND record_recipientid = #{otherUserId}) OR (record_senderid = #{otherUserId} AND record_recipientid = #{userId})")
    List<Record> getPrivateMessages( @Param("otherUserId") Integer otherUserId,@Param("userId") Integer userId);

    //根据传入gid返回自己与该gid的群聊消息

    @Select("SELECT * FROM grouprecord WHERE grouprecord_groupid = #{groupId} AND EXISTS ( SELECT 1 FROM corre WHERE correuser_id = #{userId} AND corregroup_id = #{groupId})")
    List<Grouprecord> getGroupMessages(@Param("groupId") Integer groupId, @Param("userId") Integer userId);
}
