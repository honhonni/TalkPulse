package cn.edu.ncu.talkpulse.account.service;

public interface UpdatePwdService {


    Boolean checkpwd(Integer userId,  String userPwd);
    Boolean newpwd(Integer userId,  String userPwd,  String usernewPwd);
}
