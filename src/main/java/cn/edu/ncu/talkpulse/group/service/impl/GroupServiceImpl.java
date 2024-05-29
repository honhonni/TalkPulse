package cn.edu.ncu.talkpulse.group.service.impl;


import cn.edu.ncu.talkpulse.group.dao.GroupDao;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.entity.Groupvalidation;
import cn.edu.ncu.talkpulse.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service("GroupService")
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDao groupDao;

//    @Override
//    public List<Groupinfo>selectAll(int group_id){
//        return groupDao.selectAll(group_id);
//    }

    @Autowired
    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Transactional
    @Override
    public int applyForGroup(int senderid, int hostid, int groupid, String introduce, boolean status, boolean readstatus, LocalDateTime time) {
        return groupDao.applyForGroup(senderid, hostid, groupid, introduce, status, readstatus, time);
    }

    @Override
    public int inviteUserToGroup(int senderId, int receiverId, int groupId, boolean status, boolean readStatus, LocalDateTime time) {
        // 调用GroupDAO中的inviteUserToGroup方法，并返回结果
        return groupDao.inviteUserToGroup(senderId, receiverId, groupId, status, readStatus, time);
    }

    @Override
    public int GroupApply(int applyId, boolean status) {
        // 调用GroupDAO的GroupApply方法，并返回结果
        return groupDao.GroupApply(applyId, status);
    }

    @Override
    public int addUserToGroup(int userId, int groupId) {
        // 调用GroupDAO中的addUserToGroup方法，将用户添加到群聊中，并返回结果
        return groupDao.addUserToGroup(userId, groupId);
    }

    @Override
    public int acceptGroupApply(int applyId, boolean status, boolean readStatus) {
        return groupDao.acceptGroupApply(applyId,status,readStatus);
    }

    @Override
    public int rejectGroupApply(int applyId, boolean status, boolean readStatus) {
        return groupDao.rejectGroupApply(applyId,status,readStatus);
    }

    @Override
    public int removeMemberFormGroup(int correuser_id, int corregroup_id) {
        return groupDao.removeMemberFromGroup(correuser_id,corregroup_id);
    }

    @Override
    public int disbandGroup(int corregroup_id) {
        return groupDao.disbandGroup(corregroup_id);
    }

    @Override
    public int saveInvitationValidation(int senderId, int receiverId, int groupId, boolean status, boolean readStatus, LocalDateTime time) {
        return groupDao.saveInvitationValidation(senderId,receiverId,groupId,status,readStatus,time);
    }

    @Override
    public boolean checkInvitationStatus(int senderId, int receiverId, int groupId) {
        return groupDao.checkInvitationStatus(senderId,receiverId,groupId);
    }

    @Override
    public int updateInvitationStatus(int senderId, int receiverId, int groupId, boolean newStatus, boolean newReadStatus) {
        return groupDao.updateInvitationStatus(senderId,receiverId,groupId,newStatus,newReadStatus);
    }

    @Override
    public List<Groupvalidation> getInvitationsForUser(int userId) {
        return groupDao.getInvitationsForUser(userId);
    }

    @Override
    public int updateInvitationReadStatus(int invitationId, boolean readStatus) {
        return groupDao.updateInvitationReadStatus(invitationId,readStatus);
    }

    @Override
    public int acceptInvitation(int invitationId, int userId, boolean status, boolean readStatus, LocalDateTime time) {
        return groupDao.acceptGroupApply(invitationId,status,readStatus);
    }

    @Override
    public int rejectInvitation(int invitationId, int userId, boolean status, boolean readStatus, LocalDateTime time) {
        return groupDao.rejectInvitation(invitationId,userId,status,readStatus,time);
    }

//    @Override
//    public Groupinfo getGetGroupInfoById(int groupId) {
//        return groupDao.getGroupInfoById(groupId);
//    }

    @Override
    public List<Map<String, Object>> getGroupMembers(int groupId) {
        return groupDao.getGroupMembers(groupId);
    }

//    @Override
//    public int createGroup(Groupinfo groupinfo) {
//        return groupDao.createGroup(groupinfo);
//    }

    @Override
    public int updateGroupIntroduce(int groupId, String newIntroduce) {
        return groupDao.updateGroupIntroduce(groupId,newIntroduce);
    }

    @Override
    public String getGroupIntroduceById(int groupId) {
        return groupDao.getGroupIntroduceById(groupId);
    }

    @Override
    public List<Groupinfo> selectAll(int i) {
        return null;
    }


}
