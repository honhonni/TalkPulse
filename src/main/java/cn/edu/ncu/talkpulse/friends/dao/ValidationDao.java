package cn.edu.ncu.talkpulse.friends.dao;

import cn.edu.ncu.talkpulse.friends.entity.Validation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ValidationDao {

    // 添加好友申请信息
    @Insert("INSERT INTO validation " +
            "(validation_senderid, validation_receiverid, validation_status, validation_readstatus, validation_time) " +
            "VALUES (#{validation_senderid}, #{validation_receiverid}, #{validation_status}, #{validation_readstatus}, #{validation_time})")
    @Options(useGeneratedKeys = true, keyProperty = "validation_id")
    int addValidation(Validation validation);

    // 查询申请消息
    @Select("select * from validation where validation_senderid = #{senderid} and validation_receiverid = #{receiverid}")
    Validation getValidationByUserId(@Param("senderid") Integer senderid, @Param("receiverid")Integer receiverid);

    @Select("select * from validation where validation_id = #{id}")
    Validation getValidationById(@Param("id") Integer id);

    // 更新申请
    @Update("UPDATE validation SET " +
            "validation_senderid = #{validation_senderid}, " +
            "validation_receiverid = #{validation_receiverid}, " +
            "validation_status = #{validation_status}, " +
            "validation_readstatus = #{validation_readstatus}, " +
            "validation_time = #{validation_time} " +
            "WHERE validation_id = #{validation_id}")
    int updateValidation(Validation validation);

    // 查询当前用户的被添加好友申请
    @Select("select * from validation where validation_receiverid = #{uid}")
    List<Validation> getValidationsByReceiverId(Integer uid);

    // 查询当前用户发送的好友申请
    @Select("select * from validation where validation_senderid = #{uid}")
    List<Validation> getValidationsBySenderId(Integer uid);

    // 查询后设置已读
    @Update("update validation set validation_readstatus = 1 where validation_receiverid = #{uid}")
    void markValidationsAsRead(Integer uid);


}
