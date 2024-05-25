package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.Corre;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CorreDao {
    @Insert("insert into corre( correuser_id ,corregroup_id)values(#{correuser_id},#{corregroup_id})")
    int addcorre(Integer correuser_id,Integer corregroup_id);
    @Select("SELECT correuser_id AND corregroup_id FROM corre where correuser_id=#{correuser_id} AND corregroup_id=#{corregroup_id}" )
    Corre ingroup(Integer correuser_id, Integer corregroup_id);

    //获取群聊成员
    @Select("SELECT * FROM corre\n" +
            "JOIN userinfo\n" +
            "WHERE corre.corregroup_id =#{corregroup_id}")
    List<UserInfo> getgroup(Integer corregroup_id);

    // 更新群消息状态
    @Update("update corre set newinform = #{newinform} where correuser_id = #{correuser_id} and corregroup_id = #{corregroup_id}")
    int updateState(Corre c);

    // 批量更新有新的群消息
    @Update({
            "<script>",
            "update corre",
            "set newInform = 1",
            "where correuser_id IN ",
            "<foreach item='id' index='index' collection='ids' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "and corregroup_id = #{gid}",
            "</script>"
    })
    int batchUpdateState(@Param("gid")Integer gid,@Param("ids")List<Integer> ids);

    // 查找某个群的用户
    @Select("select correuser_id from corre where corregroup_id = #{gid} and correuser_id <> #{uid}")
    List<Integer> selectUser(@Param("gid") Integer gid,@Param("uid") Integer uid);
}
