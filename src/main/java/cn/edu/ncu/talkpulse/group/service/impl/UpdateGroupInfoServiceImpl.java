package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.group.dao.UpdateGroupInfoDao;
import cn.edu.ncu.talkpulse.group.service.UpdateGroupInfoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("updategroupinfo")
public class UpdateGroupInfoServiceImpl implements UpdateGroupInfoService {
    @Autowired
    private UpdateGroupInfoDao updateGroupInfoDao;
    @Override
    public Boolean deleteIntroduce(Integer group_id, String group_introduce, HttpSession session) {
        Integer group_hostid = (Integer) session.getAttribute("user_id");
        int res = updateGroupInfoDao.deleteIntroduce(group_id, group_introduce, group_hostid);
        if(res==1)
        {
            return true;
        }
        else return false;
    }
    @Override
    public Boolean addIntroduce(Integer group_id,String group_introduce,HttpSession session){
        Integer group_hostid=(Integer) session.getAttribute("user_id");
        int res=updateGroupInfoDao.addIntroduce(group_id,group_introduce,group_hostid);
        if(res==1){
            return true;
        }
        else return false;
    }
}
