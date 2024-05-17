package cn.edu.ncu.talkpulse.friends.service.impl;

import cn.edu.ncu.talkpulse.friends.dao.ValidationDao;
import cn.edu.ncu.talkpulse.friends.entity.Validation;
import cn.edu.ncu.talkpulse.friends.service.ValidationService;
import cn.edu.ncu.talkpulse.friends.service.WebSocketServer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private WebSocketServer webSocketServer;

    @Autowired
    private ValidationDao validationDao;

    @Autowired
    private HttpServletRequest request;

    // 发送添加好友申请
    @Override
    public Boolean sendValidation(Integer uid, Integer friendId) {
        if(uid==friendId) return false;
        Validation oldValidation = validationDao.getValidationById(uid, friendId);
        int res;
        if(oldValidation!=null){
            oldValidation.setValidation_time(LocalDateTime.now());
            res = validationDao.updateValidation(oldValidation);// 更新时间
        }else{
            Validation validation = new Validation();
            validation.setValidation_senderid(uid);
            validation.setValidation_receiverid(friendId);
            validation.setValidation_status(0);
            validation.setValidation_readstatus(0);
            validation.setValidation_time(LocalDateTime.now());
            res = validationDao.addValidation(validation);
        }

        if(res==0) return false;
        else{
            webSocketServer.sendToUser(friendId,"您有新的好友请求");
            return true;
        }
    }

    // 获取当前用户接受到的好友申请
    @Override
    public List<Validation> getValidation(Integer uid) {
        List<Validation> validations = validationDao.getValidationsByReceiverId(uid);
        validationDao.markValidationsAsRead(uid);// 设置已读
        return validations;
    }


    // 同意添加好友申请后：
    // 1、先查询用户的默认分组（我的好友）的分组id
    // 2、将被申请者添加进入申请者的默认分组
    // 3、被申请者也要将申请者添加进入默认分组
}
