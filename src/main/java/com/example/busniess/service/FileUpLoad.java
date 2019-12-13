package com.example.busniess.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileUpLoad {

  @Value("${upload.path}")
  String upPath;
//    String upPath="D://appDate";

    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
//            String realPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            String fileNme = file.getOriginalFilename();//获取文件名
            String suffixName = fileNme.substring(fileNme.lastIndexOf("."));//后缀
            String newFilename = UUID.randomUUID().toString().replace("-", "") + suffixName;//新文件名
            String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String mk = upPath + "//" + now;//文件夹的名字
            File filel = new File(mk);
            if (!filel.exists()) {
                filel.mkdirs();
            }
        filel = new File(mk,newFilename);
            file.transferTo(filel);
            String path = "/img/" + now + "/" + newFilename;
        return path;
    }

    public String upload( @RequestParam("file")MultipartFile file) throws IOException {



//            String realPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String fileNme = file.getOriginalFilename();//获取文件名
        String suffixName = fileNme.substring(fileNme.lastIndexOf("."));//后缀
        String newFilename = UUID.randomUUID().toString().replace("-", "") + suffixName;//新文件名
        String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String path = upPath + "/" + now;//文件夹的名字

        File filel = new File(path);
        if (!filel.exists()) {
            filel.mkdirs();
        }
        filel = new File(path,newFilename);
        file.transferTo(filel);

        return path+"/"+newFilename;
    }
}
