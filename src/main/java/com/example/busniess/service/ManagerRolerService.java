package com.example.busniess.service;

import com.example.busniess.entity.ManagerRoler;

import java.util.List;

public interface ManagerRolerService {
    /**
     * 插入
     *
     * @param managerRolers
     * @return
     */
    public Boolean insertManagerRoler(List<ManagerRoler> managerRolers);

    /**
     * 修改
     */
    public boolean updateManagerRoler(List<ManagerRoler> managerRolers);

    /**
     * 删除
     */
    public boolean delectManagerRoler(List<Integer> ids);

    /**
     * 查看按条件查看
     */
    public List<ManagerRoler> selectManagerRolerByCondition(ManagerRoler managerRoler);
}
