package cn.edu.ncu.talkpulse.group.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CorreDao {
    @Insert("insert into corre( correuser_id ,corregroup_id)values(#{correuser_id},#{corregroup_id})")
    int addcorre(Integer correuser_id,Integer corregroup_id);
}
