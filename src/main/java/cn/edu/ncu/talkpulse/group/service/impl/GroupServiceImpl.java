package cn.edu.ncu.talkpulse.group.service.impl;


import cn.edu.ncu.talkpulse.group.dao.GroupDao;
import cn.edu.ncu.talkpulse.group.entity.groupinfo;
import cn.edu.ncu.talkpulse.group.entity.groupvalidation;
import cn.edu.ncu.talkpulse.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service("GroupService")
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDao groupDao;

    @Override
    public List<groupinfo>selectAll(int group_id){
        return groupDao.selectAll(group_id);
    }

    @Override
    public int applyForGroup(int senderId, int hostId, int groupId, boolean status, boolean readStatus, LocalDateTime time) {
        return 0;
    }

    @Override
    public int inviteUserToGroup(int senderId, int receiverId, int groupId, boolean status, boolean readStatus, LocalDateTime time) {
        return 0;
    }

    @Override
    public int GroupApply(int applyId, boolean status) {
        return 0;
    }

    @Override
    public int addUserToGroup(int userId, int groupId) {
        return 0;
    }

    @Override
    public int acceptGroupApply(int applyId, boolean status, boolean readStatus) {
        return 0;
    }

    @Override
    public int rejectGroupApply(int applyId, boolean status, boolean readStatus) {
        return 0;
    }

    @Override
    public int removeMemberFormGroup(int correuser_id, int corregroup_id) {
        return 0;
    }

    @Override
    public int disbandGroup(int corregroup_id) {
        return 0;
    }

    @Override
    public int saveInvitationValidation(int senderId, int receiverId, int groupId, boolean status, boolean readStatus, LocalDateTime time) {
        return 0;
    }

    @Override
    public boolean checkInvitationStatus(int senderId, int receiverId, int groupId) {
        return false;
    }

    @Override
    public int updateInvitationStatus(int senderId, int receiverId, int groupId, boolean newStatus, boolean newReadStatus) {
        return 0;
    }

    @Override
    public List<groupvalidation> getInvitationsForUser(int userId) {
        return null;
    }

    @Override
    public int updateInvitationReadStatus(int invitationId, boolean readStatus) {
        return 0;
    }

    @Override
    public int acceptInvitation(int invitationId, int userId, boolean status, boolean readStatus, LocalDateTime time) {
        return 0;
    }

    @Override
    public int rejectInvitation(int invitationId, int userId, boolean status, boolean readStatus, LocalDateTime time) {
        return 0;
    }

    @Override
    public groupinfo getGetGroupInfoById(int groupId) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getGroupMembers(int groupId) {
        return null;
    }

    @Override
    public int createGroup(groupinfo groupinfo) {
        return 0;
    }

    @Override
    public int updateGroupIntroduce(int groupId, String newIntroduce) {
        return 0;
    }

    @Override
    public String getGroupIntroduceById(int groupId) {
        return null;
    }


}
