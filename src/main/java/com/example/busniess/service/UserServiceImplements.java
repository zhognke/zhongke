package com.example.busniess.service;

import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.User;
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

    public User findUserByName(String userName){
        User user=userDao.selectUser(userName);

        return user;
    }

    /**
     * 查询所有用户
     * @return
     */
    public Map findAllUser(Integer startPage ){
        Map map=new HashMap();
        PageHelper.startPage(startPage, 10);
        List<User> users=userDao.selectAllUser();
        PageInfo pageInfo = new PageInfo(users);
        map.put("data",pageInfo);
        map.put("total",pageInfo.getTotal());
        map.put("pages",pageInfo.getPages());
        map.put("nowpage",pageInfo.getPageNum());
        return map;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    public  Boolean removeUser(Integer id){
        return userDao.delectUser(id);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
  public  Boolean addUser(User user){
        return userDao.insertUser(user);
  }


}
