package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.Groupvalidation;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface GroupValidationDao {
    @Update("UPDATE groupvalidation SET groupvalidation_status,groupvalidation_readstatus,groupvalidation_tim=(#{groupvalidation_status},#{groupvalidation}))values (#{groupvalidation_senderid},#{groupvalidation_receiverid},#{groupvalidation_groupid},#{groupvalidation_status},#{groupvalidation_readstatus},#{groupvalidation_time})")
    int updateinvite( Integer groupvalidation_senderid, Integer groupvalidation_receiverid, Integer groupvalidation_groupid, String groupvalidation_status, String groupvalidation_readstatus, LocalDateTime groupvalidation_time);

    //用户邀请好友进入群聊
    @Insert("INSERT INTO groupvalidation ( groupvalidation_senderid, groupvalidation_receiverid,groupvalidation_groupid, groupvalidation_time) VALUES ( #{groupvalidation_senderid}, #{groupvalidation_receiverid}, #{groupvalidation_groupid}, #{groupvalidation_time})")
            int addvalidation(Integer groupvalidation_senderid,Integer groupvalidation_receiverid,Integer groupvalidation_groupid,LocalDateTime groupvalidation_time);
    //被邀请用户处理群聊申请
    @Update("UPDATE groupvalidation SET groupvalidation_status=#{groupvalidation_status} WHERE  groupvalidation_groupid=#{groupvalidation_groupid}")
    Groupvalidation handlegroupvalidation(Groupvalidation groupvalidation);

    //查找被邀请用户的validation表格
    @Select("SELECT * FROM groupvalidation WHERE groupvalidation_id=#{groupvalidation_id}")
    Groupvalidation selectgroupvalidation(Integer groupvalidation_id);

    //查看用户邀请
    @Update("UPDATE groupvalidation SET groupvalidation_readstatus=1 WHERE  groupvalidation_groupid=#{groupvalidation_groupid} ")
    void readvalidation(Integer groupvalidation_grouid);

    //用户处理完成邀请
    @Delete("DELETE FROM groupvalidation WHERE groupvalidation_status IS NOT NULL")
    void deletevalidation( );
}
