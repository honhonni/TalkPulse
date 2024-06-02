package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.dto.WebSocketDTO;
import cn.edu.ncu.talkpulse.friends.service.WebSocketServer;
import cn.edu.ncu.talkpulse.group.dao.CorreDao;
import cn.edu.ncu.talkpulse.group.dao.GroupRecordDao;
import cn.edu.ncu.talkpulse.group.entity.Grouprecord;
import cn.edu.ncu.talkpulse.group.service.GroupMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.ncu.talkpulse.group.entity.Corre;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupMessageServiceImpl implements GroupMessageService {
    @Autowired
    private CorreDao correDao;

    @Autowired
    private GroupRecordDao groupRecordDao;

    @Autowired
    private WebSocketServer webSocketServer;


    @Override
    public Result readGroupMessage(Integer uid, Integer groupId) {
        Corre corre = new Corre(uid,groupId,false);
        int res = correDao.updateState(corre);
        if(res==1) return Result.success();
        else return Result.fail("未知错误");
    }

    @Override
    @Transactional
    public Result sendMessage(Integer uid, Integer gid, String content, Integer type) {
        if(groupRecordDao.countGroup(gid)==0) return Result.fail("非法请求，要发送消息的群组不存在！");
        if(type!=0) return Result.fail("消息类型不匹配");

        Grouprecord grouprecord = new Grouprecord(content, LocalDateTime.now(),uid,gid,type);
        int res = groupRecordDao.addGroupRecord(grouprecord);
        if(res==0) return Result.fail("消息发送失败");

        System.out.println("发送群消息id="+grouprecord.getGrouprecord_id());

        // 查询群里其他用户
        List<Integer> ids = correDao.selectUser(gid, uid);
        if(ids!=null){
            // 更新其他用户的群消息状态
            int count = correDao.batchUpdateState(gid, ids);
            if(count!=ids.size()) return Result.fail("未知错误");
            // websocket通知其他群友
            webSocketServer.sendToGroup(ids, WebSocketDTO.NEW_MSG);
        }

        return Result.success();
    }


    @Override
    @Transactional
    public Result sendFileMessage(Integer uid, Integer gid, MultipartFile content, Integer type) {
        if(groupRecordDao.countGroup(gid)==0) return Result.fail("非法请求，要发送消息的群组不存在！");
        if(type!=1 && type!=2) return Result.fail("不支持的消息类型");

        Grouprecord grouprecord = new Grouprecord("", LocalDateTime.now(),uid,gid,type);
        int res = groupRecordDao.addGroupRecord(grouprecord);
        if(res==0) return Result.fail("发送失败");
        int id = grouprecord.getGrouprecord_id();

        String path = "src/main/resources/static";// 用了项目相对路径前面不用加/
        String storePath;
        if(type==1){// 图片
            storePath = "/images/group/img_";
        }else{// 语音
            storePath = "/voice/group/v_";
        }
        String fileSuffix = content.getOriginalFilename().substring(content.getOriginalFilename().lastIndexOf("."));
        try {
            storePath = storePath+id+fileSuffix;
            path += storePath;
            File file = new File(path);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            System.out.println("上传文件："+path);
            content.transferTo(file.getAbsoluteFile());// 转为绝对路径
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("上传失败");
        }

        int count = groupRecordDao.updateContent(storePath,id);
        if(count==0) return Result.fail("发送失败");

        // 查询群里其他用户
        List<Integer> ids = correDao.selectUser(gid, uid);
        if(ids!=null){
            // 更新其他用户的群消息状态
            count = correDao.batchUpdateState(gid, ids);
            if(count!=ids.size()) return Result.fail("未知错误");
            // websocket通知其他群友
            webSocketServer.sendToGroup(ids, WebSocketDTO.NEW_MSG);
        }

        return Result.success();
    }
}
