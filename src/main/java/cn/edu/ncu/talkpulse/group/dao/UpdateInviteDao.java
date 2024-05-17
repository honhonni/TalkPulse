package cn.edu.ncu.talkpulse.group.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface UpdateInviteDao {
    @Insert("insert into groupvalidation(groupvalidation_id,groupvalidation_senderid,groupvalidation_receiverid,groupvalidation_groupid,groupvalidation_status,groupvalidation_readstatus,groupvalidation_time)values (#{groupvalidation_id},#{groupvalidation_senderid},#{groupvalidation_receiverid},#{groupvalidation_groupid},#{groupvalidation_status},#{groupvalidation_readstatus},#{groupvalidation_time})")
    int updateinvite(Integer groupvalidation_id, Integer groupvalidation_senderid, Integer groupvalidation_receiverid, Integer groupvalidation_groupid, String groupvalidation_status, String groupvalidation_readstatus, LocalDateTime groupvalidation_time);
}
