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
    public Boolean exitGroup(Integer group_id, HttpSession session) {
        Integer user_id = (Integer) session.getAttribute("user_id");
        if (user_id == null) {
            // 用户未登录或其他错误处理
            throw new IllegalStateException("当前用户未登录");
        }

        List<Groupinfo> groupinfo = exitDao.selecthost(user_id, group_id); // 假设方法名已经改为 selectHost
        if (groupinfo != null && !groupinfo.isEmpty()) { // 检查列表是否非空
            // 用户是群主，删除群聊
            return exitDao.deleteGroup(group_id) == 1;
        } else {
            // 用户不是群主，退出群聊
            return exitDao.exitGroup(user_id, group_id) == 1;
        }
    }

    @Override
    public Boolean kickmember(Integer correuser_id, Integer corregroup_id, HttpSession session) {
        Integer group_hostid=(Integer) session.getAttribute("user_id");
         int res=exitDao.kickmember(correuser_id,corregroup_id,group_hostid);
         if(res==1){
             return true;
         }
         else return false;
    }
}
