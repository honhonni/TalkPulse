package cn.edu.ncu.talkpulse.account.service;

import jakarta.servlet.http.HttpSession;

public interface UpdateInfoService {
    Boolean updatetonew(Integer uid,String uname,String ugender,Integer uage, String uintroduce);
}
