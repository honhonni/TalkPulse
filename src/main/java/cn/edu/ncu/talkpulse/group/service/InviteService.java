package cn.edu.ncu.talkpulse.group.service;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;

public interface InviteService {
    Boolean invite( Integer groupvalidation_receiverid, Integer groupvalidation_groupid, LocalDateTime groupvalidation_time, HttpSession session);
}
