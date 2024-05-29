package cn.edu.ncu.talkpulse.friends.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;


@Mapper
public interface RecordDao {
    // 查询后设置已读
    @Update("INSERT INTO record " +
            "(record_content, record_time, record_senderid, record_recipientid, record_readstatus) " +
            "VALUES (#{record_content}, #{record_time}, #{record_senderid}, #{record_recipientid}, 0)")
    void insert(@Param("record_content")String record_content, @Param("record_time") LocalDateTime record_time, @Param("record_senderid")Integer record_senderid, @Param("record_recipientid") Integer record_recipientid);
}
