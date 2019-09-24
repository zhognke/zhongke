package com.example.busniess.controller;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.FileUpLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class FileUploadController {
    @Autowired
    FileUpLoad fileUpLoad;


    /**
     * 文件上传地址
     * @param file  //文件名
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/fileUpload")
    public ReturnResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
      String imgAdress=  fileUpLoad.uploadFile(file,request);
      return ReturnResult.success(imgAdress);
    }
}
