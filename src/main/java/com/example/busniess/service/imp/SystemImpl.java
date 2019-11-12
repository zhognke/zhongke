package com.example.busniess.service.imp;

import com.example.busniess.dao.SystemDao;
import com.example.busniess.entity.System;
import com.example.busniess.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SystemImpl implements SystemService {
    @Autowired
    SystemDao systemDao;

    /**
     * 新增
     * @param url
     * @return
     */
    @Override
    public Boolean addSystem(List<String> url) {

        return  systemDao.insertSystem(url);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Boolean delectSystem(Integer id) {

        return   systemDao.delectSysem(id);
    }

    /**
     * 更新
     * @param system
     * @return
     */
    @Override
    public Boolean upDateSystem(List<System> system) {

        return systemDao.updateSystem(system);
    }

    /**
     * 查看所有的
     * @return
     */
    @Override
    public List<System> selectSystem() {
        return systemDao.selectSystem();
    }
}
