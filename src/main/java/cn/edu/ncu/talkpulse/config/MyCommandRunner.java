package cn.edu.ncu.talkpulse.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * spring boot 容器加载后自动监听
 */
@Component
public class MyCommandRunner implements CommandLineRunner {

    @Value("http://localhost/html/index.html")
    private String Url;

    @Value("true")
    private boolean isOpen;

    @Override
    public void run(String... args) {
        if (isOpen) {
            System.out.println("自动加载指定的页面");
            try {
                Runtime.getRuntime().exec("cmd  /c  start  " + Url);  // 可以指定自己的路径
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("浏览器打开页面异常");
            }
        }
    }

}