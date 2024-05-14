package cn.edu.ncu.talkpulse.account.dao;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountDao {
    @Select("select * from userinfo where user_id = #{uid}")
    public UserInfo findUserById(Integer uid);

    @Insert("insert into  userinfo(user_id, user_name, user_pwd) values (#{user_id}, #{user_name}, #{user_pwd})")
    int addUser(UserInfo user);

    @Select("select * from userinfo where user_id = #{userId} and user_pwd = #{user_pwd}")
    UserInfo checkUser(@Param("userId") Integer userId, @Param("user_pwd") String user_pwd);
}
