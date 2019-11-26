package com.example.busniess.controller;

import com.example.busniess.entity.Notification;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationServiceImpl;
    /**
     * 添加通知
     */
    @RequestMapping("/addNotificatio")
    public ReturnResult addNotification(Notification notification){
        if (notificationServiceImpl.addNotification(notification)){
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUMIT_ERROR);
    }

    /**
     * 删除
     */
@RequestMapping("/delectNotification")
    public ReturnResult delectNotification(Integer id){
        if (notificationServiceImpl.delectNotification(id)){
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELECT_ERROR);
    }
    /**
     * 修改
      */
    @RequestMapping("/updateNotification")
    public ReturnResult updateNotification(Notification notification){


        if(notificationServiceImpl.upNotification(notification)){
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR) ;
    }

    /**
     * 查看所有
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectAllNotificcation")
    public ReturnResult selectAllNotificcation(Integer pageNumber,Integer pageSize){
        return ReturnResult.success(notificationServiceImpl.selectAllNocation(pageNumber,pageSize));
    }
    /**
     * 查看具体
     */
    @RequestMapping("/selectOneNotification")
    public ReturnResult selectOneNotification(Integer id){
        return ReturnResult.success(notificationServiceImpl.selectNocation(id));
    }



}
