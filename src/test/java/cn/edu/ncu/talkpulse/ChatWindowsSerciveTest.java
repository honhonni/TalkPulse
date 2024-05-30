package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.account.entity.UserInfo;
import cn.edu.ncu.talkpulse.account.service.ChatWindowsService;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.edu.ncu.talkpulse.dto.ChatWindows;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import java.util.List;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class ChatWindowsSerciveTest {
    @Autowired
    private ChatWindowsService chatWindowsSercive;
    @Autowired
    private HttpServletRequest request;

    @Test
    public void testwindows(){
        List <ChatWindows> ans=chatWindowsSercive.chatwindowset(123456);
     //   for (int i=0;i<ans.size();i++)
       //     System.out.println(ans.get(i).getCompare_time());
        if (ans != null) {
            // 如果你需要将 List<UserInfo> 转换为 JSON 数组，你可以这样做：
            JSONArray jsonArray = new JSONArray();
            for (ChatWindows chatwindows : ans) {
                /*
    private Integer group_id;//群id
    private String group_photo;//群头像
    private String group_name;// 群名称
    private LocalDateTime grouprecord_time;//最新的
    private String grouprecord_content;//最新的
    private Integer  grouprecord_type;//最新的消息类型
    private Boolean group_no_read;// 查询corre表判断是否有新消息，有的话，值设为1
                 */
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("messagetype", chatwindows.getMessagetype());
                jsonObject.put("compare_time", chatwindows.getCompare_time());
                jsonObject.put("user_id", chatwindows.getUser_id());
                jsonObject.put("user_photo", chatwindows.getUser_photo());
                jsonObject.put("user_name", chatwindows.getUser_name());
                jsonObject.put("record_time", chatwindows.getRecord_time());
                jsonObject.put("record_content", chatwindows.getRecord_content());
                jsonObject.put("record_type", chatwindows.getRecord_type());
                jsonObject.put("no_read", chatwindows.getNo_read());
                jsonObject.put("group_id", chatwindows.getGroup_id());
                jsonObject.put("group_photo", chatwindows.getGroup_photo());
                jsonObject.put("group_name", chatwindows.getGroup_name());
                jsonObject.put("grouprecord_time", chatwindows.getGrouprecord_time());
                jsonObject.put("grouprecord_content", chatwindows.getGrouprecord_content());
                jsonObject.put("grouprecord_type", chatwindows.getGrouprecord_type());
                jsonObject.put("group_no_read", chatwindows.getGroup_no_read());
                jsonArray.add(jsonObject);
            }
            System.out.println(jsonArray.toJSONString()); // 打印 JSON 格式的字符串
        } else {
            System.out.println("聊天列表为空");
        }

    }

}