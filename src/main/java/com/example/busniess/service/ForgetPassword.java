package com.example.busniess.service;

import com.example.busniess.entity.User;
import com.example.busniess.exception.MyException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

public interface ForgetPassword {
    Boolean checkEmail(String userName,HttpSession session) throws MyException, MessagingException;
    /**
     *
     * @param session
     * @param mmCode
     * @param userName
     * @param newPassword
     * @return
     * @throws MyException
     */
   // Boolean modifyPassword(String userName) throws MessagingException, MyException;
    Boolean upPassword(HttpSession session, String mmCode,User user) throws MyException;
}
