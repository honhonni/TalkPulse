package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.corre;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CorreDao {
    @Insert("insert into corre( correuser_id ,corregroup_id)values(#{correuser_id},#{corregroup_id})")
    int addcorre(Integer correuser_id,Integer corregroup_id);
    @Select("SELECT correuser_id AND corregroup_id FROM corre where correuser_id=#{correuser_id} AND corregroup_id=#{corregroup_id}" )
    corre ingroup(Integer correuser_id, Integer corregroup_id);
}
