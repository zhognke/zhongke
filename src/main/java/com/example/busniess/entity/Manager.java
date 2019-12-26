package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Manager {

    @NotNull(message = "id号不能为空",groups = UserValidator.UpDate.class)
    private int id;//id号
    @NotNull(message = "管理员帐号不能为空",groups = {UserValidator.InSet.class,UserValidator.UpDate.class})
    private String manage;//管理员账号
    @NotNull(message = "管理员帐号不能为空",groups = {UserValidator.InSet.class,UserValidator.UpDate.class})
    private String password;//密码
    private int statue;//状态
    private String role;//角色
    private int identifier;//标识
    private Date creattime;//创建时间
    private Date uptime;//更新时间

}
