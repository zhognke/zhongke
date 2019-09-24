package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

//科技成果入住信息
@Data
public class Occupancy implements Serializable {
    @NotNull(message = "id参数不能为空", groups = UserValidator.UpDate.class)
    private Integer id;//id
    @NotNull(message = "用户不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String userName;//关联的用户
    @NotNull(message = "企业名称不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String nameFirm;//企业名称
    @NotNull(message = "技成术果不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String resultTechnolo;//科技成果
    @NotNull(message = "详情描述不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String describe;//详情描述
    @NotNull(message = "专利号不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String patentNumber;//所获专利号和名称
    @NotNull(message = "知识产权编号不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String serialNumber;//知识产权编号
    @NotNull(message = "所属行业不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String industry;//所属行业
    @NotNull(message = "具体行业不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String spIndustries;//具体行业
    @NotNull(message = "所属省不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String country;//省
    @NotNull(message = "所属市不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String city;//市
    @NotNull(message = "所属区不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String district;//区
    private Data creatTime;//创建时间
    private Data stopTime;//停止发布时间（过期）
    private Data upTiem;//修改时间
    private Data auditTime;//审核时间
    private Integer kstatue;//用户的修改状态
    private Integer statue;//入住状态

    @Valid
    private List<String> imgAddress;//图片地址


}
