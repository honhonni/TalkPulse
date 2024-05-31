package cn.edu.ncu.talkpulse.account.dao;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.dto.ChatWindows;
import cn.edu.ncu.talkpulse.friends.entity.Record;
import cn.edu.ncu.talkpulse.group.entity.Corre;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.entity.Grouprecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatWindowsDao {
    @Select("SELECT * FROM record WHERE record_senderid = #{uid} OR record_recipientid = #{uid} ORDER BY record_time DESC")
    public List <Record> ChatUserWindows(Integer uid);
    @Select("SELECT *\n" +
            "FROM grouprecord\n" +
            "WHERE grouprecord_groupid IN (SELECT corregroup_id FROM corre WHERE correuser_id = #{uid})\n" +
            "ORDER BY grouprecord_time DESC;")
    public List <Grouprecord> ChatGroupWindows(Integer uid);
    @Select("SELECT * FROM groupinfo WHERE group_id = #{gid}")
    public Groupinfo gruop_select (Integer gid);
    @Select("select * from userinfo where user_id = #{uid}")
    public UserInfo user_select(Integer uid);
    @Select("select * from corre  where correuser_id= #{uid} AND corregroup_id=#{gid}")
    public Corre corre_select(Integer uid, Integer gid);
    @Select("SELECT COUNT(*) AS unread_count\n" +
            "FROM record\n" +
            "WHERE (record_recipientid = #{recipientid} AND record_senderid = #{senderid} AND record_readstatus = 0) ")
    public Integer unread_num(Integer recipientid,Integer senderid);
}
