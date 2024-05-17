package cn.edu.ncu.talkpulse.group.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExitDao {
    @Delete("DELETE FROM corre WHERE correuser_id = #{correuser_id} AND corregroup_id = #{corregroup_id}")
    int exitGroup(Integer correuser_id, Integer corregroup_id);//群主踢出成员
    @Delete("DELETE FROM Groupinfo WHERE group_id=#{group_id} AND group_name=#{group_name} AND  group_hostid=#{group_hostid}")
    int deleteGroup(Integer group_id,String group_name,Integer group_hostid);
    @Delete("DELETE FROM corre WHERE corregroup_id=#{corregroup_id}")
    int deleteGroupId(Integer corregroup_id);

}
