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
    public Boolean updateGroupIntroduce(String group_introduce, Integer group_id, HttpSession session) {
        Integer group_hostid = (Integer) session.getAttribute("user_id");
        int res = updateGroupInfoDao.updateGroupIntroduce(group_introduce, group_id, group_hostid);
        if (res == 1) {
            return true;
        } else return false;
    }
}

