package com.example.busniess.controller;

import com.example.busniess.entity.Bsystem;
import com.example.busniess.entity.System;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    SystemService SystemImpl;
    /**
     * 添加系统
     * 系统名
     */
    @RequestMapping("/addSystem")
    public ReturnResult addSystem(@RequestParam("url")List<String> url){
      if(SystemImpl.addSystem(url)) {
          return ReturnResult.success();
      }
      return ReturnResult.erro(CodeMsg.SUBMIT_ERROR);
    }
    /**
     * 删除系统
     */
    @RequestMapping("/delectSystem")
    public ReturnResult delectSystem(Integer id){
        if(SystemImpl.delectSystem(id)){
        return ReturnResult.success();}
        return ReturnResult.erro(CodeMsg.DELETE_ERROR);
    }
    /**
     * 修改系统显示问题
     * statue状态
     * id
     */
    @RequestMapping("/upDateSystem")
    public  ReturnResult upDateSystem(Bsystem bsystem){
     List<System> system= bsystem.getSystems();
        if(SystemImpl.upDateSystem(system)){
            return ReturnResult.success();
        }
        return  ReturnResult.erro(CodeMsg.UPDATE_ERROR);

    }
    /**
     * 查看所有系统
     * 1显示
     * 0禁用
     */
    @RequestMapping("/selectSystem")
    public  ReturnResult selectSystem(){
        return ReturnResult.success( SystemImpl.selectSystem());

    }
}
