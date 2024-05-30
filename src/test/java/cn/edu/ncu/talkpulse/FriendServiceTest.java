package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.controller.FriendController;
import cn.edu.ncu.talkpulse.dto.Result;
import cn.edu.ncu.talkpulse.friends.entity.Friendship;
import cn.edu.ncu.talkpulse.friends.service.FriendService;
import com.alibaba.fastjson2.JSONArray;
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
    public void testGetFriendShip(){
        session.setAttribute("user_id",555555);
        JSONObject data  = friendService.getFriendship( session);
        System.out.println(data);
    }
    @Test
    public void testCreatFriendShip(){

        session.setAttribute("user_id",666666);
        String friendshipName = "allalla";
        Result data  = friendshipService.createFriendship(friendshipName ,session);
        System.out.println(data);
    }
    @Test
    public void testGetFriendList(){

        session.setAttribute("user_id",666666);

        JSONArray data  = friendService.getAllFriendshipsAndFriends(session);
        System.out.println(data);


    }

    @Test
    public void testGetUserGroups(){

        session.setAttribute("user_id",666666);

        JSONArray data  = JSONArray.of(friendService.getAllUserGroups(session));
        System.out.println(data);


    }


//    @Test
//    public void testGetRecords(){
//
//        session.setAttribute("你好",666666);
//
//        JSONArray data  = JSONArray.of(friendService.searchRecordsByKeyword("你好",session));
//        System.out.println(data);
//
//
//    }
    //测试根据传入uid返回用户与该uid的聊天记录
    @Test
    public void testGetPrivateMessages(){

        session.setAttribute("user_id",666666);

//        Integer otherUserId =
        Result data  = friendService.getPrivateMessages(888888 ,session);

        System.out.println(data);


    }

    //测试根据传入gid返回用户与该群的聊天记录
    @Test
    public void testGetGroupMessages(){

        session.setAttribute("user_id",666666);

//        Integer otherUserId =
        Result data  = friendService.getGroupMessages(88888888 ,session);

        System.out.println(data);


    }


}