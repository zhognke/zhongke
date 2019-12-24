package com.example.busniess.controller.manager;

import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.shiroutil.UsernamePasswordByUserTypeToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class ManagerController {
    @RequestMapping("/adminLogin")
    public ReturnResult adminLogin(String userName, String password) {
        Subject subject = SecurityUtils.getSubject();//获取subject对象
        UsernamePasswordByUserTypeToken up = new UsernamePasswordByUserTypeToken(userName, password, "admin");

        subject.login(up);
        return ReturnResult.success();

    }

}
