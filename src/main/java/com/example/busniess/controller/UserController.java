package com.example.busniess.controller;

import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.MsendMail;
import com.example.busniess.entity.Person;
import com.example.busniess.entity.User;
import com.example.busniess.exception.MyException;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessCenterService;
import com.example.busniess.service.ForgetPassword;
import com.example.busniess.service.UserService;
import com.example.busniess.service.imp.MsendMailServiceImpl;
import com.example.busniess.utiles.EmailUtiles;
import com.example.busniess.utiles.Md5Utiles;
import com.example.busniess.validator.UserValidator;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Resource
    UserService UserServiceImpl;
    @Resource
    ForgetPassword ForgetPasswordImpl;
    @Resource
    BusinessCenterService businessCenterService;
    @Resource
    MsendMailServiceImpl msendMailServiceImpl;






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
                                  @NotNull(message = "身份识别号不能为空") Integer code,
                                  @RequestParam(value = "remb", defaultValue = "false",
                                          required = false) Boolean remb) throws ShiroException {

        User user = UserServiceImpl.findAllUserByName(userName);//use对象
        if(user==null){
            return ReturnResult.erro(CodeMsg.USER_NOT_EXISTS);
        }


        if(user.getStatue()==0){
            return ReturnResult.erro(CodeMsg.FREEZE_ERROR);
        }
        Integer isPerson = user.getPersion();//身份个人或者企业
        String email = user.getEmail();//邮箱

        if (code != isPerson) {
            return ReturnResult.erro(CodeMsg.IDENTITY_ERROR);
        }
        Subject subject = SecurityUtils.getSubject();//获取subject对象
        BusinessCenter businessCenter = businessCenterService.selectMyBusinessCenter(userName);
        Integer status = null;
        if (businessCenter != null) {
            status = businessCenter.getStatue();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("email", email);
        map.put("status", status);//null是没提交0待审核1通过2驳回
        map.put("isPerson", isPerson);
        if (subject.isAuthenticated()) {
            return ReturnResult.success(map);
        }

        UsernamePasswordToken up = new UsernamePasswordToken(userName, password);



        subject.login(up);
//        subject.getSession().setTimeout(-1000000L);
//        up.setRememberMe(remb);//记住我
        //登录成功
        return ReturnResult.success(map);
    }

    @RequestMapping("/logout")
    public ReturnResult logout() {
        Subject subject = SecurityUtils.getSubject();

        if (subject.getSession() != null) {
            subject.logout();
        }

        return ReturnResult.success("登出成功!");
    }

    /**
     * 发送邮箱验证码
     *
     * @param session
     * @param email   收件箱
     * @return
     */
    @PostMapping("/sendCode")
    public ReturnResult sendCode(HttpSession session, @NotBlank(message = "邮箱不能为空") @Email(message = "邮箱格式不能为空") String email) {
        String peopleCode = Md5Utiles.getNum(6);
        User user = new User();
        user.setEmail(email);
        user.setUserName(peopleCode);
        user.setLastdate(new Date());

        List<MsendMail> list = msendMailServiceImpl.selectAll();  //获取邮箱服务器
        if (list.get(0) != null) {
            MsendMail mail = list.get(0);
            EmailUtiles.sendMailCode(mail, peopleCode, email);
            session.setAttribute("REGISTER_CODE_SESSION", user);         //存入session
            return ReturnResult.success("发送成功");
        } else {
            return ReturnResult.erro(CodeMsg.EMAIL_ERROR);
        }
    }

    /**
     * 验证邮箱验证码
     *
     * @param session
     * @param code    邮箱验证码
     * @return
     */
    @PostMapping("/checkCode")
    public ReturnResult checkCode(HttpSession session, @NotBlank(message = "验证码不能为空") String code) {
        Object obj = session.getAttribute("REGISTER_CODE_SESSION");
        User user = (User) obj;
        if (user != null) {
            String peopleCode = user.getUserName();
            Date sendDate = user.getLastdate();
            Date now = new Date();
            if ((now.getTime() - sendDate.getTime()) / (1000) > 600) {  //超时
                return ReturnResult.erro(CodeMsg.CODE_TIMEOUT_ERROR);
            } else if (!StringUtils.isNotBlank(peopleCode)) {      //验证码为空
                return ReturnResult.erro(CodeMsg.CODE_NOT_BLANK_ERROR);
            }
            if (code.equalsIgnoreCase(peopleCode)) {
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
    @RequestMapping(value = "/registerUser", method = {RequestMethod.POST})
    public ReturnResult registerUser(@Validated({UserValidator.InSet.class}) User user) throws MyException {

        UserServiceImpl.addUser(user);
        //注册成功
        return ReturnResult.success(user.getUserName());
    }

    /**
     * 忘记密码
     *
     * @param session
     * @param mmCode      邮箱验证吗
     * @param userName    用户名
     * @param newPassword 新密码
     * @return
     * @throws MessagingException
     * @throws MyException
     */
    @RequestMapping(value = "/retrievePassword", method = {RequestMethod.POST})
    public ReturnResult retrievePassword(HttpSession session, @NotBlank(message = "验证码不能为空") String mmCode, @NotBlank(message = "用户名不能为空") String userName, @NotBlank(message = "密码不能为空") String newPassword) throws MessagingException, MyException {

        User user = new User();
        user.setUserName(userName);
        user.setPassword(newPassword);
        if (ForgetPasswordImpl.upPassword(session, mmCode, user)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SERVER_ERROR);

    }

    /**
     * 修改密码
     */
    @RequestMapping(value = "/updataPassword", method = {RequestMethod.POST})
    public ReturnResult updataPassword(@NotBlank(message = "用户名不能为空") String userName, @NotBlank(message = "原密码不能为空") String password, @NotBlank(message = "新密码不能为空") String newPassword) throws MyException {

        if (UserServiceImpl.retSetPassword(userName, password, newPassword)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.UPDATE_PASSWORD_ERROR);
        }
    }

    /**
     * 清除session中的数据
     *
     * @param session
     */
    @RequestMapping("/clearSession")
    public void clearSession(HttpSession session) {
        session.removeAttribute("REGISTER_CODE_SESSION");
    }

    /**
     * 根据用户名检查认证状态
     *
     * @param username
     * @param isPerson
     * @return
     */
    @RequestMapping("/checkStatus")
    public ReturnResult checkStatus(String username, Integer isPerson) {
        Integer status = UserServiceImpl.checkStatus(username, isPerson);
        return ReturnResult.success(status);
    }


    ////////////////////////////////////////////////////////////////

    /**
     * 后台检索企业用户
     * @param businessCenter
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectBusinessUser")
    public ReturnResult selectBusinessUser(BusinessCenter businessCenter,Integer pageNumber,Integer pageSize){
  return  ReturnResult.success( UserServiceImpl.searchBusinessUser(businessCenter,pageNumber,pageSize)) ;

    }

    /**
     * 检索个人用户
     * @param person
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectPersonUser")
    public ReturnResult selectPersonUser(Person person,Integer pageNumber,Integer pageSize){
        return ReturnResult.success(UserServiceImpl.searchPerson(person,pageNumber,pageSize));
    }





}
