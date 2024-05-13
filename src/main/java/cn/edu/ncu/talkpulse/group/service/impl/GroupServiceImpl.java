package cn.edu.ncu.talkpulse.group.service.impl;


import cn.edu.ncu.talkpulse.group.dao.GroupDao;
import cn.edu.ncu.talkpulse.group.entity.groupinfo;
import cn.edu.ncu.talkpulse.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GroupService")
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDao groupDao;

    @Override
    public List<groupinfo>selectAll(int group_hostid){
        return groupDao.selectAll(group_hostid);
    }




}
