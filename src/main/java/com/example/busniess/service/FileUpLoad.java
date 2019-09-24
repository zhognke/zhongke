package com.example.busniess.service;

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

    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {


            String realPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            String fileNme = file.getOriginalFilename();//获取文件名
            String suffixName = fileNme.substring(fileNme.lastIndexOf("."));//后缀
            String newFilename = UUID.randomUUID().toString().replace("-", "") + suffixName;//新文件名
            String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String upPath ="F://myFile//";// "F://myFile//";//上传路径
            String mk = upPath + "//" + now;//文件夹的名字
            File filel = new File(mk, newFilename);
            if (!filel.exists()) {
                filel.mkdirs();
            }
            file.transferTo(filel);
            String path = realPath + "/img/" + now + "/" + newFilename;



        return path;
    }


}
