package cn.edu.ncu.talkpulse;

import cn.edu.ncu.talkpulse.group.service.CreateService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
import java.util.Enumeration;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class CreateGroupServiceTest {
    @Autowired
    private CreateService createService;
    @Autowired
    private HttpSession session;
    @Test
    public void testCreat(){

        session.setAttribute("user_id",11);
        System.out.println(session.getAttribute("user_id"));
        Boolean flag=createService.CreateGroup(99999999,"群","欢迎",session);
        System.out.println(flag);
    }
    @Test
    public void testuppoto(){
        File imageFile=new File("C:\\Users\\XM\\Desktop/\\deng.jpg");
        try{
            byte[] imageBytes=new byte[(int) imageFile.length()];
            FileInputStream fis=new FileInputStream(imageFile);
            fis.read(imageBytes);
            fis.close();
            String base64Image= Base64.getEncoder().encodeToString(imageBytes);
            System.out.println("Base64 encoded image:"+base64Image);
            byte[] decodedBytes=Base64.getDecoder().decode(base64Image);
            String decodedImage=new String(decodedBytes);
            System.out.println("Decoded image:"+decodedImage);
            Boolean flag=createService.upphoto(2,base64Image);
            System.out.println(flag);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
