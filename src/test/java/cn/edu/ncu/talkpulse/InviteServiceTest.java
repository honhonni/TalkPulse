package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.dto.ValidationReceiverDTO;
import cn.edu.ncu.talkpulse.group.entity.GroupApplyWithGroupInfo;
import cn.edu.ncu.talkpulse.group.entity.Groupapply;
import cn.edu.ncu.talkpulse.group.service.InviteService;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
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
public class InviteServiceTest{
    @Autowired
    private InviteService inviteService;
    @Autowired
    private HttpSession session;

    //发送添加群聊申请
    @Test
    public void TestAddGroup(){
        session.setAttribute("user_id",11);
        Boolean flag=inviteService.sendGroupapply(session, LocalDateTime.now(),1,70,"hello");
        System.out.println(flag);
    }
    //查看发送的群聊信息
    @Test
    public void getGroupAppliesBySenderId(){
        List<GroupApplyWithGroupInfo> flag= inviteService.getGroupAppliesBySenderId(14);
        System.out.println(flag);
    }
    //查看收到的群聊信息
    @Test
    public void getGroupAppliesByReceiver(){
        List<GroupApplyWithGroupInfo> flag=inviteService.getMyGroupapply(123456);
        System.out.println(flag);
    }
}