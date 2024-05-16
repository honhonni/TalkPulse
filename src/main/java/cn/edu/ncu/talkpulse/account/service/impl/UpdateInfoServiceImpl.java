package cn.edu.ncu.talkpulse.account.service.impl;

import cn.edu.ncu.talkpulse.account.dao.UpdateInfoDao;
import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.account.service.UpdateInfoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
@Service("UpdateInfoService")
public class UpdateInfoServiceImpl implements UpdateInfoService

{
    @Autowired
    private UpdateInfoDao updateInfoDao;

    @Override
    public Boolean updatetonew(Integer uid,String uname,String ugender,Integer uage, String uintroduce) {

        int num=updateInfoDao.updateinfo(uid,uname,ugender,uage,uintroduce);
        if (num>0)
            return true;
        else
            return false;
    }


}
