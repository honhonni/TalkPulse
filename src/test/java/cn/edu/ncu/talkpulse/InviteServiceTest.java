package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.group.entity.Groupapply;
import cn.edu.ncu.talkpulse.group.service.InviteService;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class InviteServiceTest{
    @Autowired
    private InviteService inviteService;
    @Autowired
    private HttpSession session;
    @Test
    public void TestAddGroup(){
        session.setAttribute("user_id",11);
        Boolean flag=inviteService.sendGroupapply(session, LocalDateTime.now(),1,70,"hello");
        System.out.println(flag);
    }
    @Test
    public void TestgetGroupapply(){
        session.setAttribute("user_id",14);
        JSONObject flag= inviteService.getGroupapply(session);
        System.out.println(flag);
    }
}