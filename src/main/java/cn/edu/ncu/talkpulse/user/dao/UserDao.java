package cn.edu.ncu.talkpulse.user.dao;

import cn.edu.ncu.talkpulse.user.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

// 用户信息DAO接口
@Mapper
public interface UserDao {

    // 根据用户ID查找用户信息
    @Select("SELECT * FROM userinfo WHERE user_id = #{userId}")
    UserInfo findById(@Param("userId") Integer userId);

    // 根据关键词和用户ID查找聊天记录
    @Select("SELECT * FROM Record WHERE (record_content LIKE CONCAT('%', #{keyword}, '%') OR record_senderid = #{userId} OR record_recipientid = #{userId}) AND user_id = #{userId}")
    List<Record> findRecordsByKeyword(@Param("keyword") String keyword, @Param("userId") Integer userId);

    // 更新用户信息
    @Update("UPDATE UserInfo SET user_name = #{user.userName}, user_pwd = #{user.userPwd}, user_gender = #{user.userGender}, user_age = #{user.userAge}, user_introduce = #{user.userIntroduce}, user_photo = #{user.userPhoto} WHERE user_id = #{user.userId}")
    void updateUserInfo(@Param("user") UserInfo user);
}

//// 好友列表DAO接口
//public interface FriendDao {
//    List<User> findAllFriends(Integer userId);
//}
//
//// 好友分组DAO接口
//public interface FriendshipDao {
//    List<Friendship> findAllFriendships();
//    Friendship addFriendship(Friendship friendship);
//}
//
//// 用户所在群组DAO接口
//public interface GroupMemberDao {
//    List<Group> findGroupsByUserId(Integer userId);
//}
