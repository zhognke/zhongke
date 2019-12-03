package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Person {
    @NotNull(message = "id号不能为空", groups = {UserValidator.UpDate.class})
    private int id;//id1
    @NotBlank(message = "关联的账号不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String uName;//关联的账号2
    @NotBlank(message = "姓名不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String name;//姓名3
    @NotBlank(message = "身份证不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String personCode;//身份证号4
//    @NotBlank(message = "身份证图片地址不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
   private String imgAddress;//身份证图片
    @NotBlank(message = "所属企业不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String unit;//所属企业
    @NotBlank(message = "企业类型不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String territory;//企业类型
    @NotBlank(message = "所属省不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String province;//省
    @NotBlank(message = "市不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String city;//市
    @NotBlank(message = "区不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String district;//区
    @NotBlank(message = "职位不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String post;//职位9
    @NotBlank(message = "经验不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String experience;//经验7
    @NotBlank(message = "电话不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String telephone;//电话8


    @NotBlank(message = "附件地址不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String address;//附件地址
    @NotBlank(message = "个人介绍不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String selfIntroduction;//个人介绍

    private Reject reject;//驳回
    private int statue;//状态10
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inserttime;//11
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatetime;//12
}
