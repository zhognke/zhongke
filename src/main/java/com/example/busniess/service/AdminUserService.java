package com.example.busniess.service;

import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.Person;
import com.example.busniess.entity.User;
import com.github.pagehelper.PageInfo;


public interface AdminUserService {

    /**
     * 修改企业用户信息
     */
    public boolean adminUpUser(User user, BusinessCenter businessCenter);

    /**
     * 增加企业用户
     *
     * @param user
     * @param businessCenter
     * @return
     */
    public boolean adminAddUser(User user, BusinessCenter businessCenter);

    /**
     * 删除用户逻辑删除名下所有的发布
     * @return
     */
    public boolean adminDelletUser(User user);

    /**
     * 增加私人用户
     * @param user
     * @param person
     * @return
     */
    public Boolean addPersonUser(User user, Person person);

    /**
     * 跟新私人用户信息
     * @param user
     * @param person
     * @return
     */
    public  Boolean upPerSonUser(User user,Person person);





}
