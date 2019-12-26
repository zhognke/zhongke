package com.example.busniess.service;

import com.example.busniess.entity.Manager;
import com.example.busniess.exception.MyException;

import java.util.List;

public interface ManagerService {

    /**
     * 根据用户名获取用户
     */

    public Manager selectManager(String username);


    /**
     * 注册用户
     */
    public boolean insertManager(Manager manager) throws MyException;

    /**
     * 修改用户
     */
    public boolean updateManager(Manager manager);

    /**
     * 删除
     */
    public boolean delectManager(Integer id);
/**
 * 按条件查看
 */
public List<Manager> selectManagerBycondition(Manager manager);
}
