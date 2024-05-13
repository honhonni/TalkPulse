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

        System.out.println("红色警告不用管，大概就是说你这个东西快过期了");
        System.out.println("想取消红色警告，在vm配置上加-Xshare:off  -XX:+EnableDynamicAgentLoading");
        System.out.println("vm是什么，自己搜");
        System.out.println("hello");
    }
}