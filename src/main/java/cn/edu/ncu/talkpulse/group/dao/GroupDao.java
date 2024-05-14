package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.groupinfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GroupDao {
    @Select("select group_id,group_name,group_introduce,group_hostid from Groupinfo where group_id=#{id}")
    public List<groupinfo> selectAll(int group_id);

    @Insert("INSERT INTO groupcalidation (groupvalidation_id,groupvalidation_senderid,groupvalidation_receiverid,groupvalidation_groupid,groupvalidation_status,groupvalidation_readstatus,groupvalidation_time)"+
            "VALUES(#{groupvalidation_senderid},#{groupvalidation_receiverid},#{groupvalidation_groupid})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int applyForGroup(@Param("groupvalidation_groupid") int groupvalidation_groupid, @Param("groupvalidation_receiverid") int groupvalidation_receiverid);//（用户）申请入群方法

    @Update("UPDATE groupcalidation SET groupcalidation_readstatus='1' WHERE groupvalidation_receiverid = #{groupvalidation_receiverid} AND groupvalidation_groupId = #{groupvalidation_groupId} AND  group_hostid = #{group_hostid}")
    int readApplication(@Param("groupvalidation_receiverid") int groupvalidation_receiverid, @Param("groupvalidation_groupId") int groupvalidation_groupId, @Param("group_hostid") int group_hostid);//(群主接收入群申请)
    @Update("UPDATE groupcalidation SET groupvalidation_status = '1' WHERE groupvalidation_receiverid = #{groupvalidation_receiverid} AND groupvalidation_groupId = #{groupvalidation_groupId} AND  group_hostid = #{group_hostid}")
    int approveApplication(@Param("groupvalidation_receiverid") int groupvalidation_receiverid, @Param("groupvalidation_groupId") int groupvalidation_groupId, @Param("group_hostid") int group_hostid);//群主同意了进群申请

    // 如果需要拒绝申请，可能还需要另一个方法
    @Update("UPDATE groupcalidation SET groupvalidation_status = '0' WHERE groupvalidation_receiverid = #{groupvalidation_receiverid} AND groupvalidation_groupId = #{groupvalidation_groupId} AND  group_hostid = #{group_hostid}")
    int rejectApplication(@Param("groupvalidation_receiverid") int groupvalidation_receiverid, @Param("groupvalidation_groupId") int groupvalidation_groupId, @Param("group_hostid") int group_hostid);//群主拒绝了进群申请

    @Delete("DELETE FROM corre WHERE correuser_id = #{correuser_id} AND corregroup_id = #{corregroup_id}")
    int removeMemberFromGroup(@Param("correuser_id") int correuser_id, @Param("corregroup_id") int corregroup_id);//群主踢出成员

    @Delete("DELETE FROM corre WHERE correuser_id = #{correuser_id}")
    int disbandGroup(@Param("correuser_id") int correuser_id);//把所有人踢出去删除群聊
}
