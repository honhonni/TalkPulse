package cn.edu.ncu.talkpulse.controller;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.dto.ValidationReceiverDTO;
import cn.edu.ncu.talkpulse.dto.ValidationSenderDTO;
import cn.edu.ncu.talkpulse.group.service.*;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
   @Autowired
   private HostapplyService hostapplyService;
   @Autowired
   private CorreService correService;
   @Autowired
   HttpServletRequest request;
   private Integer getGroupIdFromSession(){
      return (Integer) request.getSession().getAttribute("user_id");
   }
   @PostMapping("/create")//创建群聊
    public Result CreateGroup(@RequestParam("group_id")Integer groupId,
                              @RequestParam("group_name")String groupName,
                              @RequestParam("group_introduce")String groupIntroduce,
                              @RequestParam("group_photo")String groupPhoto,
                              HttpServletRequest request
                              ){
       HttpSession session = request.getSession();
       Boolean ok=createService.CreateGroup(groupId,groupName,groupIntroduce,session);
       if(ok) {
          createService.upphoto(groupId,groupPhoto);
          return Result.success();
       }
       else return Result.fail();
   }
   @PostMapping("exit")//退出群聊
    public Result ExitGroup(@RequestParam("corregroup_id")Integer corregroup_id , HttpServletRequest request){
       HttpSession session=request.getSession();
       Boolean ok= exitService.exitGroup(corregroup_id,session);
       if(ok) {
          System.out.println("200");

          return Result.success();
       }
       else return Result.fail();
   }
   @PostMapping("updateinvite")//更新群聊信息
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
   }
   @PostMapping("userapplyinto")//用户申请入群
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
   }
   @PostMapping("updategroupinfo")//更新群聊简介
   public Result UpdateGroupInfo(@RequestParam("group_id")Integer group_id,
                                 @RequestParam("group_introduce")String group_introduce,
                                 HttpServletRequest request){
      HttpSession session=request.getSession();
      Boolean ok=updateGroupInfoService.updateGroupIntroduce(group_introduce,group_id,session);
      if(ok) return Result.success();
      else return Result.fail();
   }
   @GetMapping("hostapply")//群主接收群聊申请
   public Result HostApply(@RequestParam("groupapply_groupid")Integer groupapply_groupid,
                           HttpServletRequest request){
      HttpSession session=request.getSession();
      Boolean ok=hostapplyService.hostapply(groupapply_groupid,session);
      if(ok) return Result.success();
      else return Result.fail();
   }
   @PostMapping("hostset")//群主处理群聊申请
   public Result HostSet(@RequestParam("groupapply_status")Boolean groupapply_status,
                         @RequestParam("groupapply_readstatus")Boolean groupapply_readstatus,
                         @RequestParam("groupapply_groupid")Integer groupapply_groupid,
                         HttpServletRequest request){
      HttpSession session=request.getSession();
      Boolean ok=hostapplyService.hostset(groupapply_status,groupapply_readstatus,groupapply_groupid,session);
      if(ok) return Result.success();
      else return Result.fail();
   }
   @GetMapping("/getGroupInfo")//获取群聊简介接口
   public Result getGroupinfo(@RequestParam("group_Id") Integer group_id,
                        HttpServletRequest request){
      HttpSession session=request.getSession();
      JSONObject data=updateGroupInfoService.getGroupInfo(group_id,session);
      if(data!=null) return Result.success(data);
      else return Result.fail();
   }
//申请群聊接口
   @PostMapping("/addGroup")
   public Result addGroup(@RequestParam("group_id") Integer GroupId){
      Integer gid=getGroupIdFromSession();
      if(gid==null) return Result.fail("非法请求，请先登录");
      return inviteService.sendGroupapply(gid,GroupId);
   }
   //接收群聊申请(获取用户发送和接受到的群聊申请)
   @GetMapping("/getGroupapply")
   public Result getGroupapply(){
      Integer gid=getGroupIdFromSession();
      if(gid==null) return Result.fail("非法请求，请先登录");
      List<ValidationSenderDTO>validationList=inviteService.getGroupapply(gid);
      List<ValidationReceiverDTO>applyList=inviteService.getMyGroupapply(gid);
      Map<String,Object>dataMap=new HashMap<>();
      dataMap.put("validationlist",validationList);
      dataMap.put("applylist",applyList);
      return Result.success(dataMap);
   }

   //处理群聊申请接口
   @PostMapping("/handleGroupapply")
   public Result handleGroupapply(@RequestParam("groupapply_id") Integer groupapplyId,
                                  HttpServletRequest request,
                                  @RequestParam("agree") Boolean groupapply_readstatus){
      HttpSession session=request.getSession();
      Integer gid=getGroupIdFromSession();
      if(gid==null) return  Result.fail("非法请求，请先登录");
      return inviteService.handleGroupapply(gid,groupapplyId,session,groupapply_readstatus);
   }
   //获取群聊成员列表
   @PostMapping("/getGroupMember")
   public List<UserInfo> getGroupMember(HttpSession session)
   {
      Integer gid=getGroupIdFromSession();
      if(gid!=null)
      {return correService.getgroup(session);}
      else
           return null;
   }
}
