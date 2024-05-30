package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.entity.Corre;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UpdateGroupInfoDao {

    @Update("UPDATE groupInfo SET group_introduce = #{group_introduce} WHERE group_id = #{group_id} AND group_hostid = #{group_hostid}")
    int updateGroupIntroduce(String group_introduce, Integer group_id, Integer group_hostid);//修改群聊简介

    @Select("SELECT * FROM groupinfo WHERE group_id=#{group_id}")
    Groupinfo getGroupInfo(@Param("group_id") Integer group_id);//获取群聊简介

    @Select("SELECT * FROM corre WHERE correuser_id=#{correuser_id} AND corregroup_id=#{corregroup_id}")
    Corre getcorreInfo(@Param("correuser_id") Integer correuser_id, @Param("corregroup_id") Integer corregroup_id);
}
