package com.example.busniess.service;

import com.example.busniess.entity.User;
import com.example.busniess.exception.MyException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {
    User findUserByName(String userName);

    Boolean removeUser(Integer id);

    Map findAllUser(Integer startPage);

    Boolean addUser(User user) throws MyException;

    Boolean modifiUser(User user);

    Set<String> findMyRole(Integer id);

    Set<String> findAllRole();

    Boolean retSetPassword(String userName, String password, String newPassword) throws MyException;
}
