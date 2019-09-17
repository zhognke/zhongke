package com.example.busniess.entity;

import lombok.Data;

import java.io.Serializable;

//科技成果入住信息
@Data
public class Occupancy implements Serializable{
    private  Integer id;//id
    private  String userName;//关联的用户
    private  String  nameFirm;//企业名称
    private String resultTechnolo;//科技成果
    private String describe;//详情描述
    private  String patentNumber;//所获专利号和名称
    private String serialNumber;//知识产权编号
    private  String imgAddress;//图片地址
    private String industry;//所属行业
    private  String spIndustries;//具体行业
    private  String country;//省
    private  String city;//市
    private  String district;//区

}
