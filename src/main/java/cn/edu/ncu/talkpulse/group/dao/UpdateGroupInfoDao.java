package cn.edu.ncu.talkpulse.group.dao;

import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UpdateGroupInfoDao {

    @Update("UPDATE GroupInfo SET group_introduce = #{group_introduce} WHERE group_id = #{group_id} AND group_hostid = #{group_hostid};")
    int updateGroupIntroduce(String group_introduce, Integer group_id, Integer group_hostid);//修改群聊简介




}
