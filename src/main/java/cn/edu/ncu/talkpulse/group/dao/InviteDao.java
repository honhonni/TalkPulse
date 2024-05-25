package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.Groupapply;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InviteDao {
    //添加群聊申请信息
    @Insert("INSERT INTO groupapply"+
            "(groupapply_senderid,groupapply_time,groupapply_groupid,groupapply_hostid,groupapply_introduce,groupapply_status,groupapply_readstatus)"+
            "VALUES (#{groupapply_senderid},#{groupapply_time},#{groupapply_groupid},#{groupapply_hostid},#{groupapply_introduce},#{groupapply_status},#{groupapply_readstatus})")
    @Options(useGeneratedKeys = true,keyProperty = "groupapply_id")
    int addgroupapply (Groupapply groupapply1);
    
    //查询申请消息
    @Select("select *from groupapply where groupapply_senderid=#{senderid}and groupapply_hostid=#{receiverid}")
    Groupapply getgroupapplyByUserId(@Param("senderid")Integer senderid, @Param("receiverid")Integer receiverid);
    
    @Select("select *from groupapply where groupapply_id=#{id}")
    Groupapply getgroupapplyById(@Param("id") Integer id);
    
    //更新申请
    @Update("UPDATE groupapply SET "+
            "groupapply_senderid=#{groupapply_senderid},"+
            "groupapply_time=#{groupapply_time},"+
            "groupapply_groupid=#{groupapply_groupid}"+
            "groupapply_hostid=#{groupapply_hostid}"+
            "groupapply_introduce=#{groupapply_introduce}"+
            "groupapply_status=#{groupapply_status}"+
            "groupapply_readstatus=#{groupapply_readstatus}")
    int updategroupapply(Groupapply groupapply1);

    //查询当前用户的需处理的群聊申请
    @Select("select *from groupapply where groupapply_hostid=#{gid}")
    List<Groupapply> getGroupapplyByReceiverId(Integer gid);

    //查询当前用户发送的好友申请
    @Select("select *from groupapply where groupapply_senderid=#{gid}")
    List<Groupapply>getGroupapplyBySenderId(Integer gid);

    //查询后设置已读
    @Update("update groupapply set groupapply_readstatus=1 where groupally_receiverid=#{gid}")
    void markGroupapplyAsRead(Integer gid);
}
