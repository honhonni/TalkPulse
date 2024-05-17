package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.group.service.UpdateInviteService;
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
public class UpdateinviteServiceTest {
    @Autowired
    private UpdateInviteService updateinviteServiceTest;
    @Autowired
    private HttpSession session;
    @Test
    public void testUpdateInvite(){
        session.setAttribute("user_id",11);
        Boolean flag =updateinviteServiceTest.updateinvite(1,1,session,1,"wfewf","rfwer",now());
        System.out.println(flag);
    }
}
