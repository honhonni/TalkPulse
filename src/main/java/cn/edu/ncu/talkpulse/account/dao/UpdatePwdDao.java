package cn.edu.ncu.talkpulse.account.dao;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UpdatePwdDao {

    @Select("select * from userinfo where user_id = #{userId} and user_pwd = #{user_pwd}")
    UserInfo checkUser(@Param("userId") Integer userId, @Param("user_pwd") String user_pwd);
    @Update("UPDATE userinfo SET user_pwd=#{user_newpwd} WHERE user_id = #{userId} and user_pwd = #{user_pwd}")
    int updatepwd (@Param("userId")Integer userId,@Param("user_pwd") String user_pwd, String user_newpwd);
}
