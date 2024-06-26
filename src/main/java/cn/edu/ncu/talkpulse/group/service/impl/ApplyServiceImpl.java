package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.account.dao.AccountDao;
import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.dto.WebSocketDTO;
import cn.edu.ncu.talkpulse.friends.service.WebSocketServer;
import cn.edu.ncu.talkpulse.group.dao.*;
import cn.edu.ncu.talkpulse.group.entity.Corre;
import cn.edu.ncu.talkpulse.group.entity.GroupApplyWithGroupInfo;
import cn.edu.ncu.talkpulse.group.entity.Groupapply;
import cn.edu.ncu.talkpulse.group.service.ApplyService;
import cn.edu.ncu.talkpulse.group.service.GroupMessageService;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("invite")
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private GroupMessageService groupMessageService;
    @Autowired
    private WebSocketServer webSocketServer;
    @Autowired
    private ApplyDao applyDao;
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
        List<GroupApplyWithGroupInfo> applyList = applyDao.getGroupAppliesBySenderId(senderid);
        return applyList;
    }
    //获取当前用户接受到的群聊申请
    @Override
    public List<GroupApplyWithGroupInfo> getMyGroupapply(Integer groupapply_hostid){
        List<GroupApplyWithGroupInfo>applyList= applyDao.getGroupapplyByReceiverId(groupapply_hostid);
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
        int res= applyDao.addgroupapply(groupapply_senderid,groupapply_time,groupapply_groupid,groupapply_hostid,groupapply_introduce);
        if(res==1){
            webSocketServer.sendToUser(groupapply_hostid, WebSocketDTO.GROUP_REQUEST);
            return true;
        }
        else return false;
    }

    //群主处理群聊申请请求
    @Override
    @Transactional
    public Result handleGroupapply(Byte status, Integer hostid, Integer groupapply_id) {
        Groupapply groupapply = applyDao.getgroupapplyByid(groupapply_id);
            if (groupapply == null) {
                return Result.fail("群聊申请不存在");
            }
            if(! hostid.equals(groupapply.getGroupapply_hostid())) return Result.fail("当前用户非群主");

            groupapply.setGroupapply_status(status);// 同意1，拒绝-1
            applyDao.updategroupapply(groupapply);
            if (status == 1) {
                Integer senderid = groupapply.getGroupapply_senderid();
                Integer gid = groupapply.getGroupapply_groupid();
                    Corre corre=correDao.ingroup(gid,senderid);
                    if(corre==null) {
                        correDao.addcorre(senderid, gid);

                        UserInfo userInfo = accountDao.findUserById(senderid);
                        groupMessageService.sendMessage(senderid, gid, userInfo.getUser_name()+" 加入了群聊", 0);

                        webSocketServer.sendToUser(senderid,WebSocketDTO.NEW_MSG);
                    }
            }
//        applyDao.exitGroupapply();

        return Result.success();
    }
}








