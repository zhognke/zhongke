package com.example.busniess.entity;

import lombok.Data;

import java.util.Date;

//企业中心
@Data
public class BusinessCenter {
    private Integer id;//主键 1
    private String uName;//关联的用户名
    private Integer identification;//身份识别  1.法人2.代理人 2
    private String societyCode;//统一社会信用代码 3
    private Integer codeStatue;//社会代码状态 4
    private String firmName;//企业名字 5
    private String industry;//所属行业 6
    private Integer scale;//规模 7
    private String country;//省 8
    private String city;//市  9
    private String district;//区 ··10
    private String legalPerson;//法人 11
    private String persionCode;//身份证号  12
    private String accessoryDesc;//附件描述 13
    private String address;//附件地址 14
    private String agentPerson;//代理人姓名 15
    private String apPersionCode;//代理人身份证号  16
    private String state;//审核状态 17
    private Date subTime;//提交时间
    private Date upTime;//审核时间


}
