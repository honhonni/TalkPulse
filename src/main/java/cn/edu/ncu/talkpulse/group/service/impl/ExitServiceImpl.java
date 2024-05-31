package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.group.dao.CorreDao;
import cn.edu.ncu.talkpulse.group.dao.CreateDao;
import cn.edu.ncu.talkpulse.group.dao.ExitDao;
import cn.edu.ncu.talkpulse.group.entity.Corre;
import cn.edu.ncu.talkpulse.group.entity.Groupapply;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.service.ExitService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("exitGroup")
public class ExitServiceImpl implements ExitService {
    @Autowired
    private ExitDao exitDao;

    @Override
    @Transactional
    public  Boolean exitGroup(Integer group_id, HttpSession session) {
        Integer user_id = (Integer) session.getAttribute("user_id");
        List<Corre> corres = exitDao.selectid(user_id);
        for (Corre corre : corres) {
            group_id = corre.getCorregroup_id();
            Groupinfo groupinfo=exitDao.selecthost(user_id);
            if (groupinfo!=null) {//用户并不是群主
                int res = exitDao.deleteGroup(user_id, group_id);//删除群聊
                if (res == 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                int res = exitDao.exitGroup(user_id, group_id);//退出群聊
                if (res == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
