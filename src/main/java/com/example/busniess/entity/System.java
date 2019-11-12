package com.example.busniess.entity;

import lombok.Data;

import java.util.Date;

@Data
public class System {
    private  int id;//id
    private String url;//系统名
    private  int statue;// 状态
    private Date upDateTime;//禁用时间
}
