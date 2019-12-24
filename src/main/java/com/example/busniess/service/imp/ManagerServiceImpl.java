package com.example.busniess.service.imp;

import com.example.busniess.dao.ManagerDao;
import com.example.busniess.entity.Manager;
import com.example.busniess.entity.User;
import com.example.busniess.exception.MyException;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.service.ManagerService;
import com.example.busniess.utiles.Md5Utiles;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {
    @Autowired
            ManagerDao managerDao;

    /**
     * 根据名字查询管理员账号信息
     * @param username
     * @return
     */
    @Override
    public Manager selectManager(String username) {
     return    managerDao.selectManagerByManage(username);
    }

    /**
     * 添加用户
     */
    public   boolean insertManager(Manager manager) throws MyException {
        //1.查一下一下数据库账号是否已经注册了确保唯一性
        String name = manager.getManage();//账户号

       Manager manager1= managerDao.selectManagerByManage(name);
        if (manager1 != null||name.contains("admin")) {
            throw new MyException(CodeMsg.USER_ALREADY_EXISTS);
        }


        String password = manager.getPassword();//密码

        manager.setPassword(Md5Utiles.returnMd5("md5", password, name, 1024));

        return managerDao.insertManager(manager);
    }

    /**
     * 修改用户
     * @param manager
     * @return
     */

    @Override
    public boolean updateManager(Manager manager) {
        String password=manager.getPassword();
        String manage=manager.getManage();
        manager.setPassword(Md5Utiles.returnMd5("md5", password, manage, 1024));

        return managerDao.upDateManager(manager);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public boolean delectManager(Integer id) {
        return managerDao.delectManager(id);
    }

    /**
     * 查看可以按条件
     * @param manager
     * @return
     */
    @Override
    public List<Manager> selectManager(Manager manager) {
        return managerDao.selectManager(manager);
    }
}
