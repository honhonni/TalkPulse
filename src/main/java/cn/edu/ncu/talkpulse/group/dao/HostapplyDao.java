package cn.edu.ncu.talkpulse.group.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface HostapplyDao {
    @Select("SELECT groupapply_id,groupapply_senderid,groupapply_time,groupapply_groupid,groupapply_hostid,groupapply_introduce,groupapply_status,groupapply_readstatus from groupapply where groupapply_hostid=#{groupapply_hostid} AND groupapply_id=#{groupapply_id}")
    int hostapply(Integer groupapply_groupid,Integer groupapply_hostid);
    @Update("UPDATE groupapply SET groupapply_status=#{groupapply_status} AND groupapply_readstatus=#{groupapply_readstatus} where groupapply_hostid=#{groupapply_hostid} AND groupapply_id=#{groupapply_id}")
    int hostset(Boolean groupapply_status,Boolean groupapply_readstatus,Integer groupapply_groupid,Integer groupapply_hostid);
}
