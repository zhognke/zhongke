package com.example.busniess.service;

import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.Person;
import com.example.busniess.entity.User;
import com.example.busniess.exception.MyException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {
    /**
     * 根据用户名搜索用户信息，用户状态1
     * @param userName
     * @return
     */
    User findUserByName(String userName);

    /**
     * 删除用户
     * @param id
     * @return
     */

    Boolean removeUser(Integer id);


    Map findAllUser(Integer startPage);

    Boolean addUser(User user) throws MyException;

    Boolean modifiUser(User user);

    Set<String> findMyRole(String userName);

    Set<String> findAllRole();

    Boolean retSetPassword(String userName, String password, String newPassword) throws MyException;

    Integer checkStatus(String username, Integer isPerson);


    /**
     * 后台检索企业用户
     */
public PageInfo searchBusinessUser(BusinessCenter businessCenter,Integer pageNumber,Integer pageSize);

/**
 * 后台检索个人用户
 */
public  PageInfo searchPerson(Person person,Integer pageNumber,Integer pageSize);

    /**
     * 查找状态不等于3的用户
     * @param userName
     * @return
     */
    public User findAllUserByName(String userName);

}
