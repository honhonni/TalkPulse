package cn.edu.ncu.talkpulse.friends.service.impl;

import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.dto.WebSocketDTO;
import cn.edu.ncu.talkpulse.friends.dao.RecordDao;
import cn.edu.ncu.talkpulse.friends.entity.Record;
import cn.edu.ncu.talkpulse.friends.service.RecordService;
import cn.edu.ncu.talkpulse.friends.service.WebSocketServer;
import cn.edu.ncu.talkpulse.account.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordDao recordDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private WebSocketServer webSocketServer;

    @Override
    public Result sendMessage(Integer senderId, Integer receiverId, String content, Integer type) {
        if(accountDao.countUser(receiverId)==0) return Result.fail("非法请求，要发送消息的用户不存在！");
        if(type!=0) return Result.fail("消息类型不匹配");

        Record record = new Record(content,LocalDateTime.now(),senderId,receiverId,0,type);
        int res = recordDao.addRecord(record);
        if(res==0) return Result.fail("消息发送失败");

        System.out.println("发送私聊消息id="+record.getRecord_id());
        // websocket通知
        webSocketServer.sendToUser(receiverId, WebSocketDTO.NEW_MSG);
        return Result.success();
    }

    @Override
    @Transactional
    public Result sendFileMessage(Integer senderId, Integer receiverId, MultipartFile content, Integer type) {
        if(accountDao.countUser(receiverId)==0) return Result.fail("非法请求，要发送消息的用户不存在！");
        if(type!=1 && type!=2) return Result.fail("不支持的消息类型");
        Record record = new Record("",LocalDateTime.now(),senderId,receiverId,0,type);
        int res = recordDao.addRecord(record);
        if(res==0) return Result.fail("发送失败");
        Integer recordId = record.getRecord_id();

        String path = "src/main/resources/static";// 注意：用了项目相对路径前面不用加/
        String fileSuffix = content.getOriginalFilename().substring(content.getOriginalFilename().lastIndexOf("."));
        String storePath;
        if(type==1){// 图片
            storePath = "/images/friends/img_";
        }else{// 语音
            storePath = "/voice/friends/v_";
        }
        try {
            storePath = storePath+recordId+fileSuffix;
            path += storePath;
            File file = new File(path);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();// 注意mkdir和mkdirs的区别！
            }
            System.out.println("上传文件："+path);
            content.transferTo(file.getAbsoluteFile());// 巨坑：transferTo只能使用绝对路径，否则会将相对路径拼接成不存在的绝对路径，报错FileNotFoundException
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("上传失败");
        }

        int count = recordDao.updateContent(storePath, recordId);
        if(count==0) return Result.fail("发送失败");

        // websocket通知
        webSocketServer.sendToUser(receiverId, WebSocketDTO.NEW_MSG);

        return Result.success();
    }

    @Override
    public Result readMessage(Integer uid, Integer friendId) {
        recordDao.updateReadStatus(friendId,uid);
        return Result.success();
    }
}
