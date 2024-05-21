package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.group.service.HostapplyService;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class HostapplyServiceTest {
    @Autowired
    private HostapplyService hostapplyService;
    @Autowired
    private HttpSession session;
    @Test
    public void TestHostapply(){
        session.setAttribute("user_id",11);
        Boolean flag=hostapplyService.hostapply(11,session);
    }
    @Test
    public void TestHostset(){
        session.setAttribute("user_id",11);
        Boolean flag=hostapplyService.hostset(true,true,11,session);
    }
}
