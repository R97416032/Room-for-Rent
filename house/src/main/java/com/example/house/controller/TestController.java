package com.example.house.controller;



import com.example.house.mapper.ManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;


/*
 * 控制层
 * */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/uploadImg")
    public String uploadImg(MultipartFile uploadFile,int roomid,HttpServletRequest req){
        System.out.println("QQQ:>>"+roomid);
        String fileName = uploadFile.getOriginalFilename();//获取文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));//获取文件后缀名
        fileName = roomid+suffixName;//重新生成文件名
        String filePath = "C:/Users/17999/Desktop/imgs/";//指定本地文件夹存储图片
        File file = new File(filePath,fileName);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            //将图片保存到文件夹里
            file.createNewFile();
            uploadFile.transferTo(new File(filePath+fileName));
            return  "http://"+req.getRemoteHost()+":"+req.getServerPort()+"/"+fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    @Autowired
    private ManageMapper s;
    @RequestMapping("/getMaxRoomId")
    public int getMaxRoomId(){
        System.out.println("AA"+s.getMaxRoomId());
        return s.getMaxRoomId();
    }





}
