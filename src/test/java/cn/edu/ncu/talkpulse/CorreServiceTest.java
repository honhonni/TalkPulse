package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.group.service.CorreService;
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
        session.setAttribute("group_id", 32121311);
        List<UserInfo> userInfoList = correService.getgroup(session); // 假设 getgroup 返回 List<UserInfo>
        if (userInfoList != null) {
            // 如果你需要将 List<UserInfo> 转换为 JSON 数组，你可以这样做：
            JSONArray jsonArray = new JSONArray();
            for (UserInfo userInfo : userInfoList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("groupmember_id", userInfo.getUser_id());
                jsonObject.put("groupmember_name", userInfo.getUser_name());
                jsonObject.put("groupmember_photo", userInfo.getUser_photo());
                jsonArray.add(jsonObject);
            }
            System.out.println(jsonArray.toJSONString()); // 打印 JSON 格式的字符串
        } else {
            System.out.println("用户信息列表为空");
        }
    }
}
