package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.group.dao.CreateDao;
import cn.edu.ncu.talkpulse.group.dao.ExitDao;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.service.ExitService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("exitGroup")
public class ExitServiceImpl implements ExitService {
    @Autowired
    private ExitDao exitDao;
    @Override
    public  Boolean exitGroup(Integer corregroup_id, HttpSession session) {
        Groupinfo group_hostid = ExitDao.judgeHost(corregroup_id);
        Integer correuser_id = (Integer) session.getAttribute("user_id");
        if (group_hostid == ExitDao.judgeHost(correuser_id)) {
            int res = exitDao.deleteGroupId(corregroup_id);
            if (res == 1) {
                return true;
            } else return false;
        } else {
            int res = exitDao.exitGroup(correuser_id, corregroup_id);
            if (res == 1) {
                return true;
            } else return false;
        }
    }
}
