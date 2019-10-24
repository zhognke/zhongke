package com.example.busniess.entity;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.example.busniess.validator.UserValidator;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

//科技成果入住信息
@Data
public class Occupancy implements Serializable {
    @NotNull(message = "id参数不能为空", groups = UserValidator.UpDate.class)
    private Integer id;//id
    @NotNull(message = "用户不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String userName;//关联的用户
    @NotNull(message = "技成术果不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String resultTechnolo;//科技成果名
    @NotNull(message = "科技成果阶段不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String stage;//科技成果阶段
    @NotNull(message = "科技成果优势不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String advantages;//成果优势
    @NotNull(message = "所属行业不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String industry;//所属行业
    @NotBlank(message = "成果属性不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String attribute;//成果属性
    @NotBlank(message = "专利名称不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String patenNname;//专利名称
    @NotBlank(message = "专利号不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String patenNumber;//专利号
    @NotNull(message = "专利价格不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal price;//专利价格

    private String registerNumber;//软件登记号
    @NotNull(message = "详情描述不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String describe;//详情描述
    @NotNull(message = "应用范围不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String appliedRange;//应用范围

    @NotNull(message = "联系人姓名不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String linkMan;//联系人
    @NotNull(message = "联系电话不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private Integer phoneNumber;//联系人电话

    private Data stopTime;//停止发布时间（过期）
    private Data creatTime;//创建时间

    private Data upTiem;//修改时间
    private Data auditTime;//审核时间
    private Integer kstatue;//用户的修改状态
    private Integer statue;//入住状态
    @Valid
    private List<ImageAddress> imgAddress;//图片地址


}
