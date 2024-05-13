package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupDao {
    @Select("select groupId,groupName,groupIntroduce,groupHostid,from Groupinfo where groupId=#{id}")
    public List<Groupinfo> selectAll(int id);
}
