package com.example.busniess.service;

import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.User;
import com.example.busniess.exception.MyException;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.utiles.Md5Utiles;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImplements implements UserService {
    @Autowired
    UserDao userDao;

    /**
     * 按名字查询用户
     */

    public User findUserByName(String userName) {
        User user = userDao.selectUser(userName);

        return user;
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    public Map findAllUser(Integer startPage) {
        Map map = new HashMap();
        PageHelper.startPage(startPage, 10);
        List<User> users = userDao.selectAllUser();
        PageInfo pageInfo = new PageInfo(users);
        map.put("data", pageInfo);
        map.put("total", pageInfo.getTotal());
        map.put("pages", pageInfo.getPages());
        map.put("nowpage", pageInfo.getPageNum());
        return map;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public Boolean removeUser(Integer id) {
        return userDao.delectUser(id);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    public Boolean addUser(User user) throws MyException {
        //1.查一下一下数据库账号是否已经注册了
        String name = user.getUserName();
        User user1 = findUserByName(name);
        if (user1 != null) {
            throw new MyException(CodeMsg.USER_ALREADY_EXSIS);
        }
        String password = user.getPassword();//密码

        user.setPassword(Md5Utiles.returnMd5("md5", password, name, 1024));

        return userDao.insertUser(user);
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    public Boolean modifiUser(User user) {
        String password=user.getPassword();
        if(password!=null&&password!=""){
            System.out.println("1231231");
         password = Md5Utiles.returnMd5("md5", user.getPassword(), user.getUserName(), 1024);
        user.setPassword(password);
        }
        return userDao.updateUser(user);
    }



}
