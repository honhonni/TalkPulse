package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.group.dao.ApplyDao;
import cn.edu.ncu.talkpulse.group.dao.GroupValidationDao;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.entity.Groupvalidation;
import cn.edu.ncu.talkpulse.group.service.GroupValidationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

@Service("Updateinvite")
public class GroupGroupValidationServiceImpl implements GroupValidationService {
    @Autowired
    private GroupValidationDao groupValidationDao;
    @Autowired
    private ApplyDao applyDao;
    @Override
    public Boolean updateinvite(Integer groupvalidation_senderid, HttpSession session, Integer groupvalidation_groupid, String groupvalidation_status, String groupvalidation_readstatus, LocalDateTime groupvalidation_time){
        Integer groupvalidation_receiverid=(Integer) session.getAttribute("user_id");
        int res= groupValidationDao.updateinvite(groupvalidation_senderid,groupvalidation_receiverid,groupvalidation_groupid,groupvalidation_status,groupvalidation_readstatus,groupvalidation_time);
        if(res==1){
            return true;
        }else return false;
    }

    @Override
    public Boolean groupaddvalidation(HttpSession session, Integer groupvalidation_receiverid, Integer groupvalidation_groupid, LocalDateTime groupvalidation_time) {
        Integer groupvalidation_senderid=(Integer) session.getAttribute("user_id");
        int res= groupValidationDao.addvalidation(groupvalidation_senderid,groupvalidation_receiverid,groupvalidation_groupid,LocalDateTime.now());
        if(res==1){
            return true;
        }
        else return false;
    }

    @Override
    public Boolean handlegroupvalidation(Byte agree, Integer group_validation_id) {
        Groupvalidation groupvalidation=groupValidationDao.selectgroupvalidation(group_validation_id);
        if(groupvalidation==null){
            return false;
        }
        else {
            groupValidationDao.readvalidation(group_validation_id);
            if(agree==1){
                Groupinfo groupinfo=applyDao.getgroupapplyByGroupId(groupvalidation.getGroupvalidation_groupid());
                applyDao.addgroupapply(groupvalidation.getGroupvalidation_receiverid(),LocalDateTime.now(),groupvalidation.getGroupvalidation_groupid(),groupinfo.getGroup_hostid(), String.valueOf(groupvalidation.getGroupvalidation_senderid()));
                groupValidationDao.deletevalidation();
                return true;
            }
            else {
                groupValidationDao.deletevalidation();
                return true;
            }
        }
    }
}
