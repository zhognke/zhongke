package com.example.busniess.controller;

import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlJump {
    @RequestMapping("/needLogin")
    public ReturnResult  needLogin(){

        return ReturnResult.erro(CodeMsg.NEED_LOGIN);
    }
    @RequestMapping("/permissiondenied")
    public ReturnResult  permissiondenied(){

        return ReturnResult.erro(CodeMsg.NOT_HAVE_LIMITS);
    }
}
