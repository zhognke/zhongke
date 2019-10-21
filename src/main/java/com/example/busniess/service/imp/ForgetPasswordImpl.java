package com.example.busniess.service.imp;

import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.User;
import com.example.busniess.exception.MyException;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.service.ForgetPassword;
import com.example.busniess.utiles.EmailUtiles;
import com.example.busniess.utiles.Md5Utiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@Service
public class ForgetPasswordImpl implements ForgetPassword {

    @Autowired
    UserDao userDao;

    /**
     * 忘记密码时候修改密码
     *
     * @return
     */
//    public Boolean modifyPassword(String userName) throws MessagingException, MyException {
//
//    User fuser= userDao.selectUser(userName);//根据用户名查询用户
//        if(fuser==null){
//            throw new MyException(CodeMsg.USER_NOT_EXSIS);
//        }
//
//
//   String password=Md5Utiles.getNum(8);//产生新的密码
//    String newPassword=Md5Utiles.returnMd5("md5",password,userName,1024);
//        fuser.setPassword(newPassword);//设置新的密码
//
//   if(userDao.updatPassword(fuser)){
//       EmailUtiles.sendEmail("您的新密码",fuser.getEmail(),password);
//       return true;
//
//   }
//        return false;
//    }


    /**
     * 忘记密码
     * 邮箱验证码
     */
public  Boolean checkEmail(String userName,HttpSession session) throws MyException, MessagingException {
    User user=userDao.selectUser(userName);
    if(user==null){
        throw new  MyException(CodeMsg.USER_NOT_EXSIS);
    }
    String mmcode=Md5Utiles.getNum(4);//产生新的验证码
    session.setAttribute("mmcode",mmcode);//放入session
    EmailUtiles.sendEmail("您的验证功码",user.getEmail(),mmcode);
    return true;

}
/**
 * 验证邮箱验证码
 * 并修改密码
 */
public  Boolean upPassword(HttpSession session,String mmCode,User user) throws MyException {
    String smmCode= (String) session.getAttribute("mmcode");
    if(!mmCode.equals(smmCode)){
        throw new MyException(CodeMsg.CODE_ERROR);
    }
User  user1=userDao.selectUser(user.getUserName());//根据用户名或email获取用户
    if(user1==null){
        throw new  MyException(CodeMsg.USER_NOT_EXSIS);//用户不存在
    }
    String ps=user.getPassword();//获取传来的密码
String password=Md5Utiles.returnMd5("md5",ps,user1.getUserName(),1024);//加密
   user.setPassword(password);

    return  userDao.updatPassword(user);//修改密码


}




}
