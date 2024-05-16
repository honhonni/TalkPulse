package cn.edu.ncu.talkpulse.account.service.impl;

import cn.edu.ncu.talkpulse.account.dao.UpdateInfoDao;
import cn.edu.ncu.talkpulse.account.dao.UpdatePwdDao;
import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.account.service.UpdateInfoService;
import cn.edu.ncu.talkpulse.account.service.UpdatePwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service("UpdatePwdService")
public class UpdatePwdServiceImpl implements UpdatePwdService {
    @Autowired
    private UpdatePwdDao updatePwdDao;

    @Override
    public Boolean newpwd(Integer userId,  String userPwd,  String usernewPwd) {
        String md5Pwd = DigestUtils.md5DigestAsHex((userPwd).getBytes());
        String md5Pwd2 = DigestUtils.md5DigestAsHex((usernewPwd).getBytes());
        int num=updatePwdDao.updatepwd(userId,md5Pwd,md5Pwd2);
        if (num>0)
            return true;
        else
            return false;
    }

    @Override
    public Boolean checkpwd(Integer userId,  String userPwd) {
        String md5Pwd = DigestUtils.md5DigestAsHex((userPwd).getBytes());
        UserInfo userInfo = updatePwdDao.checkUser(userId, md5Pwd);
        if(userInfo!=null){
            return true;
        }
        else
            return false;
    }
}
