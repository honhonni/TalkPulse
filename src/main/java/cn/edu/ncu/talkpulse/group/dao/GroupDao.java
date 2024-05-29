package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.entity.Groupvalidation;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface GroupDao {

    //获取群聊信息
    @Select("select group_id,group_name,group_introduce,group_hostid from Groupinfo where group_id=#{id}")
    public List<Groupinfo> selectAll(int group_id);

    @Insert("INSERT INTO groupvalidation (groupvalidation_senderid, groupvalidation_receiverid, groupvalidation_groupid, groupvalidation_status, groupvalidation_readstatus, groupvalidation_time) " +
            "VALUES (#{senderid}, #{receiverid}, #{groupid}, #{status}, #{readstatus}, #{time})")
    int inviteUserToGroup(@Param("senderid") int senderid,
                          @Param("receiverid") int receiverid,
                          @Param("groupid") int groupid,
                          @Param("status") boolean status,
                          @Param("readstatus") boolean readstatus,
                          @Param("time") LocalDateTime time);//邀请进入群聊
    @Update("UPDATE groupapply SET groupapply_status = #{status},groupapply_processed = 1 WHERE groupapply_id = #{applyId}")
    int GroupApply(@Param("applyId") int applyId, @Param("status") boolean status);//群主接收入群申请
    @Insert("INSERT INTO corre (correuser_id, corregroup_id) VALUES (#{userId}, #{groupId})")
    int addUserToGroup(@Param("userId") int userId, @Param("groupId") int groupId);//用户成为群聊成员
    // 处理入群申请：接受申请
    @Update("UPDATE groupapply SET groupapply_status = #{status}, groupapply_readstatus = #{readStatus} " +
            "WHERE groupapply_id = #{applyId}")
    int acceptGroupApply(@Param("applyId") int applyId,
                         @Param("status") boolean status,
                         @Param("readStatus") boolean readStatus);

    // 处理入群申请：拒绝申请
    @Update("UPDATE groupapply SET groupapply_status = #{status}, groupapply_readstatus = #{readStatus} " +
            "WHERE groupapply_id = #{applyId}")
    int rejectGroupApply(@Param("applyId") int applyId,
                         @Param("status") boolean status,
                         @Param("readStatus") boolean readStatus);

    @Delete("DELETE FROM corre WHERE correuser_id = #{correuser_id} AND corregroup_id = #{corregroup_id}")
    int removeMemberFromGroup(@Param("correuser_id") int correuser_id, @Param("corregroup_id") int corregroup_id);//群主踢出成员

    @Delete("DELETE FROM corre WHERE corregroup_id = #{corregroup_id}")
    int disbandGroup(@Param("corregroup_id") int corregroup_id);//把所有人踢出去删除群聊
    // 保存邀请验证记录
    @Insert("INSERT INTO groupvalidation (groupvalidation_senderid, groupvalidation_receiverid, groupvalidation_groupid, groupvalidation_status, groupvalidation_readstatus, groupvalidation_time) " +
            "VALUES (#{senderId}, #{receiverId}, #{groupId}, #{status}, #{readStatus}, #{time})")
    int saveInvitationValidation(@Param("senderId") int senderId,
                                 @Param("receiverId") int receiverId,
                                 @Param("groupId") int groupId,
                                 @Param("status") boolean status,
                                 @Param("readStatus") boolean readStatus,
                                 @Param("time") LocalDateTime time);

    // 检查邀请状态
    @Select("SELECT groupvalidation_status FROM groupvalidation WHERE groupvalidation_senderid = #{senderId} AND groupvalidation_receiverid = #{receiverId} AND groupvalidation_groupid = #{groupId}")
    boolean checkInvitationStatus(@Param("senderId") int senderId,
                                  @Param("receiverId") int receiverId,
                                  @Param("groupId") int groupId);

    // 更新邀请状态（例如，当受邀用户接受邀请时）
    @Update("UPDATE groupvalidation SET groupvalidation_status = #{newStatus}, groupvalidation_readstatus = #{newReadStatus} WHERE groupvalidation_senderid = #{senderId} AND groupvalidation_receiverid = #{receiverId} AND groupvalidation_groupid = #{groupId}")
    int updateInvitationStatus(@Param("senderId") int senderId,
                               @Param("receiverId") int receiverId,
                               @Param("groupId") int groupId,
                               @Param("newStatus") boolean newStatus,
                               @Param("newReadStatus") boolean newReadStatus);
    // 获取用户收到的所有入群邀请
    @Select("SELECT * FROM groupvalidation WHERE groupvalidation_receiverid = #{userId}")
    @Results({
            @Result(column = "groupvalidation_id", property = "groupvalidationId"),
            @Result(column = "groupvalidation_senderid", property = "senderId"),
            @Result(column = "groupvalidation_receiverid", property = "receiverId"),
            @Result(column = "groupvalidation_groupid", property = "groupId"),
            @Result(column = "groupvalidation_status", property = "status"),
            @Result(column = "groupvalidation_readstatus", property = "readStatus"),
            @Result(column = "groupvalidation_time", property = "time")
    })
    List<Groupvalidation> getInvitationsForUser(@Param("userId") int userId);
    @Update("UPDATE groupvalidation SET groupvalidation_readstatus = #{readStatus} WHERE groupvalidation_id = #{invitationId}")
    int updateInvitationReadStatus(@Param("invitationId") int invitationId, @Param("readStatus") boolean readStatus);//跟新邀请的状态

    // 用户接受入群邀请
    @Update("UPDATE groupvalidation SET groupvalidation_status = #{status}, groupvalidation_readstatus = #{readStatus}, groupvalidation_time = #{time} " +
            "WHERE groupvalidation_id = #{invitationId} AND groupvalidation_receiverid = #{userId}")
    int acceptInvitation(@Param("invitationId") int invitationId,
                         @Param("userId") int userId,
                         @Param("status") boolean status,
                         @Param("readStatus") boolean readStatus,
                         @Param("time") LocalDateTime time);

    // 用户拒绝入群邀请
    @Update("UPDATE groupvalidation SET groupvalidation_status = #{status}, groupvalidation_readstatus = #{readStatus}, groupvalidation_time = #{time} " +
            "WHERE groupvalidation_id = #{invitationId} AND groupvalidation_receiverid = #{userId}")
    int rejectInvitation(@Param("invitationId") int invitationId,
                         @Param("userId") int userId,
                         @Param("status") boolean status,
                         @Param("readStatus") boolean readStatus,
                         @Param("time") LocalDateTime time);
    // 根据群聊ID获取群组信息
    @Select("SELECT group_id, group_name, group_introduce, group_hostid FROM groupinfo WHERE group_id = #{groupId}")
    Groupinfo getGroupInfoById(@Param("groupId") int groupId);
    @Select("SELECT g.group_id, g.group_name, g.group_introduce, g.group_hostid, u.user_id, u.username " +
            "FROM groupinfo g " +
            "JOIN corre c ON g.group_id = c.corregroup_id " +
            "JOIN user u ON c.correuser_id = u.user_id " +
            "WHERE g.group_id = #{groupId}")
    List<Map<String, Object>> getGroupMembers(@Param("groupId") int groupId);//获取群成员列表

    @Insert({
            "<script>",
            "INSERT INTO groupinfo (group_name, group_introduce, group_hostid) ",
            "VALUES (#{groupInfo.group_name}, #{groupInfo.group_introduce}, #{groupInfo.group_hostid})",
            "</script>"
    })
    @Options(useGeneratedKeys = true, keyProperty = "group_id", keyColumn = "group_id")
    int createGroup(@Param("groupInfo") Groupinfo groupInfo);//创建了一个新的群聊
    @Update("UPDATE groupinfo SET group_introduce = #{newIntroduce} WHERE group_id = #{groupId}")
    int updateGroupIntroduce(@Param("groupId") int groupId, @Param("newIntroduce") String newIntroduce);//修改群聊简介
    @Select("SELECT group_introduce FROM groupinfo WHERE group_id = #{groupId}")
    String getGroupIntroduceById(@Param("groupId") int groupId);//获取群聊简介


}
