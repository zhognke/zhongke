package com.example.busniess.service;

import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.User;


public interface AdminUserService {

    /**
     * 修改企业用户信息
     */
public boolean adminUpUser(User user, BusinessCenter businessCenter);

    /**
     * 增加企业用户
     * @param user
     * @param businessCenter
     * @return
     */
    public boolean adminAddUser(User user, BusinessCenter businessCenter);

}
