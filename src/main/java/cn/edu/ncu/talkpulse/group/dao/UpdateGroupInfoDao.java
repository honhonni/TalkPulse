package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UpdateGroupInfoDao {

    @Update("UPDATE GroupInfo SET group_introduce = #{group_introduce} WHERE group_id = #{group_id} AND group_hostid = #{group_hostid}")
    int updateGroupIntroduce(String group_introduce, Integer group_id, Integer group_hostid);//修改群聊简介

    @Select("SELECT group_introduce FROM groupinfo,corre WHERE group_id=corregroup_id=#{group_id} AND correuser_id=#{correuser_id}")
    Groupinfo getGroupInfo(@Param("group_id") Integer group_id, @Param("correuser_id") Integer correuser_id);//获取群聊简介

}
