package com.example.busniess.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Manager {
    private int id;//id号
    private String manage;//管理员账号
    private String password;//密码
    private int statue;//状态
    private String role;//角色
    private int identifier;//标识
    private Date creattime;//创建时间
    private Date uptime;//更新时间

}
