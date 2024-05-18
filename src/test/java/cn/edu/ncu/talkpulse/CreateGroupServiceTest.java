package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.group.service.CreateService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Enumeration;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class CreateGroupServiceTest {
    @Autowired
    private CreateService createService;
    @Autowired
    private HttpSession session;
    @Test
    public void testCreat(){

        session.setAttribute("user_id",11);
        System.out.println(session.getAttribute("user_id"));
        Boolean flag=createService.CreateGroup(99999999,"群","欢迎",session,"should");
        System.out.println(flag);
    }
}
