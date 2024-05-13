package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.groupinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupDao {
    @Select("select group_id,group_name,group_introduce,group_hostid from Groupinfo where group_id=#{id}")
    public List<groupinfo> selectAll(int group_hostid);
}
