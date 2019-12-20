package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 意向表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-14 16:19:00
 */
@Data
public class IntentionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = {UserValidator.UpDate.class})
    private Integer id;
    /**
     * 关联用户名
     */
//    @NotNull(message = "关联用户名不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String userName;
    /**
     * 项目id
     */
    @NotNull(message = "项目id不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private Integer projectId;
    /**
     * 项目名称
     */
    @NotNull(message = "项目名称不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String projectName;
    /**
     * 意向类型
     */
    @NotNull(message = "意向类型不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String projectType;
    /**
     * 联系人
     */
    @NotNull(message = "联系人不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String contact;
    /**
     * 电话
     */
    @NotNull(message = "电话不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String phoneNum;
    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String email;
    /**
     * 工作单位
     */
    @NotNull(message = "工作单位不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String companyName;
    /**
     * 地址
     */
    private String address;
    /**
     * 意向说明
     */
    private String context;
    /**
     * 处理状态:0待处理;1跟进中;2已结束
     */
    private Integer statue;
    /**
     * 意向结果:0待确认;1有效;2;无效
     */
    private Integer isValid;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 是否删除;0正常,1删除
     */
    private Integer delFlag;

    /**
     * 提交时间-开始-搜索使用字段
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date startDate;
    /**
     * 提交时间-截止-搜索使用字段
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date endDate;

}
