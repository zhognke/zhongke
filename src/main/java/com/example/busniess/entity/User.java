package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

//用户表
@Data
public class User implements Serializable {
    @NotNull(message = "id参数不能为空", groups = UserValidator.UpDate.class)
    private Integer id;
    //@NotNull(message = "用户角色不能为空",groups =UserValidator.InSet.class)
    private  String userRole;//用户角色
    @NotNull(message = "账号不能为空", groups = {UserValidator.UpDate.class,UserValidator.InSet.class})
    private String userName;//用户账号
    @NotNull(message = "密码不能为空", groups = {UserValidator.UpDate.class,UserValidator.InSet.class})
    @Length(min = 6, max = 20, message = "密码长度大于6小于20", groups = {UserValidator.UpDate.class,UserValidator.InSet.class})
    private String password;//密码
    @Pattern(regexp = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$", message = "手机号码格式错误", groups = {UserValidator.UpDate.class,UserValidator.InSet.class})
    private String phoneNumber;//手机号
    @NotNull(message = "邮箱不能为空")
    @Email(message = "请填写正确的邮箱格式",groups = {UserValidator.UpDate.class,UserValidator.InSet.class})
    private String email;//邮箱
    private String statu;//用户状态备用
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastdate;
    private String ip;
    private Integer persion;//区分私人和企业

}
