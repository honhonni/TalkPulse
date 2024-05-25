package cn.edu.ncu.talkpulse.group.dao;

import cn.edu.ncu.talkpulse.group.entity.Grouprecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GroupRecordDao {

    @Insert("insert into grouprecord(grouprecord_content, grouprecord_time, grouprecord_senderid, grouprecord_groupid, grouprecord_type) "
            + "values(#{grouprecord_content}, #{grouprecord_time}, #{grouprecord_senderid}, #{grouprecord_groupid}, #{grouprecord_type})")
    @Options(useGeneratedKeys = true, keyProperty = "grouprecord_id")
    int addGroupRecord(Grouprecord grouprecord);

    @Update("update grouprecord set grouprecord_content = #{content} where grouprecord_id = #{id}")
    int updateContent(@Param("content") String content,@Param("id")Integer id);

    @Select("select count(*) from groupinfo where group_id = #{group_id}")
    int countGroup(int group_id);
}
