package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class User {

    private Integer id;
    @NotNull(message = "账号不能为空")
    private String userName;//用户账号
    @NotNull(message = "密码不能为空")
    @DecimalMin(value = "6", message = "密码长度不能少于6个字符")
    @DecimalMax(value = "20", message = "密码长度不能超过20个字符")
    private String password;//密码
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号码格式错误")
    private String phoneNumber;//手机号
    private String statu;//用户状态备用
}
