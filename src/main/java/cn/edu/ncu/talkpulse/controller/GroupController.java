package cn.edu.ncu.talkpulse.controller;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.group.entity.GroupApplyWithGroupInfo;
import cn.edu.ncu.talkpulse.group.service.*;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
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
   private ApplyService applyService;
   @Autowired
   private GroupValidationService groupValidationService;
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
   @PostMapping("exit")//退出群聊（如果是群主，则解散群聊
    public Result ExitGroup(@RequestParam("group_id")Integer group_id , HttpServletRequest request){
       HttpSession session=request.getSession();
       Boolean ok= exitService.exitGroup(group_id,session);
       if(ok) {
          return Result.success();
       }
       else return Result.fail();
   }
   @PostMapping("kick")//群主踢出群聊
   public Result Kickmember(@RequestParam("correuser_id")Integer correuser_id,
                            @RequestParam("corregroup_id")Integer corregroup_id,
                            HttpServletRequest request){
                  HttpSession session=request.getSession();
                  Boolean ok=exitService.kickmember(correuser_id,corregroup_id,session);
                  if(ok){
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
                              @RequestParam("groupvalidation_readstatus")String groupvalidationReadStatus
                              ){
      HttpSession session=request.getSession();
      Boolean ok= groupValidationService.updateinvite(groupvalidationSenderId,session,groupvalidationGroupId,groupvalidationStatus,groupvalidationReadStatus,LocalDateTime.now());
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
   public Result addGroup(HttpServletRequest request,
                          @RequestParam("groupapply_groupid") Integer groupapply_groupid,
                          @RequestParam("groupapply_hostid") Integer groupapply_hostid,
                          @RequestParam("groupapply_introduce")String groupapply_introduce){
      HttpSession session=request.getSession();
      Boolean data= applyService.sendGroupapply(session,LocalDateTime.now(),groupapply_groupid,groupapply_hostid,groupapply_introduce);
      if(data)return Result.success();
      else return Result.fail();
   }
   //接收群聊申请(获取用户发送的群聊申请)
   @GetMapping("/getGroupapply")
   public Result getGroupapply(HttpServletRequest request){
      Integer id=(Integer) request.getSession().getAttribute("user_id");
      List<GroupApplyWithGroupInfo> applyList= applyService.getGroupAppliesBySenderId(id);
      List<GroupApplyWithGroupInfo>apply= applyService.getMyGroupapply(id);
      if(applyList!=null) {
         Map<String, Object> dataMap = new HashMap<>();
         dataMap.put("applylist", applyList);
         return Result.success(dataMap);
      }else if(apply!=null){
         Map<String,Object> dateMap=new HashMap<>();
         dateMap.put("apply",apply);
         return Result.success(dateMap);
         }
      else {
         return Result.fail();
      }
   }
   //群聊成员邀请好友进入群聊
   @PostMapping("addGroupvalidation")
   public Result addGroupvalidation(HttpServletRequest request,
                                    @RequestParam("groupvalidation_receiverid")Integer groupvalidation_receiverid,
                                    @RequestParam("groupvalidation_groupid")Integer groupvalidation_groupid){
      HttpSession session=request.getSession();
      Boolean date= groupValidationService.groupaddvalidation(session,groupvalidation_receiverid,groupvalidation_groupid,LocalDateTime.now());
      if(date){
         return Result.success();
      }else return Result.fail();
   }
   //被邀请成员处理信息
   @PostMapping("handlegroupvalidation")
   public Result handlegroupvalidation(@RequestParam("groupvalidation_state")Byte groupvalidation_state,
                                       @RequestParam("groupvalidation_id")Integer groupvalidation_id,
                                       HttpServletRequest request){
      Integer id=(Integer) request.getSession().getAttribute("user_id");
      Boolean data=groupValidationService.handlegroupvalidation(groupvalidation_state,groupvalidation_id);
      if(data)
      {
         return Result.success();
      }
      else return Result.fail();
   }

   //群主处理群聊申请接口
   @PostMapping("/handleGroupapply")
   public Result handleGroupapply(@RequestParam("groupapply_id") Integer groupapply_id,
                                  HttpServletRequest request,
                                  @RequestParam("groupapply_status") Byte groupapply_status){
      Integer ghid=(Integer) request.getSession().getAttribute("user_id");
      if(ghid==null) return Result.fail("非法请求，请先登录");
      return applyService.handleGroupapply(groupapply_status,ghid,groupapply_id);
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
