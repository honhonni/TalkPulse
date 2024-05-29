package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.account.dao.AccountDao;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.dto.ValidationReceiverDTO;
import cn.edu.ncu.talkpulse.group.dao.*;
import cn.edu.ncu.talkpulse.group.entity.GroupApplyWithGroupInfo;
import cn.edu.ncu.talkpulse.group.entity.Groupapply;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.service.InviteService;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("invite")
public class InviteServiceImpl implements InviteService {
    @Autowired
    private InviteDao inviteDao;
    @Autowired
    private CorreDao correDao;
    @Autowired
    private CreateDao createDao;
    @Resource
    private AccountDao accountDao;
    @Autowired
    private GroupInfoDao groupInfoDao;
//获取用户发送的群聊申请

    @Override
    public List<GroupApplyWithGroupInfo> getGroupAppliesBySenderId(Integer senderid) {
        List<GroupApplyWithGroupInfo> applyList = inviteDao.getGroupAppliesBySenderId(senderid);
        return applyList;
    }
    //获取当前用户接受到的群聊申请
    @Override
    public List<GroupApplyWithGroupInfo> getMyGroupapply(Integer groupapply_hostid){
        List<GroupApplyWithGroupInfo>applyList=inviteDao.getGroupapplyByReceiverId(groupapply_hostid);
        return applyList;
    }


    // 假设的创建响应的方法
    private JSONObject createResponse(int code, String message, JSONArray data) {
        JSONObject response = new JSONObject();
        response.put("code", code);
        response.put("message", message);
        response.put("data", data);
        return response;
    }
    //发送添加群聊申请

    @Override
    public Boolean sendGroupapply(HttpSession session ,LocalDateTime groupapply_time,Integer groupapply_groupid,Integer groupapply_hostid,String groupapply_introduce) {
        Integer groupapply_senderid=(Integer) session.getAttribute("user_id");
        int res=inviteDao.addgroupapply(groupapply_senderid,groupapply_groupid,groupapply_hostid,groupapply_introduce,groupapply_time);
        if(res==1){
            return true;
        }
        else return false;
    }

    //获取用户发送的好友请求


    //处理好友申请请求
    @Override
    @Transactional
    public Result handleGroupapply(Integer gid, Integer senderid, HttpSession session, Boolean status){
        Integer hostid=(Integer) session.getAttribute("user_id");
        Groupapply groupapply1=inviteDao.getgroupapplyById(hostid);
        if(groupapply1==null) return Result.fail("群聊申请不存在");
        if(!gid.equals(groupapply1.getGroupapply_groupid())) return Result.fail("非法请求");
        if(groupapply1.getGroupapply_status()!=0)return Result.fail("已经处理过请求");
        groupapply1.getGroupapply_status();
        inviteDao.updategroupapply(groupapply1);
        if(status){
            senderid=groupapply1.getGroupapply_senderid();
            gid=groupapply1.getGroupapply_groupid();
            int res1=correDao.addcorre(senderid,gid);
            return Result.success();
        }
        return Result.success();
    }
}







