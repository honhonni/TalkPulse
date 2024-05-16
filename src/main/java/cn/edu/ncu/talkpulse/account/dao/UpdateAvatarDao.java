package cn.edu.ncu.talkpulse.account.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UpdateAvatarDao {
    @Update("UPDATE userinfo SET user_photo=#{uphoto}   WHERE user_id = #{uid}")
    int updateavatar (Integer uid,String uphoto);
}
