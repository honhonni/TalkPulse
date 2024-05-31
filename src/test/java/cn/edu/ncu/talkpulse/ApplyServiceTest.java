package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.group.entity.GroupApplyWithGroupInfo;
import cn.edu.ncu.talkpulse.group.service.ApplyService;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class ApplyServiceTest {
    @Autowired
    private ApplyService applyService;
    @Autowired
    private HttpSession session;

    //发送添加群聊申请
    @Test
    public void TestAddGroup(){
        session.setAttribute("user_id",12);
        Boolean flag= applyService.sendGroupapply(session, LocalDateTime.now(),99999999,11,"欢迎");
        System.out.println(flag);
    }
    //查看发送的群聊信息
    @Test
    public void getGroupAppliesBySenderId(){
        List<GroupApplyWithGroupInfo> flag= applyService.getGroupAppliesBySenderId(14);
        System.out.println(flag);
    }
    //查看收到的群聊信息
    @Test
    public void getGroupAppliesByReceiver(){
        List<GroupApplyWithGroupInfo> flag= applyService.getMyGroupapply(70);
        System.out.println(flag);
    }
    //群主处理群聊信息
    @Test
    public void handleGroupapply(){
        session.setAttribute("user_id",11);
        Result flag= applyService.handleGroupapply((byte) 1,session);
    }
}