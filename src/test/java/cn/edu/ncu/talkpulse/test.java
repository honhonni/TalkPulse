package cn.edu.ncu.talkpulse;

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
public class test {

    @Test
    public void setUp() throws Exception {

        System.out.println("��ɫ���治�ùܣ���ž���˵����������������");
        System.out.println("��ȡ����ɫ���棬��vm�����ϼ�-Xshare:off  -XX:+EnableDynamicAgentLoading");
        System.out.println("vm��ʲô���Լ���");
        System.out.println("hello");
    }
}