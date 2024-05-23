package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.friends.entity.Friendship;
import cn.edu.ncu.talkpulse.friends.service.FriendService;
import com.alibaba.fastjson2.JSONObject;
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
public class FriendServiceTest {
    @Autowired
    private FriendService friendService;
    @Autowired
    private HttpSession session;
    @Autowired
    private FriendService friendshipService;

    @Test
    public void testSearch(){
        session.setAttribute("user_id",456);
        JSONObject data  = friendService.search(1, session);
        System.out.println(data);
    }
    @Test
    public void testgetFriendShip(){
        session.setAttribute("user_id",555555);
        JSONObject data  = friendService.getFriendship( session);
        System.out.println(data);
    }
    @Test
    public void testCreatFriendShip(){

        session.setAttribute("creat_id",666666);
        String friendshipName = "测试分组3";
        JSONObject data  = friendshipService.createFriendship(friendshipName ,session);
        System.out.println(data);
    }
}