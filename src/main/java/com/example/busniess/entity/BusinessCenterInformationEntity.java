package com.example.busniess.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//企业认证&入驻信息视图
@Data
public class BusinessCenterInformationEntity implements Serializable {
    private int id;
    private String uname;   //关联用户名
    private String companyName;    //企业名称
    private String industry; //公司行业
    private String typeEnterprise;//企业类型
    private String province;//省份
    private String city;//城市
    private String district;//区县
    private String societyCode; //统一社会信用代码
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;  //注册时间
    private BigDecimal registeredCapita;   //注册资金
    private int scale;  //规模
    private String enterpriseProfile;//企业简介
    private String logo;//log地址
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;//插入时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;//更新时间
    private Integer statue; //认证状态0:待认证;1:通过;2:驳回

}
