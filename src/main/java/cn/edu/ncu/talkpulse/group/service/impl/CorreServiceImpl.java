package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.group.dao.CorreDao;
import cn.edu.ncu.talkpulse.group.entity.Corre;
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
    public Result getmember(Integer group_id) {
        List<UserInfo> members = correDao.getgroup(group_id);
        if (members != null && !members.isEmpty()) {
            // 如果需要的话，可以在这里对group中的UserInfo进行某种处理
            return Result.success(members);
        } else {
            return Result.fail();
        }
    }
}
