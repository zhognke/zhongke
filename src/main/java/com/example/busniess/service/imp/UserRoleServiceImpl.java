package com.example.busniess.service.imp;

import com.example.busniess.dao.UserRroleDao;
import com.example.busniess.entity.UserRole;
import com.example.busniess.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRoleServiceImpl implements UserRoleService {


    @Autowired
    UserRroleDao userRroleDao;
    /**
     * 新建角色关联关系
     * @param userRole
     * @return
     */
    @Override
    public boolean insertUserRoler(UserRole userRole) {

        return userRroleDao.insertRolerUser(userRole);
    }

    /**
     * 修改关联关联关系
     * @param userRole
     * @return
     */
    @Override
    public boolean upUserRoler(UserRole userRole) {


        return userRroleDao.upRoler(userRole);
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @Override
    public boolean delectUserroler(Integer id) {

        return userRroleDao.delectRoler(id);
    }

    /**
     * 查看角色
     * @param userRole
     * @return
     */
    @Override
    public List<UserRole> selectUserRoler(UserRole userRole) {
        return userRroleDao.selectAllUserRole(userRole);
    }
}
