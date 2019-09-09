package com.example.busniess.dao;

import com.example.busniess.entity.User;
import org.apache.ibatis.annotations.Insert;

public interface UserDao {
    /**
     * 名字：新增用户
     * @param user
     * @return
     */
    @Insert("INSERT INTO `user` (`username`, `password`, `phonenumber`) VALUES (#{userName}, #{password}, #{phoneNumber})")
    public  Boolean insertUser(User user);
}
