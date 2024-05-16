package cn.edu.ncu.talkpulse.group.service;

import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.entity.groupvalidation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface GroupService {

//    List<groupinfo> selectAll(int group_id);
    int applyForGroup(int senderid, int hostid, int groupid, String introduce, boolean status, boolean readstatus, LocalDateTime time);
    int inviteUserToGroup(int senderId, int receiverId, int groupId, boolean status, boolean readStatus, LocalDateTime time);
    int GroupApply(int applyId, boolean status);
    int addUserToGroup(int userId,int groupId);
    int acceptGroupApply(int applyId,boolean status,boolean readStatus);
    int rejectGroupApply(int applyId,boolean status,boolean readStatus);
    int removeMemberFormGroup(int correuser_id,int corregroup_id);
    int disbandGroup(int corregroup_id);
    int saveInvitationValidation(int senderId,int receiverId,int groupId,boolean status,boolean readStatus,LocalDateTime time);
    boolean checkInvitationStatus(int senderId,int receiverId,int groupId);
    int updateInvitationStatus(int senderId,int receiverId,int groupId,boolean newStatus,boolean newReadStatus);
    List<groupvalidation> getInvitationsForUser(int userId);
    int updateInvitationReadStatus(int invitationId,boolean readStatus);
    int acceptInvitation(int invitationId,int userId,boolean status,boolean readStatus,LocalDateTime time);
    int rejectInvitation(int invitationId,int userId,boolean status,boolean readStatus,LocalDateTime time);

    List<Map<String, Object>> getGroupMembers(int groupId);

    //    groupinfo getGetGroupInfoById(int groupId);
//    List<Map<String,Object>>getGroupMembers(int groupId);
//    int createGroup(groupinfo groupinfo);
    int updateGroupIntroduce(int groupId,String newIntroduce);
    String getGroupIntroduceById(int groupId);

    List<Groupinfo> selectAll(int i);
}
