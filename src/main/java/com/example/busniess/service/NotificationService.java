package com.example.busniess.service;

import com.example.busniess.entity.Notification;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface NotificationService {
    /**
     * 增加
     */
    public Boolean addNotification(Notification notification);
    /**
     * 删除
     */
    public Boolean delectNotification(Integer id);
    /**
     * 修改
     */
    public Boolean upNotification(Notification notification);
    /**
     * 查看所有的
     */
    public PageInfo selectAllNocation(Integer pageNumber,Integer pageSize);
    /**
     * 查看单个
     */
    public  Notification selectNocation(Integer id);


}
