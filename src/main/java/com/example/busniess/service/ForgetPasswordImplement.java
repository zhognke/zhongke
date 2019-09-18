package com.example.busniess.service;

import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.User;
import com.example.busniess.exception.MyException;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.utiles.EmailUtiles;
import com.example.busniess.utiles.Md5Utiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.mail.MessagingException;

@Service
public class ForgetPasswordImplement implements ForgetPassword {

    @Autowired
    UserDao userDao;

    /**
     * 忘记密码时候修改密码
     *
     * @return
     */
    public Boolean modifyPassword(String userName) throws MessagingException, MyException {

    User fuser= userDao.selectUser(userName);//根据用户名查询用户
        if(fuser==null){
            throw new MyException(CodeMsg.USER_NOT_EXSIS);
        }



   String password=Md5Utiles.getNum(8);//产生新的密码
    String newPassword=Md5Utiles.returnMd5("md5",password,userName,1024);
        fuser.setPassword(newPassword);//设置新的密码
   if(userDao.updatPassword(fuser)){
       EmailUtiles.sendEmail("您的新密码",fuser.getEmail(),password);
       return true;

   }
        return false;
    }
}
