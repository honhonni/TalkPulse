package cn.edu.ncu.talkpulse.account.service.impl;


import cn.edu.ncu.talkpulse.account.dao.ChatWindowsDao;
import cn.edu.ncu.talkpulse.account.dao.UpdateAvatarDao;
import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.account.service.ChatWindowsService;
import cn.edu.ncu.talkpulse.dto.ChatWindows;
import cn.edu.ncu.talkpulse.friends.entity.Record;
import cn.edu.ncu.talkpulse.group.entity.Corre;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.entity.Grouprecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service("ChatWindowsService")
public class ChatWindowsServiceImpl implements ChatWindowsService {
    @Autowired
    private ChatWindowsDao chatWindowsDao;
    @Override
    public List<ChatWindows> chatwindowset(Integer uid)
    {
        List <Record> userlist = chatWindowsDao.ChatUserWindows(uid);

        List <ChatWindows> windowslist = null;

        Map <String, Record> recordMap = new HashMap<>();
        // 遍历记录列表
        for (Record record : userlist) {
            String key =  record.getRecord_senderid() + "-" + record.getRecord_recipientid();
            // 如果 Map 中已经包含了相同发送者和接收者的记录，比较时间，保留最新的一条记录
            if (recordMap.containsKey(key)) {
                Record existingRecord = recordMap.get(key);
                if (record.getRecord_time().compareTo(existingRecord.getRecord_time()) > 0) {
                    // 如果当前记录的时间比已存在记录的时间晚，则替换已存在记录
                    recordMap.put(key, record);
                }
            } else {
                // 如果 Map 中没有相同发送者和接收者的记录，则添加到 Map 中
                recordMap.put(key, record);
            }
        }

        // 将 Map 中的记录转换为列表
        List<Record> filteredUserRecords = new ArrayList<>(recordMap.values());
        Collections.sort(filteredUserRecords, Comparator.comparing(Record::getRecord_time).reversed());
        // 打印筛选后的记录
        /*for (Record record : filteredUserRecords) {
            System.out.println(record);
        }*/

        //群聊过滤
        List <Grouprecord> grouplist = chatWindowsDao.ChatGroupWindows(uid);
        Set <Integer> appearedGroupIds = new HashSet<>();
        // 创建一个用于存储过滤后的群聊记录的列表
        List<Grouprecord> filteredGroupRecords = new ArrayList<>();
        // 遍历群聊记录列表，并根据出现过的群聊 ID 进行过滤
        for (Grouprecord groupRecord : grouplist) {
            int groupId = groupRecord.getGrouprecord_groupid();
            // 如果群聊 ID 已经出现过，则跳过该记录
            if (appearedGroupIds.contains(groupId)) {
             //   System.out.println(groupId+"已经出现过了");
                continue;
            }
            // 否则将该群聊 ID 加入到已出现的群聊 ID 集合中，并将记录添加到过滤后的列表中
          //  System.out.println(groupId+"加入屏蔽");
            appearedGroupIds.add(groupId);
            filteredGroupRecords.add(groupRecord);
        }

        // 打印筛选后的记录
       /* for (Grouprecord record : filteredGroupRecords) {
            System.out.println(record);
        }*/
        //混合编排群聊和私聊
        int user_index=0;
        int group_index=0;
        List<ChatWindows> chatwindows = new ArrayList<>();
        for (int i=1;i<=filteredUserRecords.size()+filteredGroupRecords.size();i++)
        {
            if (user_index>=filteredUserRecords.size())
            {
                Groupinfo groupinfo=chatWindowsDao.gruop_select(filteredGroupRecords.get(group_index).getGrouprecord_groupid());
                Corre corres=chatWindowsDao.corre_select(uid,filteredGroupRecords.get(group_index).getGrouprecord_groupid());
                ChatWindows chatWindow=new ChatWindows("groups",groupinfo.getGroup_id(),groupinfo.getGroup_photo(),groupinfo.getGroup_name(),filteredGroupRecords.get(group_index).getGrouprecord_time(),filteredGroupRecords.get(group_index).getGrouprecord_content(),filteredGroupRecords.get(group_index).getGrouprecord_type(),corres.isNewinform());
                chatWindow.setCompare_time(filteredGroupRecords.get(group_index).getGrouprecord_time());
                chatwindows.add(chatWindow);
                //System.out.println("user_index="+user_index+"    group_index="+group_index + "   group!!!!");
              //  System.out.println(chatWindow);
                group_index++;
                continue;
            }
            if (group_index>=filteredGroupRecords.size())
            {
                UserInfo userinfo=null;
                if (filteredUserRecords.get(user_index).getRecord_recipientid()==uid)
                     userinfo =chatWindowsDao.user_select(filteredUserRecords.get(user_index).getRecord_senderid());
                else
                     userinfo =chatWindowsDao.user_select(filteredUserRecords.get(user_index).getRecord_recipientid());
                Integer num=chatWindowsDao.unread_num(filteredUserRecords.get(user_index).getRecord_recipientid(),filteredUserRecords.get(user_index).getRecord_senderid());
                //Corre corres=chatWindowsDao.corre_select(uid,filteredGroupRecords.get(group_index).getGrouprecord_groupid());
                ChatWindows chatWindow=new ChatWindows("friends",userinfo.getUser_id(),userinfo.getUser_photo(),userinfo.getUser_name(),filteredUserRecords.get(user_index).getRecord_time(),filteredUserRecords.get(user_index).getRecord_content(),filteredUserRecords.get(user_index).getRecord_type(),num);
                chatWindow.setCompare_time(filteredUserRecords.get(user_index).getRecord_time());
                chatwindows.add(chatWindow);
              //  System.out.println(chatWindow);
               // System.out.println("user_index="+user_index+"    group_index="+group_index+"    user!!!!!");
                user_index++;
                continue;
            }
            LocalDateTime userRecordTime = filteredUserRecords.get(user_index).getRecord_time();
            LocalDateTime groupRecordTime = filteredGroupRecords.get(group_index).getGrouprecord_time();

            if (userRecordTime.isAfter(groupRecordTime) || userRecordTime.isEqual(groupRecordTime)) {
              /*  // 添加私聊记录到混合列表中
                Record userRecord = filteredUserRecords.get(user_index);
                UserInfo userInfo = null;
                if (userRecord.getRecord_recipientid() == uid) {
                    userInfo = chatWindowsDao.user_select(userRecord.getRecord_senderid());
                } else {
                    userInfo = chatWindowsDao.user_select(userRecord.getRecord_recipientid());
                }
                Integer num = chatWindowsDao.unread_num(userRecord.getRecord_recipientid(), userRecord.getRecord_senderid());
                ChatWindows chatWindow = new ChatWindows("friends", userInfo.getUser_id(), userInfo.getUser_photo(), userInfo.getUser_name(), userRecord.getRecord_time(), userRecord.getRecord_content(), userRecord.getRecord_type(), num);
                chatWindow.setCompare_time();
                chatwindows.add(chatWindow);
                System.out.println("user_index="+user_index+"    group_index="+group_index+"     user!!!!!");
                user_index++; // 移动用户记录指针*/
                UserInfo userinfo=null;
                if (filteredUserRecords.get(user_index).getRecord_recipientid()==uid)
                    userinfo =chatWindowsDao.user_select(filteredUserRecords.get(user_index).getRecord_senderid());
                else
                    userinfo =chatWindowsDao.user_select(filteredUserRecords.get(user_index).getRecord_recipientid());
                Integer num=chatWindowsDao.unread_num(filteredUserRecords.get(user_index).getRecord_recipientid(),filteredUserRecords.get(user_index).getRecord_senderid());
                //Corre corres=chatWindowsDao.corre_select(uid,filteredGroupRecords.get(group_index).getGrouprecord_groupid());
                ChatWindows chatWindow=new ChatWindows("friends",userinfo.getUser_id(),userinfo.getUser_photo(),userinfo.getUser_name(),filteredUserRecords.get(user_index).getRecord_time(),filteredUserRecords.get(user_index).getRecord_content(),filteredUserRecords.get(user_index).getRecord_type(),num);
                chatWindow.setCompare_time(filteredUserRecords.get(user_index).getRecord_time());
                chatwindows.add(chatWindow);
           //     System.out.println(chatWindow);
                //System.out.println("user_index="+user_index+"    group_index="+group_index+"    user!!!!!");
                user_index++;
            }
            else
            {
               /* Grouprecord groupRecord = filteredGroupRecords.get(group_index);
                Groupinfo groupInfo = chatWindowsDao.gruop_select(groupRecord.getGrouprecord_groupid());
                Corre corre = chatWindowsDao.corre_select(uid, groupRecord.getGrouprecord_groupid());
                ChatWindows chatWindow = new ChatWindows("groups", groupInfo.getGroup_id(), groupInfo.getGroup_photo(), groupInfo.getGroup_name(), groupRecord.getGrouprecord_time(), groupRecord.getGrouprecord_content(), groupRecord.getGrouprecord_type(), corre.isNewinform());
                chatwindows.add(chatWindow);
                System.out.println("user_index="+user_index+"    group_index="+group_index+"    group!!!");
                group_index++; // 移动群聊记录指针*/
                Groupinfo groupinfo=chatWindowsDao.gruop_select(filteredGroupRecords.get(group_index).getGrouprecord_groupid());
                Corre corres=chatWindowsDao.corre_select(uid,filteredGroupRecords.get(group_index).getGrouprecord_groupid());
                ChatWindows chatWindow=new ChatWindows("groups",groupinfo.getGroup_id(),groupinfo.getGroup_photo(),groupinfo.getGroup_name(),filteredGroupRecords.get(group_index).getGrouprecord_time(),filteredGroupRecords.get(group_index).getGrouprecord_content(),filteredGroupRecords.get(group_index).getGrouprecord_type(),corres.isNewinform());
                chatWindow.setCompare_time(filteredGroupRecords.get(group_index).getGrouprecord_time());
                chatwindows.add(chatWindow);
              //  System.out.println(chatWindow);
                //System.out.println("user_index="+user_index+"    group_index="+group_index + "   group!!!!");
                group_index++;
            }
        }
        Collections.sort(chatwindows, Comparator.comparing(ChatWindows::getCompare_time).reversed());
        return  chatwindows;
    }
}
