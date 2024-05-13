package cn.edu.ncu.talkpulse.group.service;

import cn.edu.ncu.talkpulse.group.entity.groupinfo;

import java.util.List;

public interface GroupService {

    List<groupinfo> selectAll(int group_hostid);
}
