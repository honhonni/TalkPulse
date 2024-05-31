package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.group.service.GroupValidationService;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class GroupValidation {
    @Autowired
    private GroupValidationService groupValidationService;
    @Autowired
    private HttpSession session;
    @Test
    public void testaddvalidation(){
        session.setAttribute("user_id",11);
        Boolean flag= groupValidationService.groupaddvalidation(session,13,99999999, LocalDateTime.now());
        System.out.println(flag);
    }
    @Test
    public void testhandlevalidation(){
        Boolean flag=groupValidationService.handlegroupvalidation((byte) 1,1);
        System.out.println(flag);
    }
}
