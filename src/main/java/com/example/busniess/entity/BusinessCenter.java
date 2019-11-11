package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

//企业中心
@Data
public class BusinessCenter implements Serializable {

    @NotBlank(message = "主键不能为空", groups = {UserValidator.UpDate.class})
    private Integer id;//主键 1
    @NotBlank(message = "关联用户名不能为空", groups = {UserValidator.InSet.class, UserValidator.UpDate.class})
    private String uName;//关联的用户名2
    @NotBlank(message = "企业名称不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String firmName;//企业名字 3firmName
    @NotBlank(message = "所属行业不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String industry;//所属行业 4(增加)
    @NotBlank(message = "企业类型不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String typeEnterprise;

    @NotNull(message = "规模不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private Integer scale;//规模 5
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "注册日期必须必须小于当前日期", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private Date recordDate;//注册日期6（增加）
    //    @NotBlank(message = "注册资金不能为空",groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    @DecimalMin(value = "1", message = "注册资金不能小于1", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal registeredCapital;//注册资金7（增加）

    @NotBlank(message = "所属省不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String country;//省 8
    @NotBlank(message = "所属市不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String city;//市  9
    @NotBlank(message = "所属区不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String district;//区 ··10
    @NotBlank(message = "企业简介不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String enterpriseProfile;//企业简介//11（增加
    @NotBlank(message = "logo地址不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String logo;//企业logo 12

    @NotBlank(message = "身份不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String identification;//身份识别  1.法人2.代理人 2 //13
    @NotBlank(message = "统一社会信用不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String societyCode;//统一社会信用代码 14
//    @NotBlank(message = "社会代码不能为空", groups = {UserValidator.InSet.class})
//    private Integer codeStatue;//社会代码状态 14


    @NotBlank(message = "法人姓名不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String legalPerson;//法人 15
    @NotBlank(message = "法人身份证号不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String persionCode;//身份证号  16
    //    @NotBlank(message = "附件描述不能为空", groups = {UserValidator.InSet.class})
//    private String accessoryDesc;//附件描述 17
    @NotBlank(message = "附件地址不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String address;//附件地址 17
    @NotNull(message = "联系电话不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
//    @Pattern(regexp = "((13[0-9])|(15[0-9])|(18[0,5-9]))\\d{8}",message = "输入手机号码有误")
    private BigInteger phoneNumber;//手机号码（增加）18
    //    @NotBlank(message = "代理人姓名不能为空",groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String agentPerson;//代理人姓名 19
    //    @NotBlank(message = "代理人身份证号不能为空",groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String apPersionCode;//代理人身份证号  20

    private Integer statue;//审核状态 17 0审核中 1审核通过 2审核驳回21
    private Date subTime;//提交时间22
    private Date upTime;//更新时间23
    private Date auditTime;//审核时间//24
    private Reject reject;//关联驳回

    private Integer scaleBegin;
    private Integer scaleEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recordDateBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recordDateEnd;
    private BigDecimal registeredCapitalBegin;
    private BigDecimal registeredCapitalEnd;
    private String keyWord;
    private List<Occupancy> occupancyList;
}
