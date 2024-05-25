package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.group.dao.CorreDao;
import cn.edu.ncu.talkpulse.group.entity.corre;
import cn.edu.ncu.talkpulse.group.service.CorreService;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("getGroup")
public class CorreServiceImpl implements CorreService {
    @Autowired
    private CorreDao correDao;

    @Override//获取群聊成员信息
    public List<UserInfo> getgroup(HttpSession session) {
        Integer corregroup_id = (Integer) session.getAttribute("group_id");
        List<UserInfo> group = correDao.getgroup(corregroup_id);
        if (group != null && !group.isEmpty()) {
            // 如果需要的话，可以在这里对group中的UserInfo进行某种处理
            return group;
        } else {
            return null;
        }
    }
}
