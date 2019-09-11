package com.example.busniess.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
@RequestMapping("/userLogin")
    public  String userLogin(String userName,String password) throws Exception{


            Subject subject= SecurityUtils.getSubject();//获取subject对象

            if(subject.isAuthenticated()){
                return "成功";
            }
            UsernamePasswordToken up=new UsernamePasswordToken(userName,password);

            subject.login(up);
            return "成功";




    }


}
