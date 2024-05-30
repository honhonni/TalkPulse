package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.GroupApplyWithGroupInfo;
import cn.edu.ncu.talkpulse.group.entity.Groupapply;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface InviteDao {
    //添加群聊申请信息
    @Insert("INSERT INTO groupapply ( groupapply_senderid, groupapply_time, groupapply_groupid, groupapply_hostid, groupapply_introduce)" +
            "VALUES ( #{groupapply_senderid}, #{groupapply_time}, #{groupapply_groupid}, #{groupapply_hostid}, #{groupapply_introduce})")
    int addgroupapply(@Param("groupapply_senderid") int groupapply_senderid,
                      @Param("groupapply_time") LocalDateTime groupapply_time,
                      @Param("groupapply_groupid") int groupapply_groupid,
                      @Param("groupapply_hostid") int groupapply_hostid,
                      @Param("groupapply_introduce") String groupapply_introduce
                      );
    //查询申请消息所在群聊
    @Select("SELECT groupapply_groupid FROM groupapply WHERE groupapply_senderid=#{groupapply_senderid}")
    Integer getid(Integer groupapply_groupid);
    @Select("SELECT * FROM groupinfo WHERE group_id=#{group_id} ")
    Groupinfo getgroupapplyByGroupId(Integer group_id);


    //查询某个用户的群聊信息
    @Select("select *from groupapply where groupapply_senderid=#{groupapply_senderid}")
    Groupapply getgroupapplyBysender(@Param("groupapply_senderid") Integer groupapply_senderid);

    //获取与群主有关的申请信息
    @Select("select * from groupapply where groupapply_hostid=#{groupapply_hostid}")
   List<Groupapply> getgroupapplyByhost(Integer groupapply_hostid);
    
    //群主处理入群申请
    @Update("UPDATE groupapply SET groupapply_status=#{groupapply_status} WHERE  groupapply_groupid=#{groupapply_groupid}  ")
    int updategroupapply(Groupapply groupapply1);

    //删除掉已经处理过的申请
    @Delete("DELETE FROM groupapply WHERE groupapply_status IS NOT NULL ")
    int exitGroupapply( );

    //查询当前用户的需处理的群聊申请
    @Select("SELECT \n" +
            "    ga.groupapply_senderid,\n" +
            "    ga.groupapply_time,\n" +
            "    ga.groupapply_groupid,\n" +
            "    ga.groupapply_hostid,\n" +
            "    ga.groupapply_status,\n" +
            "    ga.groupapply_readstatus,\n" +
            "    g.group_photo,\n" +
            "    g.group_name,\n" +
            "\t\tu.user_name,\n" +
            "    u.user_photo,\n" +
            "    g.group_id\n" +
            "FROM \n" +
            "    groupapply ga\n" +
            "LEFT JOIN \n" +
            "    groupinfo g ON ga.groupapply_groupid = g.group_id\n" +
            "LEFT JOIN \n" +
            "    userinfo u ON ga.groupapply_senderid = u.user_id\n" +
            "WHERE \n" +
            "    ga.groupapply_hostid = #{groupapply_hostid}")
    List<GroupApplyWithGroupInfo> getGroupapplyByReceiverId(Integer groupapply_hostid);

    //查询当前用户发送的好友申请
    @Select("SELECT \n" +
            "    ga.groupapply_senderid, \n" +
            "    ga.groupapply_time, \n" +
            "    ga.groupapply_groupid, \n" +
            "    ga.groupapply_hostid, \n" +
            "    ga.groupapply_status, \n" +
            "    ga.groupapply_readstatus, \n" +
            "    g.group_photo, \n" +
            "    g.group_name, \n" +
            "    g.group_id \n" +
            "FROM \n" +
            "    groupapply ga \n" +
            "LEFT JOIN \n" +
            "    groupinfo g \n" +
            "ON \n" +
            "    ga.groupapply_groupid = g.group_id\n" +
            "WHERE \n" +
            "    ga.groupapply_senderid = #{groupapply_senderid}")
    List<GroupApplyWithGroupInfo> getGroupAppliesBySenderId(@Param("groupapply_senderid") Integer groupapplySenderId);

    //查询后设置已读
    @Update("update groupapply set groupapply_readstatus=true where groupapply_hostid=#{grouapply_hostid}")
    void markGroupapplyAsRead(Integer groupapply_hostid);

}
