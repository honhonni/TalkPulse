package cn.edu.ncu.talkpulse.account.service.impl;

import cn.edu.ncu.talkpulse.account.dao.UpdateAvatarDao;
import cn.edu.ncu.talkpulse.account.dao.UpdateInfoDao;
import cn.edu.ncu.talkpulse.account.service.UpdateAvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service("UpdateAvatarService")
public class UpdateAvatarServiceImpl implements UpdateAvatarService {

    @Autowired
    private UpdateAvatarDao updateAvatarDao;

    @Override
    public Boolean updatephoto(Integer uid, String uphoto) {
        try {


            // 解码Base64字符串为字节数组
            byte[] photoData = Base64.getDecoder().decode(uphoto);

            // 保存图片到指定路径
            String filePath = "src/main/resources/static/images/avatar/img_" + uid + ".png";
            FileOutputStream outputStream = new FileOutputStream(filePath);
            outputStream.write(photoData);
            outputStream.close();
            System.out.println("Image saved successfully at: " + filePath);

            // 更新数据库中的用户头像信息
            int num = updateAvatarDao.updateavatar(uid, filePath);
            return num > 0;
        } catch (IOException e) {
            System.out.println("Error saving image: " + e.getMessage());
            return false;
        }
    }
}

