package cn.edu.ncu.talkpulse.group.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface InviteDao {
    @Insert("insert into groupvalidation(groupvalidation_id,groupvalidation_senderid,groupvalidation_receiverid,groupvalidation_groupid,groupvalidation_time)values(#{groupvalidation_id},#{groupvalidation_senderid},#{groupvalidation_receiverid},#{groupvalidation_groupid},#{groupvalidation_time})")
    int invite(Integer groupvalidation_id, Integer groupvalidation_senderid,Integer groupvalidation_receiverid, Integer groupvalidation_groupid, LocalDateTime groupvalidation_time);
}
