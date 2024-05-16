package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.account.service.UpdateInfoService;
import cn.edu.ncu.talkpulse.account.service.UpdatePwdService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class UpdatePwdServiceTest {
    @Autowired
    private UpdatePwdService updatePwdService;
    @Autowired
    private HttpServletRequest request;

    @Test
    public void testnewpwd(){
        Boolean flag = updatePwdService.checkpwd(1005, "123456");
        if (flag)
        {
            Boolean flag2 = updatePwdService.newpwd(1005, "123456", "aaaaaa");
            System.out.println(flag2);
        }
        else
            System.out.println("oldpwd is wrong");
    }

}
