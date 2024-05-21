package cn.edu.ncu.talkpulse.group.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;

@Mapper
public interface UserApplyIntoDao {
    @Insert("insert into groupapply(groupapply_senderid,groupapply_time,groupapply_groupid,groupapply_introduce) values (#{groupapply_senderid},#{groupapply_time},#{groupapply_groupid},#{groupapply_introduce})")
   int UserApplyinto(Integer groupapply_senderid, LocalDateTime groupapply_time,Integer groupapply_groupid,String groupapply_introduce);
    @Select("select groupapply_senderid,groupapply_time,groupapply_introduce,groupapply_status,groupapply_readstatus from groupapply where groupapply_groupid=#{groupapply_groupid} AND groupapply_hostid=#{groupapply_hostid}")
    int hostset(Integer groupapply_groupid,Integer groupapply_hostid);
}
