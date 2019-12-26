package com.example.busniess.dao;

import com.example.busniess.entity.Manager;
import com.example.busniess.entity.ManagerRoler;

import java.util.List;

public interface ManagerRolerDao {

    /**
     * 批量插入
     */
    public Boolean insertManagerRoler(List<ManagerRoler> managerRolers);

    /**
     * 批量删除
     */
    public boolean delectManagerRoler(List<Integer> id);

    /**
     * 批量修改
     */
    public boolean updateManagerRoler(List<ManagerRoler> managerRolers);

    /**
     * 查看
     */
    public List<ManagerRoler> selectManagerRoler(ManagerRoler managerRoler);
}
