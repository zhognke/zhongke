package com.example.busniess.service;

import com.example.busniess.dao.SystemDao;
import com.example.busniess.entity.System;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface SystemService {


    /**
     * 增加
     */
    public Boolean addSystem(List<String> url);
    /**
     * 删除
     */
    public Boolean delectSystem(Integer id);
    /**
     * 批量修改
     */
    public  Boolean upDateSystem(List<System> system);

    /**
     * 查询
     */
    public  List<System> selectSystem();
}
