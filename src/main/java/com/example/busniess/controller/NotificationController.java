package com.example.busniess.controller;

import com.example.busniess.entity.Notification;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.NotificationService;
import com.example.busniess.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/notification")
@Validated
public class NotificationController {
    @Autowired
    NotificationService notificationServiceImpl;
    /**
     * 添加通知
     */
    @RequestMapping("/addNotificatio")
    public ReturnResult addNotification(  @Validated({UserValidator.InSet.class})
                                                      Notification notification){
        if (notificationServiceImpl.addNotification(notification)){
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUBMIT_ERROR);
    }

    /**
     * 删除
     */
@RequestMapping("/delectNotification")
    public ReturnResult delectNotification(@NotNull(message = "id号不能为空") Integer id){
        if (notificationServiceImpl.delectNotification(id)){
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELETE_ERROR);
    }
    /**
     * 修改
      */
    @RequestMapping("/updateNotification")
    public ReturnResult updateNotification(@Validated(UserValidator.UpDate.class) Notification notification){


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
    public ReturnResult selectAllNotificcation(
            @RequestParam(required = false,defaultValue = "1")
                    Integer pageNumber,
            @RequestParam(required = false,defaultValue = "5")
                    Integer pageSize){
        return ReturnResult.success(notificationServiceImpl.selectAllNocation(pageNumber,pageSize));
    }
    /**
     * 查看具体
     */
    @RequestMapping("/selectOneNotification")
    public ReturnResult selectOneNotification(
            @NotNull(message = "id号不能为空")
            Integer id){
        return ReturnResult.success(notificationServiceImpl.selectNocation(id));
    }



}
