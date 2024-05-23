package cn.edu.ncu.talkpulse.friends.dao;

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
    @Select("SELECT * FROM Friend WHERE firstid = #{user_id}")
    List<Friendship> getFriendsList(@Param("user_id") Integer userId);

    // 获取所在群列表
    @Select("SELECT * FROM GroupMember WHERE user_id = #{user_id}")
    List<Groupinfo> getUserGroups(@Param("user_id") Integer userId);

    // 获取好友分组信息

    @Select("SELECT * FROM Friendship WHERE creat_id = #{userId}")
    List<Friendship> getFriendship(@Param("userId") Integer userId);



}
