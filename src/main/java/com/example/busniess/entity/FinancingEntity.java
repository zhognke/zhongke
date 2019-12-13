package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class FinancingEntity {
    @NotNull(message = "id号不能为空", groups = {UserValidator.UpDate.class})
    private int id;
    @NotBlank(message = "关联用户名不能为空", groups = {UserValidator.InSet.class})
    private String uName;//关联用户的名字24
    @NotBlank(message = "项目名不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String projectName;//项目名 1
    @NotBlank(message = "项目概述不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String projectOutline;//项目概述2
    @NotBlank(message = "项目优势不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String advantage;//项目优势3
    @NotBlank(message = "所属行业不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String industry;//所属行业4
    @NotBlank(message = "所属省份不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String province;//省5
    @NotBlank(message = "所属市不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String city;//市 6
    @NotBlank(message = "所属区不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String discribe;//区 7
    @NotBlank(message = "项目阶段不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String projecrPhase;//项目阶段8
    //
    @NotNull(message = "项目周期不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal period;//项目周期9
    @NotBlank(message = "联系人不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String linkMan;//联系人 10
    @NotBlank(message = "电话号码不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String phoneNumber;//电话号码11
    @NotBlank(message = "融资用途不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String extensive;//融资用途12
    @NotNull(message = "项目总投资不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal cost;//项目总投资13
    @NotNull(message = "以融资金额不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal hascost;//以融资金额14
    @NotBlank(message = "融资方式不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String financing;//融资方式15
    //    @NotBlank(message = "可承担利息不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String interest;//可承担利息16
    //    @NotNull(message = "所占时间不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal time;//所占时间17
    @NotBlank(message = "单位类型不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String unit;//单位18
    //    @NotBlank(message = "资金占用时长单位不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String lunit;//资金占用时长单位19
    @NotNull(message = "计划融资不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal projectFinancing;//计划融资20
    @NotNull(message = "预计收入所得不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal income;//计划融资所得21
    @NotNull(message = "利润不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal profit;//利润22
    //    @NotBlank(message = "所占比例不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String proportion;//所占比例 23///////


    private String projectType;//项目类型名

    private String projectStatic;//项目状态

    private String goal;//目的

    private String type;//类型


    private BigDecimal pgreat;//周期最大值
    private BigDecimal pless;//周期最小值
    private BigDecimal greater;//资金大于
    private BigDecimal less;//资金小于
    private int ageLimit;//年限
    private int statue;//发布状态
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;//插入时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTiem;//更新时间
    private String reject;//驳回原因
    private BusinessInformation businessInformation;//关联企业补充信息
    private BusinessCenter businessCenter;//关联企业
}
