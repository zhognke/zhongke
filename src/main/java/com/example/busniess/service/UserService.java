package com.example.busniess.service;

import com.example.busniess.entity.User;
import com.example.busniess.exception.MyException;

import java.util.List;
import java.util.Map;

public interface UserService {
    User findUserByName(String userName);

    Boolean removeUser(Integer id);

    Map findAllUser(Integer startPage);

    Boolean addUser(User user) throws MyException;
    Boolean modifiUser(User user);
}
