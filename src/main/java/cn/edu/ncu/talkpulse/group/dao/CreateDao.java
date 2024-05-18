package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CreateDao {

    @Insert("insert into groupinfo(group_id,group_name,group_introduce,group_hostid)values(#{group_id},#{group_name},#{group_introduce},#{group_hostid})")
    int CreateGroup(Groupinfo groupinfo);
    @Select("select * from groupinfo where group_id=#{gid}")
    public Groupinfo findGroupGId(int gid);
    @Update("UPDATE groupinfo SET group_photo=#{group_photo} WHERE group_id=#{group_id}")
    int upphoto(Integer group_id,String group_photo);
}
