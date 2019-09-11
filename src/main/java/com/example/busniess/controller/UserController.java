package com.example.busniess.controller;

import com.example.busniess.entity.User;
import com.example.busniess.exception.MyException;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource(name="userServiceImplements")
    UserService userServiceImplements;

    /**
     * 登录验证
     * @param userName
     * @param password
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping("/userLogin")
    public ReturnResult userLogin(String userName, String password) throws AuthenticationException {
        Subject subject = SecurityUtils.getSubject();//获取subject对象
        if (subject.isAuthenticated()) {
            return ReturnResult.success();
        }
        UsernamePasswordToken up = new UsernamePasswordToken(userName, password);
        subject.login(up);
        return ReturnResult.success();
    }

    /**
     * 注册用户
     * @param user
     * @return
     * @throws MyException
     */
    @RequestMapping("/registerUser")
    public ReturnResult registerUser(User user) throws MyException {

        userServiceImplements.addUser(user);

        return ReturnResult.success();
    }



public  ReturnResult modifiUser(){
        return ReturnResult.success();
}



}
