package com.example.busniess.service;

import com.example.busniess.entity.Roler;

import java.util.List;

public interface RolerService {

    /**
     * 增加
     */
    public boolean insertRoler(Roler roler);

    /**
     * 删除
     */
    public boolean delectRoler(Integer id);

    /**
     * 修改
     */
    public boolean updateRoler(Roler roler);

    /**
     * 查询角色
     * @return
     */
    public List<Roler> selectRoler();

    /**
     * 插入角色
     * @param rid
     * @param userName
     * @return
     */
    public  Boolean insertRoler(Integer rid,String userName);
}
