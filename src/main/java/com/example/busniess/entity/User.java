package com.example.busniess.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String userName;//用户账号
    private String password;//密码
    private String phoneNumber;//手机号

}
