package com.example.busniess.service;

import com.example.busniess.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
   User findUserByName(String userName);
   Boolean removeUser(Integer id);
  Map findAllUser(Integer startPage);
   Boolean addUser(User user);

}
