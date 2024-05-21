package cn.edu.ncu.talkpulse.group.service;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;

public interface UpdateInviteService {
   Boolean updateinvite( Integer groupvalidation_senderid, HttpSession session, Integer groupvalidation_groupid, String groupvalidation_status, String groupvalidation_readstatus, LocalDateTime groupvalidation_time);

}
