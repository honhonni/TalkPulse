package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.group.dao.HostapplyDao;
import cn.edu.ncu.talkpulse.group.service.HostapplyService;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("HostapplyService")
public class HostapplyServiceImpl implements HostapplyService {
    @Autowired
    private HostapplyDao hostapplyDao;
    @Override
    public Boolean hostset(Boolean groupapply_status,Boolean groupapply_readstatus,Integer groupapply_groupid,HttpSession session){
        Integer groupapply_hostid=(Integer) session.getAttribute("user_id");
        int res=hostapplyDao.hostset(groupapply_status,groupapply_readstatus,groupapply_groupid,groupapply_hostid);
        if(res==1){
            return true;
        }else return false;
    }
}
