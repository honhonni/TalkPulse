package cn.edu.ncu.talkpulse.group.service;

import cn.edu.ncu.talkpulse.group.entity.Groupvalidation;
import jakarta.servlet.http.HttpSession;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

public interface GroupValidationService {
   Boolean updateinvite( Integer groupvalidation_senderid, HttpSession session, Integer groupvalidation_groupid, String groupvalidation_status, String groupvalidation_readstatus, LocalDateTime groupvalidation_time);

   Boolean groupaddvalidation(HttpSession session,Integer groupvalidation_receiverid,Integer groupvalidation_groupid,LocalDateTime groupvalidation_time);

   Boolean handlegroupvalidation(Byte agree,Integer group_validation_id);
}
