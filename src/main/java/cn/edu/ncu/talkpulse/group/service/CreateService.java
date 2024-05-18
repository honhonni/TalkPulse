package cn.edu.ncu.talkpulse.group.service;

import jakarta.servlet.http.HttpSession;

public interface CreateService {


    Boolean CreateGroup(Integer group_id, String group_name, String group_introduce, HttpSession session);
    Boolean upphoto(Integer group_id,String group_photo);
}
