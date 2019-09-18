package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

//用户表
@Data
public class MsendMail implements Serializable {
    @NotNull(message = "id参数不能为空", groups = UserValidator.UpDate.class)
    private Integer id;
    @NotNull(message = "服务器不能为空", groups = {UserValidator.InSet.class})
    private String server;//邮箱服务器
    @NotNull(message = "端口不能为空", groups = {UserValidator.InSet.class})
    private int port;//端口
    @NotNull(message = "邮箱不能为空", groups = {UserValidator.InSet.class,UserValidator.UpDate.class})
    @Email(message = "请填写正确的邮箱格式")
    private String mail;//邮箱
    private String name;//显示名称
    @NotNull(message = "密码不能为空", groups = {UserValidator.InSet.class,UserValidator.UpDate.class})
    private String password;//密码
}