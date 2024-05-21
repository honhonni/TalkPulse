package cn.edu.ncu.talkpulse.controller;

import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.group.dao.UpdateGroupInfoDao;
import cn.edu.ncu.talkpulse.group.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/group")
public class GroupController {
   @Autowired
   private CreateService createService;
   @Autowired
   private ExitService exitService;
   @Autowired
   private InviteService inviteService;
   @Autowired
   private UpdateInviteService updateInviteService;
   @Autowired
   private UserApplyIntoService userApplyIntoService;
   @Autowired
   private UpdateGroupInfoService updateGroupInfoService;
   @PostMapping("/create")
    public Result CreateGroup(@RequestParam("group_id")Integer groupId,
                              @RequestParam("group_name")String groupName,
                              @RequestParam("group_introduce")String groupIntroduce,
                              HttpServletRequest request
                              ){
       HttpSession session = request.getSession();
       Boolean ok=createService.CreateGroup(groupId,groupName,groupIntroduce,session);
       if(ok) return Result.success();
       else return Result.fail();
   }//创建群聊
   @PostMapping("exit")
    public Result ExitGroup(@RequestParam("corregroup_id")Integer corregroup_id , HttpServletRequest request){
       HttpSession session=request.getSession();
       Boolean ok= exitService.exitGroup(corregroup_id,session);
       if(ok) return Result.success();
       else return Result.fail();
   }//退出群聊
   @PostMapping("invite")
   public Result InviteGroup(@RequestParam("groupvalidation_receiverid")Integer groupvalidationReceiverId,
                             @RequestParam("groupvalidation_groupid")Integer groupvalidationGroupId,
                             HttpServletRequest request){
      HttpSession session=request.getSession();
      Boolean ok=inviteService.invite(groupvalidationReceiverId,groupvalidationGroupId,session);
      if(ok) return Result.success();
      else return Result.fail();
   }//邀请进入群聊
   @PostMapping("updateinvite")
   public Result UpdateInvite(
                              @RequestParam("groupvalidation_senderid")Integer groupvalidationSenderId,
                              HttpServletRequest request,
                              @RequestParam("groupvalidation_groupid")Integer groupvalidationGroupId,
                              @RequestParam("groupvalidation_status")String groupvalidationStatus,
                              @RequestParam("groupvalidation_readstatus")String groupvalidationReadStatus,
                              @RequestParam("groupvalidation_time")LocalDateTime groupvalidationTime){
      HttpSession session=request.getSession();
      Boolean ok=updateInviteService.updateinvite(groupvalidationSenderId,session,groupvalidationGroupId,groupvalidationStatus,groupvalidationReadStatus,groupvalidationTime);
      if(ok) return Result.success();
      else return Result.fail();
   }//更新群聊信息
   @PostMapping("userapplyinto")
   public Result UserApplyInto(
                               HttpServletRequest request,
                               @RequestParam("groupapply_time")LocalDateTime groupapplyTime,
                               @RequestParam("groupapply_groupid")Integer groupapplyGroupId,
                               @RequestParam("groupapply_introduce")String groupapplyIntroduce
                               ){
      HttpSession session=request.getSession();
      Boolean ok=userApplyIntoService.UserApplyInto(session,groupapplyTime,groupapplyGroupId,groupapplyIntroduce);
      if(ok) return Result.success();
      else return Result.fail();
   }//用户申请入群
   @GetMapping("hostset")
   public Result hostSet(@RequestParam("groupapply_groupid")Integer groupapply_grouid,
                         HttpServletRequest request){
      HttpSession session=request.getSession();
      Boolean res=userApplyIntoService.hostSet(groupapply_grouid,session);
      if(res) return Result.success(res);
      else return Result.fail();


   }
   @PostMapping("updategroupinfo")
   public Result UpdateGroupInfo(@RequestParam("group_id")Integer group_id,
                                 @RequestParam("group_introduce")String group_introduce,
                                 HttpServletRequest request){
      HttpSession session=request.getSession();
      Boolean ok=updateGroupInfoService.updateGroupIntroduce(group_introduce,group_id,session);
      if(ok) return Result.success();
      else return Result.fail();
   }//更新群聊简介
}
