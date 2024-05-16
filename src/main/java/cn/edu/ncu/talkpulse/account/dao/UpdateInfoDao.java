package cn.edu.ncu.talkpulse.account.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface UpdateInfoDao {
    @Update("UPDATE userinfo SET user_name=#{uname}, user_gender=#{ugender}, user_age=#{uage}, user_introduce=#{uintroduce}   WHERE user_id = #{uid}")
    int updateinfo (Integer uid,String uname,String ugender,Integer uage, String uintroduce);
}
