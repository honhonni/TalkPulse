package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.group.entity.groupinfo;
import cn.edu.ncu.talkpulse.group.service.GroupService;
import cn.edu.ncu.talkpulse.user.entity.UserInfo;
import cn.edu.ncu.talkpulse.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class UserServiceTest{
    @Autowired
    private UserService userService;
    @Test
    public void setUp() throws Exception{
        UserInfo userInfo=userService.findById(1);
        System.out.println(userInfo);
    }
}