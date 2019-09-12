package com.example.busniess.entity;

import lombok.Data;

//科技成果表
@Data
public class Scientific {
    private String companyName;//企业名字
    private String technology;//技术成果
    private String descript;//详情描述
    private String proof;//资质证地址
    private String industry;//所属行业
    private  String spIndustries;//具体行业
    private  String country;//省
    private  String city;//市
    private  String district;//区
    private  String patent;//专利号

}
