package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.Groupapply;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface InviteDao {
    //添加群聊申请信息
    @Insert("INSERT INTO groupapply(groupapply_senderid, groupapply_time, groupapply_groupid, groupapply_hostid, groupapply_introduce)\n" +
            "VALUES(#{groupapply_senderid},#{groupapply_time},#{groupapply_groupid},#{groupapply_hostid},#{groupapply_introduce})")
    int addgroupapply (Integer groupapply_senderid, LocalDateTime groupapply_time,Integer groupapply_groupid,Integer groupapply_hostid,String groupapply_introduce);
    
    //用户查询申请消息
    @Select("select *from groupapply where groupapply_senderid=#{groupapply_senderid} ")
    Groupapply getgroupapplyByUserId(Integer groupapply_senderid);

    //查询第几条申请消息
    @Select("select *from groupapply where groupapply_id=#{id}")
    Groupapply getgroupapplyById(@Param("id") Integer id);
    
    //群主处理入群申请
    @Update("UPDATE groupapply SET groupapply_status=#{groupapply_status} WHERE groupapply_senderid=#{groupapply_senderid} groupapply_groupid=#{groupapply_groupid} AND groupapply_hostid=#{groupapply_hostid}")
    int updategroupapply(Groupapply groupapply1);

    //查询当前用户的需处理的群聊申请
    @Select("select *from groupapply where groupapply_hostid=#{groupapply_hostid}")
    List<Groupapply> getGroupapplyByReceiverId(Integer groupapply_hostid);

    //查询当前用户发送的好友申请
    @Select("SELECT * FROM groupapply WHERE groupapply_senderid = #{groupapply_senderid}")
    List<Groupapply> getGroupapplyBySenderId(@Param("groupapply_senderid") Integer groupapplySenderId);

    //查询后设置已读
    @Update("update groupapply set groupapply_readstatus=true where groupapply_hostid=#{grouapply_hostid}")
    void markGroupapplyAsRead(Integer groupapply_hostid);
}
