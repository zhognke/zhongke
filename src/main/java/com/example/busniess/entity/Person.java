package com.example.busniess.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Person {

    private int id;//id1
    private String uName;//关联的账号2
    private String name;//姓名3
    private String personCode;//身份证号4
    private String imgAddress;//身份证图片
    private String unit;//所属企业
    private String territory;//企业类型
    private String province;//省
    private String city;//市
    private String district;//区
    private String post;//职位9
    private String experience;//经验7
    private String telephone;//电话8
    private int statue;//状态10
    private Date inserttime;//11
    private Date updatetime;//12

    private String address;//附件地址
    private  String selfIntroduction;//个人介绍

    private  Reject reject;//驳回
}
