package com.example.busniess.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class InformEntity implements  Serializable{
    private  String userName;//用户名
    private String count;//内容
    private String time;//时间


}
