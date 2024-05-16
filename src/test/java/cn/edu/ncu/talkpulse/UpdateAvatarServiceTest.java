package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.account.service.UpdateAvatarService;
import cn.edu.ncu.talkpulse.account.service.UpdateInfoService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class UpdateAvatarServiceTest {
    @Autowired
    private UpdateAvatarService updateAvatarService;
    @Autowired
    private HttpServletRequest request;

    @Test
    public void testUpdatephoto(){
        File imageFile = new File("C:\\Users\\yuyu\\Desktop\\屏幕截图 2024-05-15 150258.png"); // 替换为你的图片文件路径

        try {
            // 读取图片文件内容
            byte[] imageBytes = new byte[(int) imageFile.length()];
            FileInputStream fis = new FileInputStream(imageFile);
            fis.read(imageBytes);
            fis.close();

            // 将图片内容进行 Base64 编码
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // 将编码后的字符串存储到数据库中的 user_photo 字段中
            // 这里省略数据库操作，你需要根据你的数据库连接方式来存储数据

            System.out.println("Base64 encoded image: " + base64Image);

            // 将Base64编码字符串解码为普通字符串
            byte[] decodedBytes = Base64.getDecoder().decode(base64Image);
            String decodedImage = new String(decodedBytes);

            System.out.println("Decoded image: " + decodedImage);

            Boolean flag = updateAvatarService.updatephoto(1000, base64Image);
            System.out.println(flag);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
