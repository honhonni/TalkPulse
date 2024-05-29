package cn.edu.ncu.talkpulse.friends.dao;

import cn.edu.ncu.talkpulse.friends.entity.Record;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RecordDao {

    // 新增消息记录
    @Insert("insert into record(record_content, record_time, record_senderid, record_recipientid, record_readstatus, record_type) "
            + "values (#{record_content}, #{record_time}, #{record_senderid}, #{record_recipientid}, #{record_readstatus}, #{record_type})")
    @Options(useGeneratedKeys = true, keyProperty = "record_id")
    int addRecord(Record record);

    // 设置消息内容
    @Update("update record set record_content = #{content} where record_id = #{id}")
    int updateContent(@Param("content")String content,@Param("id")Integer id);

    // 设置已读好友发送的消息
    @Update("update record set record_readstatus = 1 where record_senderid = #{senderid} and record_recipientid = #{receiverid}")
    int updateReadStatus(@Param("senderid") Integer senderid, @Param("receiverid")Integer receiverid);
}
