package cn.edu.ncu.talkpulse.group.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface UserApplyIntoDao {
    @Insert("insert into groupvalidation(groupapply_sendardid,groupapply_time,groupapply_groupid)values(#{groupapply_sendardid},#{groupapply_time},#{groupapply_groupid})")
   int UserApplyinto(Integer groupapply_sendardid, LocalDateTime groupapply_time,Integer groupapply_groupid);
}
