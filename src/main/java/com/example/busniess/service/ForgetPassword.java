package com.example.busniess.service;

import com.example.busniess.entity.User;
import com.example.busniess.exception.MyException;

import javax.mail.MessagingException;

public interface ForgetPassword {
    /**
     * 忘记密码
     * @param user
     * @param email
     * @return
     * @throws MessagingException
     */
    Boolean modifyPassword(String userName) throws MessagingException, MyException;
}
