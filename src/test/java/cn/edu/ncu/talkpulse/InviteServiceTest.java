package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.group.service.InviteService;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.time.LocalDateTime.now;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class InviteServiceTest {
    @Autowired
    private InviteService inviteService;
    @Autowired
    private HttpSession session;
    @Test
    public void testInvite(){
        session.setAttribute("user_id",11);
        Boolean flag =inviteService.invite(789,1,session);
        System.out.println(flag);
    }
}
