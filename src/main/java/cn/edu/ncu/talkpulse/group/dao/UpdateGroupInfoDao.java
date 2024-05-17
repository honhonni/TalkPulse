package cn.edu.ncu.talkpulse.group.dao;

import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UpdateGroupInfoDao {

    @Delete("DELETE FROM Grouinfo WHERE group_id = #{group_id} AND group_introduce = #{group_introduce} AND group_hostid=#{group_hostid}")
    int deleteIntroduce(Integer group_id,String group_introduce,Integer group_hostid);//删除群聊简介

    @Insert("insert into Groupinfo(group_id,group_introduce,group_hostid)VALUES (#{group_id},#{group_introduce},#{group_hostid})")
    int addIntroduce(Integer group_id,String group_introduce,Integer group_hostid);


}
