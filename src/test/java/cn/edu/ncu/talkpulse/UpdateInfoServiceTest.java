package cn.edu.ncu.talkpulse;


import cn.edu.ncu.talkpulse.account.service.AccountService;
import cn.edu.ncu.talkpulse.account.service.UpdateInfoService;
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
public class UpdateInfoServiceTest {
    @Autowired
    private UpdateInfoService updateInfoService;
    @Autowired
    private HttpServletRequest request;

    @Test
    public void testUpdatetonew(){
        Boolean flag = updateInfoService.updatetonew(1000, "yuyu", "男",11,"你好！交朋友吗？");
        System.out.println(flag);
    }


}
