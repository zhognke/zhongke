package com.example.busniess.controller;

import com.example.busniess.entity.MsendMail;
import com.example.busniess.entity.User;
import com.example.busniess.exception.MyException;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.ForgetPassword;
import com.example.busniess.service.MsendMailServiceImplements;
import com.example.busniess.service.UserService;
import com.example.busniess.utiles.EmailUtiles;
import com.example.busniess.utiles.Md5Utiles;
import com.example.busniess.validator.UserValidator;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Resource(name = "userServiceImplements")
    UserService userServiceImplements;
    @Resource
    ForgetPassword ForgetPasswordImplement;

    @Resource(name="msendMailServiceImplements")
    MsendMailServiceImplements msendMailServiceImplements;

    /**
     * 登录验证
     *
     * @param userName
     * @param password
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping("/userLogin")
    public ReturnResult userLogin(@NotBlank(message = "名字不能为空") String userName,
                                  @NotBlank(message = "密码不能为空") String password,
                                  @RequestParam(value = "remb", defaultValue = "false",
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
     * 发送邮箱验证码
     * @param session
     * @param email 收件箱
     * @return
     */
    @PostMapping("/sendCode")
    public ReturnResult sendCode(HttpSession session,String email){
        String peopleCode = Md5Utiles.getNum(6);
        User user = new User();
        user.setEmail(email);
        user.setUserName(peopleCode);
        user.setLastdate(new Date());
        session.setAttribute("REGISTER_CODE_SESSION",user);         //存入session
        List<MsendMail> list = msendMailServiceImplements.selectAll();  //获取邮箱服务器
        if(list.get(0)!=null){
            MsendMail mail = list.get(0);
            EmailUtiles.sendMailCode(mail,peopleCode,email);
            return ReturnResult.success("发送成功");
        }else{
            return ReturnResult.erro(CodeMsg.EMAIL_ERROR);
        }
    }

    /**
     * 验证邮箱验证码
     * @param session
     * @param code  邮箱验证码
     * @return
     */
    @PostMapping("/checkCode")
    public ReturnResult checkCode(HttpSession session, String code){
        Object obj = session.getAttribute("REGISTER_CODE_SESSION");
        User user = (User)obj;
        if(user!=null){
            String peopleCode = user.getUserName();
            Date sendDate = user.getLastdate();
            Date now = new Date();
            if((now.getTime()-sendDate.getTime())/(1000)>600){  //超时
                return ReturnResult.erro(CodeMsg.CODE_TIMEOUT_ERROR);
            }else if(!StringUtils.isNotBlank(peopleCode)){      //验证码为空
                return ReturnResult.erro(CodeMsg.CODE_NOTBLANK_ERROR);
            }
            if(code.equalsIgnoreCase(peopleCode)){
                return ReturnResult.success("验证成功");
            }
        }
        return ReturnResult.erro(CodeMsg.CODE_CHECK_ERROR);
    }

    /**
     * 注册用户
     *
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
     * 忘记密码
     * @param session
     * @param mmCode  邮箱验证吗
     * @param userName 用户名
     * @param newPassword 新密码
     * @return
     * @throws MessagingException
     * @throws MyException
     */
    @RequestMapping("/retrievePassword")
    public ReturnResult retrievePassword(HttpSession session,@NotBlank(message = "验证码不能为空")String mmCode, @NotBlank(message = "用户名不能为空")String userName, @NotBlank(message = "密码不能为空")String newPassword) throws MessagingException, MyException {

     User user =new User();
     user.setUserName(userName);
     user.setPassword(newPassword);
       if (ForgetPasswordImplement.upPassword(session,mmCode,user)){
           return ReturnResult.success();
       }
       return ReturnResult.erro(CodeMsg.SERVER_ERROR);

    }

    /**
     * 修改密码
     */
    @RequestMapping("/updataPassword")
    public ReturnResult updataPassword(@NotBlank(message = "用户名不能为空") String userName, @NotBlank(message = "原密码不能为空") String password, @NotBlank(message = "新密码不能为空") String newPassword) throws MyException {

        if (userServiceImplements.retSetPassword(userName, password, newPassword)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.UPDATE_PASSWORD_ERROR);
        }
    }


}
