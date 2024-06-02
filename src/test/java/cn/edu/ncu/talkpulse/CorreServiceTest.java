package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.group.service.CorreService;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class CorreServiceTest {
    @Autowired
    private CorreService correService;
    @Autowired
    private HttpSession session;
    @Test
    public void testGroup(){
        Result result = correService.getmember(12345678); // 假设 getgroup 返回 List<UserInfo>
        System.out.println(JSON.toJSON(result)); // 打印 JSON 格式的字符串
    }
}
