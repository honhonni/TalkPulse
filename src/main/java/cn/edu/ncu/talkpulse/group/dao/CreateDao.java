package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface CreateDao {

    @Insert("insert into groupinfo(group_id,group_name,group_introduce,group_hostid)values(#{group_id},#{group_name},#{group_introduce},#{group_hostid})")
    int CreateGroup(Groupinfo groupinfo);
    @Select("select * from groupinfo where group_id=#{gid}")
    public Groupinfo findGroupGId(int gid);
}
