package cn.edu.ncu.talkpulse.group.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

@Mapper
public interface UpdateInviteDao {
    @Update("UPDATE groupvalidation SET groupvalidation_status,groupvalidation_readstatus,groupvalidation_tim=(#{groupvalidation_status},#{groupvalidation}))values (#{groupvalidation_senderid},#{groupvalidation_receiverid},#{groupvalidation_groupid},#{groupvalidation_status},#{groupvalidation_readstatus},#{groupvalidation_time})")
    int updateinvite( Integer groupvalidation_senderid, Integer groupvalidation_receiverid, Integer groupvalidation_groupid, String groupvalidation_status, String groupvalidation_readstatus, LocalDateTime groupvalidation_time);
}
