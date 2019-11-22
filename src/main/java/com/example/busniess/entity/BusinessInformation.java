package com.example.busniess.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//企业信息
@Data
public class BusinessInformation implements Serializable {
    private int id;//id
    private String uName;//关联用户名
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;//注册日期
    private BigDecimal registeredCapita;//注册资金
    private int scale;//规模
    private String enterprise;//企业简介
    private String logo;//log地址
    private Date insertTime;//插入时间
    private Date updateTime;//更新时间
    private  int statue;//状态


}
