package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.account.service.AccountService;
import cn.edu.ncu.talkpulse.group.service.GroupService;
import jakarta.servlet.http.HttpServletRequest;
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
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    private HttpServletRequest request;

    @Test
    public void testRegister(){
        Boolean flag = accountService.register(1, "hk", "123456");
        System.out.println(flag);
    }

    @Test
    public void testLogin(){
        Boolean flag = accountService.login(1, "W848oa35qR", request.getSession());
        System.out.println(flag);
        System.out.println(request.getSession().getAttribute("user_id"));
    }
}
