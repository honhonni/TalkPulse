package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.group.dao.UpdateGroupInfoDao;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.entity.corre;
import cn.edu.ncu.talkpulse.group.service.UpdateGroupInfoService;
import com.alibaba.fastjson2.JSONObject;
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
    @Override
    public JSONObject getGroupInfo(Integer group_id, HttpSession session){
        Integer correuser_id=(Integer) session.getAttribute("user_id");
        Groupinfo groupinfo=updateGroupInfoDao.getGroupInfo(group_id,correuser_id);
        JSONObject data=new JSONObject();
        if(groupinfo!=null){
            data.put("status",200);
            data.put("message","查询成功");
            data.put("present",true);
            data.put("group",groupinfo);
            return data;
        }
        else{
            data.put("status",201);
            data.put("message","查询失败");
            data.put("prsent",false);
            data.put("group",groupinfo);
            return data;
        }
    }
}

