package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//企业信息
@Data
public class BusinessInformation implements Serializable {

    @NotNull(message = "id不能为空", groups = {UserValidator.UpDate.class})
    private int id;//id
    @NotNull(message = "关联用户名不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String uName;//关联用户名
    @NotNull(message = "注册日期不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date registrationDate;//注册日期
    @NotNull(message = "注册资金不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal registeredCapital;//注册资金
    @NotNull(message = "规模不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private int scale;//规模
    @NotBlank(message = "企业简介不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String enterprise;//企业简介
    @NotBlank(message = "log地址不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String logo;//log地址
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;//插入时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;//更新时间
    private int statue;//状态


    private BusinessCenter businessCenter;//关联的企业认证中心

}
