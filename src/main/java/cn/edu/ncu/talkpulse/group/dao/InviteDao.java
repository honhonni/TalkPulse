package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.entity.groupapply;
import cn.edu.ncu.talkpulse.group.entity.groupvalidation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface InviteDao {
    @Insert("insert into groupvalidation(groupvalidation_senderid,groupvalidation_receiverid,groupvalidation_groupid,groupvalidation_time)values(#{groupvalidation_senderid},#{groupvalidation_receiverid},#{groupvalidation_groupid},#{groupvalidation_time})")
    int invite( Integer groupvalidation_senderid,Integer groupvalidation_receiverid, Integer groupvalidation_groupid, LocalDateTime groupvalidation_time);
    @Select("SELECT group_photo,group_name FROM groupinfo WHERE group_id=#{group_id}")
    Groupinfo getgourp(Integer group_id);
    @Select("SELECT * FROM groupapply WHERE groupapply_senderid=#{groupapply_sendider},groupapply_hostid=#{groupapply_hostid},groupapply_groupid")
    groupapply getgroupapply(Integer groupapply_senderid, Integer groupapply_hostid, Integer groupapply_groupid);
    @Select("SELECT user_photo FROM userinfo WHERE user_id=#{user_id}")
    String getuser(Integer user_id);
}
