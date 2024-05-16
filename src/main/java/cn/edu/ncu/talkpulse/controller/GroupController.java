package cn.edu.ncu.talkpulse.controller;

import cn.edu.ncu.talkpulse.account.dao.AccountDao;
import cn.edu.ncu.talkpulse.account.service.AccountService;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.group.dao.GroupDao;
import cn.edu.ncu.talkpulse.group.entity.groupinfo;
import cn.edu.ncu.talkpulse.group.entity.groupvalidation;
import cn.edu.ncu.talkpulse.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupDao groupDao;
    // 用户申请入群接口方法
    @PostMapping("/apply")
    public ResponseEntity<Result> applyForGroup(
            @RequestParam("senderid") int senderid,
            @RequestParam("hostid") int hostid,
            @RequestParam("groupid") int groupid,
            @RequestParam("introduce")String introduce,
            @RequestParam("status") boolean status,
            @RequestParam("readstatus") boolean readstatus,
            @RequestParam(value = "time", required = false) LocalDateTime time
    ) {
        try {
            // 如果没有传入时间，则使用当前时间
            if (time == null) {
                time = LocalDateTime.now();
            }

            // 调用服务层方法处理申请入群逻辑
            int result = groupService.applyForGroup(senderid, hostid, groupid, introduce,status, readstatus, time);

            // 根据服务层返回的结果构建响应
            if (result > 0) {
                return new ResponseEntity<>(Result.success("申请入群成功！"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Result.fail("申请入群失败！"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            // 处理异常，返回失败响应
            return new ResponseEntity<>(Result.fail("申请入群时发生异常：" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/invite")
    public ResponseEntity<Result> inviteUserToGroup(
            @RequestParam("senderid") int senderId,
            @RequestParam("receiverid") int receiverId,
            @RequestParam("groupid") int groupId,
            @RequestParam("status") boolean status,
            @RequestParam(value = "readstatus", defaultValue = "false") boolean readStatus,
            @RequestParam(value = "time", required = false) LocalDateTime time
    ) {
        try {
            // 如果没有传入时间，则使用当前时间
            if (time == null) {
                time = LocalDateTime.now();
            }

            // 调用服务层方法处理邀请逻辑
            int result = groupService.inviteUserToGroup(senderId, receiverId, groupId, status, readStatus, time);

            // 根据服务层返回的结果构建响应
            if (result > 0) {
                return new ResponseEntity<>(Result.success("邀请用户加入群聊成功！"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Result.fail("邀请用户加入群聊失败！"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            // 处理异常，返回失败响应
            return new ResponseEntity<>(Result.fail("邀请用户加入群聊时发生异常：" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // 群主接收入群申请接口方法
    @PutMapping("/apply/{applyId}/accept")
    public ResponseEntity<?> acceptGroupApply(@PathVariable("applyId") int applyId, @RequestParam("status") boolean status) {
        try {
            // 调用GroupDao的GroupApply方法更新申请状态
            int updatedRows = groupDao.GroupApply(applyId, status);

            if (updatedRows > 0) {
                // 如果成功更新了申请状态，返回成功的响应
                return ResponseEntity.status(HttpStatus.OK).body("已成功接受组申请。");
            } else {
                // 如果没有找到对应的申请或更新失败，返回错误响应
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("找不到具有给定ID的组应用程序或无法更新状态。");
            }
        } catch (Exception e) {
            // 处理异常，返回错误响应
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("接受组应用程序时出错。");
        }
    }
    @PostMapping("/addUser")
    public ResponseEntity<String> addUserToGroup(@RequestParam("userId") int userId,
                                                 @RequestParam("groupId") int groupId) {
        try {
            // 调用DAO层的addUserToGroup方法将用户添加到群聊
            int result = groupDao.addUserToGroup(userId, groupId);

            if (result > 0) {
                // 如果返回结果大于0，表示添加成功
                return new ResponseEntity<>("用户已成功添加到群聊！", HttpStatus.OK);
            } else {
                // 否则，可能是添加失败或用户已存在于群聊中
                return new ResponseEntity<>("添加用户到群聊失败，请检查用户是否已存在或群聊信息是否正确！", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            // 异常处理
            return new ResponseEntity<>("添加用户到群聊时发生异常：" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // 群主接受入群申请接口
    @PostMapping("/acceptApply")
    public ResponseEntity<String> acceptGroupApply(@RequestParam("applyId") int applyId,
                                                   @RequestParam("status") boolean status,
                                                   @RequestParam("readStatus") boolean readStatus) {
        try {
            // 调用DAO层的acceptGroupApply方法接受入群申请
            int updatedRows = groupDao.acceptGroupApply(applyId, status, readStatus);

            if (updatedRows > 0) {
                // 如果返回结果大于0，表示更新成功
                return new ResponseEntity<>("入群申请已成功接受！", HttpStatus.OK);
            } else {
                // 否则，可能是没有找到对应的申请记录或更新失败
                return new ResponseEntity<>("无法找到对应的入群申请或接受申请失败！", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // 异常处理
            return new ResponseEntity<>("接受入群申请时发生异常：" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/reject/{applyId}")
    public ResponseEntity<?> rejectGroupApply(@PathVariable int applyId) {
        try {
            // 设置拒绝申请的状态和已读状态
            boolean status = false; // 假设false表示拒绝
            boolean readStatus = true; // 假设true表示已读

            // 调用GroupDao中的方法拒绝申请
            int updatedRows = groupDao.rejectGroupApply(applyId, status, readStatus);

            if (updatedRows > 0) {
                // 申请被拒绝成功
                return ResponseEntity.ok("申请被拒绝成功");
            } else {
                // 没有找到对应的申请或拒绝失败
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("未找到对应的申请或拒绝失败");
            }
        } catch (Exception e) {
            // 处理异常
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器内部错误");
        }
    }
    @DeleteMapping("/{groupId}/members/{memberId}")
    public ResponseEntity<?> removeMemberFromGroup(@PathVariable int groupId, @PathVariable int memberId) {
        try {
            // 调用GroupDao中的方法踢出成员
            int deletedRows = groupDao.removeMemberFromGroup(memberId, groupId);

            if (deletedRows > 0) {
                // 成员被成功踢出
                return ResponseEntity.ok("成员已从群中成功踢出");
            } else {
                // 没有找到对应的成员或踢出失败
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("未找到对应的成员或踢出失败");
            }
        } catch (Exception e) {
            // 处理异常
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器内部错误");
        }
    }
    @DeleteMapping("/{groupId}/disband")
    public ResponseEntity<?> disbandGroup(@PathVariable int groupId, @RequestParam int correuser_id) {
        try {
            // 调用GroupDao中的方法解散群聊
            int deletedRows = groupDao.disbandGroup(correuser_id);

            if (deletedRows > 0) {
                // 群聊解散成功
                return ResponseEntity.ok("群聊已成功解散");
            } else {
                // 没有找到对应的群聊或解散失败
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("未找到对应的群聊或解散失败");
            }
        } catch (Exception e) {
            // 处理异常
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器内部错误");
        }
    }
    @PostMapping("/invitations/validation")
    public ResponseEntity<?> saveInvitationValidation(
            @RequestParam int senderId,
            @RequestParam int receiverId,
            @RequestParam int groupId,
            @RequestParam boolean status,
            @RequestParam("readStatus") boolean readStatus,
            @RequestParam("time") LocalDateTime time) {
        try {
            // 调用GroupDao中的方法保存邀请验证记录
            int rowsAffected = groupDao.saveInvitationValidation(senderId, receiverId, groupId, status, readStatus, time);

            if (rowsAffected > 0) {
                // 保存成功
                return ResponseEntity.ok("邀请验证记录保存成功");
            } else {
                // 保存失败，可能是数据验证不通过或其他原因
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("邀请验证记录保存失败");
            }
        } catch (Exception e) {
            // 处理异常
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器内部错误：" + e.getMessage());
        }
    }
    @GetMapping("/check-invitation")
    public ResponseEntity<?> checkInvitationStatus(
            @RequestParam("senderId") int senderId,
            @RequestParam("receiverId") int receiverId,
            @RequestParam("groupId") int groupId) {
        try {
            // 调用GroupDao中的方法来检查邀请状态
            boolean invitationStatus = groupDao.checkInvitationStatus(senderId, receiverId, groupId);

            // 根据邀请状态返回相应的响应
            if (invitationStatus) {
                // 假设true表示邀请有效或已接受
                return ResponseEntity.ok("邀请有效或已接受");
            } else {
                // 假设false表示邀请无效、已拒绝或不存在
                return ResponseEntity.ok("邀请无效、已拒绝或不存在");
            }
        } catch (Exception e) {
            // 处理异常
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器内部错误");
        }
    }
    @PutMapping("/update-invitation-status")
    public ResponseEntity<?> updateInvitationStatus(
            @RequestParam("senderId") int senderId,
            @RequestParam("receiverId") int receiverId,
            @RequestParam("groupId") int groupId,
            @RequestParam("newStatus") boolean newStatus,
            @RequestParam("newReadStatus") boolean newReadStatus) {
        try {
            // 调用GroupDao中的方法来更新邀请状态
            int updatedRows = groupDao.updateInvitationStatus(senderId, receiverId, groupId, newStatus, newReadStatus);

            if (updatedRows > 0) {
                // 邀请状态更新成功
                return ResponseEntity.ok("邀请状态更新成功");
            } else {
                // 没有找到对应的邀请或更新失败
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("未找到对应的邀请或更新失败");
            }
        } catch (Exception e) {
            // 处理异常
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器内部错误");
        }
    }
    @GetMapping("/invitations")
    public ResponseEntity<List<groupvalidation>> getInvitationsForUser(@RequestParam("userId") int userId) {
        try {
            // 调用GroupDao的getInvitationsForUser方法获取邀请列表
            List<groupvalidation> invitations = groupDao.getInvitationsForUser(userId);

            // 如果邀请列表不为空，返回成功的响应
            if (invitations != null && !invitations.isEmpty()) {
                return ResponseEntity.ok(invitations);
            } else {
                // 如果没有找到邀请，返回一个空的列表
                return ResponseEntity.ok(new ArrayList<>());
            }
        } catch (Exception e) {
            // 处理异常，返回错误响应
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PutMapping("/update-invitation-readstatus")
    public ResponseEntity<?> updateInvitationReadStatus(
            @RequestParam("invitationId") int invitationId,
            @RequestParam("readStatus") boolean readStatus) {
        try {
            // 调用GroupDao的updateInvitationReadStatus方法来更新邀请的已读状态
            int updatedRows = groupDao.updateInvitationReadStatus(invitationId, readStatus);

            if (updatedRows > 0) {
                // 如果成功更新了行，返回成功的响应
                return ResponseEntity.ok().body("已成功更新邀请读取状态。");
            } else {
                // 如果没有更新任何行，返回相应的消息
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("未找到邀请或未进行更改。");
            }
        } catch (Exception e) {
            // 处理异常，返回错误响应
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新邀请读取状态时出错。");
        }
    }
    @PutMapping("/accept-invitation")
    public ResponseEntity<?> acceptInvitation(
            @RequestParam("invitationId") int invitationId,
            @RequestParam("userId") int userId,
            @RequestParam("status") boolean status,
            @RequestParam("readStatus") boolean readStatus) {
        try {
            // 获取当前时间作为时间戳
            LocalDateTime currentTime = LocalDateTime.now();

            // 调用GroupDao的acceptInvitation方法更新邀请状态
            int updatedRows = groupDao.acceptInvitation(invitationId, userId, status, readStatus, currentTime);

            if (updatedRows > 0) {
                // 如果成功更新了行，返回成功的响应
                return ResponseEntity.ok().body("邀请已成功接受。");
            } else {
                // 如果没有更新任何行，可能是邀请不存在或用户不匹配
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("找不到此用户的邀请。");
            }
        } catch (Exception e) {
            // 处理异常，返回错误响应
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("接受邀请时出错。");
        }
    }
    @PutMapping("/reject-invitation")
    public ResponseEntity<?> rejectInvitation(
            @RequestParam("invitationId") int invitationId,
            @RequestParam("userId") int userId,
            @RequestParam("status") boolean status) {
        try {
            // 获取当前时间作为时间戳
            LocalDateTime currentTime = LocalDateTime.now();

            // 假设readStatus是固定的，比如用户拒绝后，readStatus通常设置为true表示已读
            boolean readStatus = true; // 根据业务逻辑确定这个值

            // 调用GroupDao的rejectInvitation方法更新邀请状态
            int updatedRows = groupDao.rejectInvitation(invitationId, userId, status, readStatus, currentTime);

            if (updatedRows > 0) {
                // 如果成功更新了行，返回成功的响应
                return ResponseEntity.ok().body("邀请被成功拒绝。");
            } else {
                // 如果没有更新任何行，可能是邀请不存在或用户不匹配
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("找不到此用户的邀请。");
            }
        } catch (Exception e) {
            // 处理异常，返回错误响应
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("拒绝邀请时出错。");
        }
    }
    @GetMapping("/get-group-info")
    public ResponseEntity<?> getGroupInfoById(@RequestParam("groupId") int groupId) {
        try {
            // 调用GroupDao的getGroupInfoById方法获取群组信息
            groupinfo groupInfo = groupDao.getGroupInfoById(groupId);

            if (groupInfo != null) {
                // 如果找到了群组信息，返回成功响应和群组信息
                return ResponseEntity.ok(groupInfo);
            } else {
                // 如果没有找到群组信息，返回未找到响应
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("没有找到群组信息.");
            }
        } catch (Exception e) {
            // 处理异常，返回错误响应
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("寻找群组信息发生错误.");
        }
    }
    @GetMapping("/{groupId}/members")
    public ResponseEntity<List<Map<String, Object>>> getGroupMembers(@PathVariable("groupId") int groupId) {
        try {
            // 调用GroupDao的getGroupMembers方法获取群成员列表
            List<Map<String, Object>> members = groupDao.getGroupMembers(groupId);

            if (members != null && !members.isEmpty()) {
                // 如果成功获取到成员列表，返回成功的响应
                return ResponseEntity.ok(members);
            } else {
                // 如果没有找到成员，返回空列表
                return ResponseEntity.ok().body(new ArrayList<>());
            }
        } catch (Exception e) {
            // 处理异常，返回错误响应
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody groupinfo groupInfo) {
        try {
            // 调用GroupDao的createGroup方法创建新群聊
            int createdGroupId = groupDao.createGroup(groupInfo);

            if (createdGroupId > 0) {
                // 如果成功创建了群聊，返回成功的响应以及新创建的群聊ID
                return ResponseEntity.status(HttpStatus.CREATED).body("已成功创建ID为的群组: " + createdGroupId);
            } else {
                // 如果没有成功创建群聊，返回错误的响应
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("无法创建群组。");
            }
        } catch (Exception e) {
            // 处理异常，返回错误响应
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("创建群组时出错。");
        }
    }
    @PutMapping("/{groupId}/introduce")
    public ResponseEntity<?> updateGroupIntroduce(@PathVariable("groupId") int groupId, @RequestBody String newIntroduce) {
        try {
            // 调用GroupDao的updateGroupIntroduce方法更新群聊简介
            int updatedRows = groupDao.updateGroupIntroduce(groupId, newIntroduce);

            if (updatedRows > 0) {
                // 如果成功更新了群聊简介，返回成功的响应
                return ResponseEntity.status(HttpStatus.OK).body("群组简介更新成功。");
            } else {
                // 如果没有找到对应的群组或更新失败，返回错误响应
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("找不到具有给定ID的群组或无法更新简介。");
            }
        } catch (Exception e) {
            // 处理异常，返回错误响应
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新群组简介时出错。");
        }
    }
    @GetMapping("/get-group-introduce")
    public ResponseEntity<?> getGroupIntroduceById(@RequestParam("groupId") int groupId) {
        try {
            // 调用GroupDao的getGroupIntroduceById方法获取群聊简介
            String groupIntroduce = groupDao.getGroupIntroduceById(groupId);

            if (groupIntroduce != null && !groupIntroduce.isEmpty()) {
                // 如果找到了群聊简介，返回成功响应和简介内容
                return ResponseEntity.ok(groupIntroduce);
            } else {
                // 如果没有找到群聊简介或简介为空，返回未找到响应
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("找不到具有所提供ID的群组介绍。");
            }
        } catch (Exception e) {
            // 处理异常，返回错误响应
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("检索群组引入时出错。");
        }
    }
}
