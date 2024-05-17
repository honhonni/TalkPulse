package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.group.service.UserApplyIntoService;
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
public class UserApplyIntoServiceTest {
    @Autowired
    private UserApplyIntoService userApplyIntoService;
    @Autowired
    private HttpSession session;
    @Test
    public void UserApplyIntoTest(){
        session.setAttribute("user_id",11);
        Boolean flag=userApplyIntoService.UserApplyInto(session,now(),1,"hello");
        System.out.println(flag);
    }
}
