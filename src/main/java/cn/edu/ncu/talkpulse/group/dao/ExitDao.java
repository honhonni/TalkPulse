package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.Corre;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExitDao {
    @Delete("DELETE FROM corre WHERE correuser_id = #{correuser_id} AND corregroup_id = #{corregroup_id}")
    int exitGroup(Integer correuser_id, Integer corregroup_id);//成员退出群聊
    @Delete("DELETE FROM Groupinfo WHERE group_hostid=#{group_hostid}，group_id=#{group_id}")
    int deleteGroup(Integer group_hostid,Integer group_id);//删除群聊
    @Delete("DELETE FROM corre WHERE corregroup_id=#{corregroup_id}")
    int deleteGroupId(Integer corregroup_id);

    //群主踢出群成员
    @Delete("DELETE FROM corre\n" +
            "WHERE correuser_id = 123456\n" +
            "AND corregroup_id = 32121311\n" +
            "AND EXISTS (\n" +
            "    SELECT 1\n" +
            "    FROM groupinfo\n" +
            "    WHERE groupinfo.group_id = corre.corregroup_id\n" +
            "    AND groupinfo.group_hostid = 123\n" +
            ")")
    int kickmember(Integer correuser_id,Integer corregroup_id,Integer group_hostid);

    //找出群聊的群主
//    @Select("SELECT group_hostid form Groupinfo where group_id=#{group_id}")
//    static Integer judgeHost(Integer group_id);
    //根据群聊的群主查询群聊列表
    @Select("SELECT * FROM Groupinfo WHERE group_hostid=#{group_hostid}")
    Groupinfo selecthost(Integer group_hostid);
    @Select("SELECT corregroup_id FROM corre WHERE correuser_id=#{group_userid}")
    List<Corre> selectid(Integer group_userid);
}
