package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface GroupInfoDao {

        @Select("SELECT * FROM groupinfo WHERE group_id #{group_id}")
        Groupinfo getGroupInfoById(@Param("group_id") Integer group_id);
    }

