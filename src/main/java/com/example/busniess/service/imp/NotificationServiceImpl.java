package com.example.busniess.service.imp;

import com.example.busniess.dao.NotificationDao;
import com.example.busniess.entity.Notification;
import com.example.busniess.service.NotificationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationServiceImpl implements NotificationService {


    @Autowired
    NotificationDao notificationDao;

    /**
     * 添加
     * @param notification
     * @return
     */
    @Override
    public Boolean addNotification(Notification notification) {

        return  notificationDao.insertNotification(notification);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Boolean delectNotification(Integer id) {

        return notificationDao.delectNotification(id);
    }

    /**
     * 修改
     * @param notification
     * @return
     */
    @Override
    public Boolean upNotification(Notification notification) {
        return notificationDao.upNotification(notification);
    }

    /**
     * 查看所有
     * @return
     */
    @Override
    public PageInfo selectAllNocation(Integer pageNumber,Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
     List<Notification> o= notificationDao.selectAllNotification();
     PageInfo p=new PageInfo(o);
        return p;
    }

    /**
     * 查看单个的
     * @param id
     * @return
     */
    @Override
    public Notification selectNocation(Integer id) {

        return   notificationDao.selectOneNotification(id);
    }
}
