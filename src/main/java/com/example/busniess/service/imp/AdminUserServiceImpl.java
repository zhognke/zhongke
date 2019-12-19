package com.example.busniess.service.imp;

import com.example.busniess.dao.BusinessCenterDao;
import com.example.busniess.dao.PersonDao;
import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.Person;
import com.example.busniess.entity.User;
import com.example.busniess.service.AdminUserService;

import com.example.busniess.utiles.Md5Utiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    UserDao userDao;
    @Autowired
    BusinessCenterDao businessCenterDao;
    @Autowired
    PersonDao personDao;

    /**
     * 修改企业用户
     *
     * @param user
     * @param businessCenter
     * @return
     */
    @Override
    @Transactional
    public boolean adminUpUser(User user, BusinessCenter businessCenter) {
        user.setPassword(Md5Utiles.returnMd5("md5",
                user.getPassword(), user.getUserName(), 1024));
        if (userDao.updatPassword(user)) {
            return businessCenterDao.updateBusinessCenter(businessCenter);
        }
        return false;
    }

    /**
     * 增加企业用户
     */
    @Transactional
    public boolean adminAddUser(User user, BusinessCenter businessCenter) {
        user.setPersion(2);
        user.setPassword(Md5Utiles.returnMd5("md5",
                user.getPassword(), user.getUserName(), 1024));
        if (userDao.insertUser(user)) {
            return businessCenterDao.insertBusinessCenter(businessCenter);
        }

        return false;
    }

    /**
     * 删除用户
     * 冻结用户
     * @param user
     * @return
     */
    @Override
    public boolean adminDelletUser(User user) {

        return  userDao.updateUser(user);
    }

    /**
     * 增加个人用户
     * @param user
     * @param person
     * @return
     */
    @Override
    public Boolean addPersonUser(User user, Person person) {
        user.setPersion(1);
        user.setPassword(Md5Utiles.returnMd5("md5",
                user.getPassword(), user.getUserName(), 1024));
        if(userDao.insertUser(user)){
            return personDao.insertPerson(person);
        }

        return false;
    }

    /**
     * 修改个人信息
     * @param user
     * @param person
     * @return
     */
    @Override
    public Boolean upPerSonUser(User user, Person person) {
        user.setPassword(Md5Utiles.returnMd5("md5",
                user.getPassword(), user.getUserName(), 1024));
     if(userDao.updatPassword(user)){
       return   personDao.updatePerson(person);
     }

        return false;
    }
}
