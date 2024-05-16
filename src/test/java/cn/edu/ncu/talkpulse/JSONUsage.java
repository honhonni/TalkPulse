package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.service.GroupService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
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
public class JSONUsage {

    @Autowired
    private GroupService groupService;
    @Test
    public void setUp() throws Exception {

        System.out.println("红色警告不用管，大概就是说你这个东西快过期了");
        System.out.println("想取消红色警告，在vm配置上加-Xshare:off  -XX:+EnableDynamicAgentLoading");
        System.out.println("vm是什么，自己搜");

//        json数据转换格式
//        用groupinfo做测试了
//        像这种根据参数group_hostid准确查询具体一条数据的，不要用 list
        JSONObject response = new JSONObject();
        response.put("status", "201");
        response.put("messages", "success");
        List<Groupinfo> list=groupService.selectAll(1);
        response.put("data", list);

        System.out.println(response);
    }
}