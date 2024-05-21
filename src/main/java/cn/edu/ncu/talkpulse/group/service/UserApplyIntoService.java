package cn.edu.ncu.talkpulse.group.service;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;

public interface UserApplyIntoService {
   Boolean UserApplyInto(HttpSession session, LocalDateTime groupapply_time, Integer groupapply_groupid,String groupapply_introduce);
}
