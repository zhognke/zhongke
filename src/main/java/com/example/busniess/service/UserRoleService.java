package com.example.busniess.service;

import com.example.busniess.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    /**
     * 增加角色和用户的关联关系
     */
    public  boolean insertUserRoler(UserRole userRole);
    /**
     * 修改
     */
    public  boolean upUserRoler(UserRole userRole);
    /**
     * 删除
     */
    public boolean delectUserroler(Integer id);
    /**
     * 按条件查看关联关系
     */
    public List<UserRole> selectUserRoler(UserRole userRole);
}
