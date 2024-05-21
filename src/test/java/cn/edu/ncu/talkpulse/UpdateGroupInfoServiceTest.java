package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.group.service.UpdateGroupInfoService;
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
public class UpdateGroupInfoServiceTest {
    @Autowired
    private UpdateGroupInfoService updateGroupInfoService;
    @Autowired
    private HttpSession session;
    @Test
    public void TestUpdate(){
        session.setAttribute("user_id",11);
        Boolean flag=updateGroupInfoService.updateGroupIntroduce("hallo",1,session);
        System.out.println(flag);
    }

}
