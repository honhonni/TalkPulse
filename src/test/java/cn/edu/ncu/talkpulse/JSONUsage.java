package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.group.entity.groupinfo;
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

        System.out.println("��ɫ���治�ùܣ���ž���˵����������������");
        System.out.println("��ȡ����ɫ���棬��vm�����ϼ�-Xshare:off  -XX:+EnableDynamicAgentLoading");
        System.out.println("vm��ʲô���Լ���");

//        json����ת����ʽ
//        ��groupinfo��������
//        �����ָ��ݲ���group_hostid׼ȷ��ѯ����һ�����ݵģ���Ҫ�� list
        JSONObject response = new JSONObject();
        response.put("status", "201");
        response.put("messages", "success");
        List<groupinfo> list=groupService.selectAll(1);
        response.put("data", list);

        System.out.println(response);
    }
}