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
    public  Boolean exitGroup(Integer group_id, HttpSession session) {
        Groupinfo group_hostid = ExitDao.judgeHost(group_id);
        Integer user_id = (Integer) session.getAttribute("user_id");
        if (group_hostid == ExitDao.judgeHost(user_id)) {
            int res = exitDao.deleteGroup(user_id,group_id);//删除群聊
            if (res == 1) {
                return true;
            } else return false;
        } else {
            int res = exitDao.exitGroup(user_id, group_id);//退出群聊
            if (res == 1) {
                return true;
            } else return false;
        }
    }
}
