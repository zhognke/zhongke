package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;

//用户表
@Data
public class User implements Serializable {
    @NotNull(message = "id参数不能为空", groups = UserValidator.UpDate.class)
    private Integer id;
    @NotNull(message = "账号不能为空", groups = {UserValidator.InSet.class})
    private String userName;//用户账号
    @NotNull(message = "密码不能为空", groups = {UserValidator.InSet.class})
    @Length(min = 6, max = 20, message = "密码长度大于6小于20",groups = {UserValidator.InSet.class})
    private String password;//密码
    @Pattern(regexp = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$", message = "手机号码格式错误", groups = {UserValidator.InSet.class})
    private String phoneNumber;//手机号
    private String statu;//用户状态备用
}
