package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

//企业中心
@Data
public class BusinessCenter implements Serializable {
    @NotBlank(message = "关联用户名不能为空", groups = {UserValidator.UpDate.class})
    private Integer id;//主键 1
    @NotBlank(message = "关联用户名不能为空", groups = {UserValidator.InSet.class})
    private String uName;//关联的用户名
    @NotBlank(message = "身份不能为空", groups = {UserValidator.InSet.class})
    private Integer identification;//身份识别  1.法人2.代理人 2
    @NotBlank(message = "统一社会信用不能为空", groups = {UserValidator.InSet.class})
    private String societyCode;//统一社会信用代码 3
    @NotBlank(message = "社会代码不能为空", groups = {UserValidator.InSet.class})
    private Integer codeStatue;//社会代码状态 4
    @NotBlank(message = "企业名称不能为空", groups = {UserValidator.InSet.class})
    private String firmName;//企业名字 5
    @NotBlank(message = "所属行业不能为空", groups = {UserValidator.InSet.class})
    private String industry;//所属行业 6
    @NotBlank(message = "规模不能为空", groups = {UserValidator.InSet.class})
    private Integer scale;//规模 7
    @NotBlank(message = "所属省不能为空", groups = {UserValidator.InSet.class})
    private String country;//省 8
    @NotBlank(message = "所属市不能为空", groups = {UserValidator.InSet.class})
    private String city;//市  9
    @NotBlank(message = "所属区不能为空", groups = {UserValidator.InSet.class})
    private String district;//区 ··10
    @NotBlank(message = "法人姓名不能为空", groups = {UserValidator.InSet.class})
    private String legalPerson;//法人 11
    @NotBlank(message = "法人身份证号不能为空", groups = {UserValidator.InSet.class})
    private String persionCode;//身份证号  12
    @NotBlank(message = "附件描述不能为空", groups = {UserValidator.InSet.class})
    private String accessoryDesc;//附件描述 13
    @NotBlank(message = "附件地址不能为空", groups = {UserValidator.InSet.class})
    private String address;//附件地址 14
    @NotBlank(message = "代理人姓名不能为空", groups = {UserValidator.InSet.class})
    private String agentPerson;//代理人姓名 15
    @NotBlank(message = "代理人身份证号不能为空", groups = {UserValidator.InSet.class})
    private String apPersionCode;//代理人身份证号  16

    private Integer statue;//审核状态 17 0审核中 1审核通过 2审核驳回
    private Date subTime;//提交时间
    private Date upTime;//更新时间
    private Reject reject;//驳回理由


}
