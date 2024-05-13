package cn.edu.ncu.talkpulse.group.service;

import cn.edu.ncu.talkpulse.group.entity.Groupinfo;

import java.util.List;

public interface GroupService {

    List<Groupinfo> selectAll(Integer groupId);
}
