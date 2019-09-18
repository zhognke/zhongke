package com.example.busniess.controller;

import com.example.busniess.entity.User;
import com.example.busniess.exception.MyException;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.ForgetPassword;
import com.example.busniess.service.UserService;
import com.example.busniess.validator.UserValidator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Resource(name="userServiceImplements")
    UserService userServiceImplements;
    @Resource
    ForgetPassword ForgetPasswordImplement;

    /**
     * 登录验证
     * @param userName
     * @param password
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping("/userLogin")
    public ReturnResult userLogin(@NotBlank(message = "名字不能为空")String userName,
                                  @NotBlank(message = "密码不能为空")String password,
                                  @RequestParam(value = "remb",defaultValue = "false",
                                          required = false) Boolean remb) throws ShiroException {
        Subject subject = SecurityUtils.getSubject();//获取subject对象
        if (subject.isAuthenticated()) {
            return ReturnResult.success();
        }
        UsernamePasswordToken up = new UsernamePasswordToken(userName, password);

        subject.login(up);
        up.setRememberMe(remb);//记住我
        //登录成功
        User user = userServiceImplements.findUserByName(userName);
        return ReturnResult.success(user.getUserName());
    }

    /**
     * 注册用户
     * @param user
     * @return
     * @throws MyException
     */
    @RequestMapping("/registerUser")
    public ReturnResult registerUser(@Validated({UserValidator.InSet.class}) User user) throws MyException {

        userServiceImplements.addUser(user);
//注册成功
        return ReturnResult.success(user.getUserName());
    }


/**
 * 忘记密码找回
 */
@RequestMapping("/retrievePassword")
public ReturnResult retrievePassword(String userName) throws MessagingException, MyException {
   if(ForgetPasswordImplement.modifyPassword(userName)){
       return ReturnResult.success();
   }else {
       return ReturnResult.erro(CodeMsg.FIND_PASSWORD_ERROR);
   }

}

/**
 * 修改密码
 */
@RequestMapping("/updataPassword")
public ReturnResult updataPassword(@NotBlank(message = "用户名不能为空") String userName,@NotBlank(message = "原密码不能为空")String password,@NotBlank(message = "新密码不能为空")String newPassword) throws MyException {

if(userServiceImplements.retSetPassword(userName,password,newPassword)) {
    return ReturnResult.success();
}else {
    return ReturnResult.erro(CodeMsg.UPDATE_PASSWORD_ERROR);
}
}

}
