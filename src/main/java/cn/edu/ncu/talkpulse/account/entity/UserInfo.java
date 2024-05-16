package cn.edu.ncu.talkpulse.account.entity;

// 用户信息表
public class UserInfo {
    // id
    private Integer user_id;

    // 用户名
    private String user_name;

    // 密码
    private String user_pwd;

    // 性别
    private String user_gender;

    // 年龄
    private Integer user_age;

    // 简介
    private String user_introduce;

    // 头像（地址）
    private String user_photo;

    public UserInfo(Integer user_id, String user_name, String user_pwd, String user_gender, Integer user_age, String user_introduce, String user_photo) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_pwd = user_pwd;
        this.user_gender = user_gender;
        this.user_age = user_age;
        this.user_introduce = user_introduce;
        this.user_photo = user_photo;
    }

    public UserInfo(Integer user_id, String user_name, String user_pwd) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_pwd = user_pwd;
    }
    public UserInfo(Integer user_id, String user_name, String user_gender, Integer user_age, String user_introduce, String user_photo) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_gender = user_gender;
        this.user_age = user_age;
        this.user_introduce = user_introduce;
        this.user_photo = user_photo;
    }

    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_pwd='" + user_pwd + '\'' +
                ", user_gender='" + user_gender + '\'' +
                ", user_age=" + user_age +
                ", user_introduce='" + user_introduce + '\'' +
                ", user_photo='" + user_photo + '\'' +
                '}';
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public Integer getUser_age() {
        return user_age;
    }

    public void setUser_age(Integer user_age) {
        this.user_age = user_age;
    }

    public String getUser_introduce() {
        return user_introduce;
    }

    public void setUser_introduce(String user_introduce) {
        this.user_introduce = user_introduce;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }
}
