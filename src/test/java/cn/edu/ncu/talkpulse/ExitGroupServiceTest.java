package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.group.service.ExitService;
import jakarta.servlet.ServletContext;
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
public class ExitGroupServiceTest {
    @Autowired
    private ExitService exitService;
    @Autowired
    private HttpSession session;

    @Test
    public void testExit() {//解散群聊和退出群聊
        session.setAttribute("user_id",11);
        Boolean flag = exitService.exitGroup(99999999, session);
        System.out.println(flag);
    }
    @Test
    public void testkickmember(){
        session.setAttribute("user_id",11);
        Boolean flag=exitService.kickmember(12,99999999,session);
        System.out.println(flag);
    }
}
