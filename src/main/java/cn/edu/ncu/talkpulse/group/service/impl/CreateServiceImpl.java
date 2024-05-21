package cn.edu.ncu.talkpulse.group.service.impl;

import cn.edu.ncu.talkpulse.group.dao.CorreDao;
import cn.edu.ncu.talkpulse.group.dao.CreateDao;
import cn.edu.ncu.talkpulse.group.entity.Groupinfo;
import cn.edu.ncu.talkpulse.group.service.CreateService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service("CreateGroup")
public class CreateServiceImpl implements CreateService {
    @Autowired
    private CreateDao createDao;
    @Autowired
    private CorreDao correDao;
    @Override
    public Boolean CreateGroup(Integer group_id,String group_name,String group_introduce, HttpSession session){
        Groupinfo group= createDao.findGroupGId(group_id);
        if(group!=null){
            return false;
        }else{
            Integer group_hostid = (Integer) session.getAttribute("user_id");
            Groupinfo groupinfo = new Groupinfo(group_id,group_name,group_introduce,group_hostid);
            int res=createDao.CreateGroup(groupinfo);
            if(res==1){
                correDao.addcorre(group_hostid,group_id);
                return true;
            }
            else return false;
        }
    }
    @Override
    public Boolean upphoto(Integer group_id,String group_photo){
        try{
            byte[] photoData= Base64.getDecoder().decode(group_photo);
            String filePath = "/images/group/img_" + group_id + ".png";
            FileOutputStream outputStream = new FileOutputStream("src/main/resources/static"+filePath);
            outputStream.write(photoData);
            outputStream.close();
            int num= createDao.upphoto(group_id,filePath);
            return num>0;
        }catch (IOException e){
            System.out.println("Error saving image:"+e.getMessage());
            return false;
        }
    }
}
