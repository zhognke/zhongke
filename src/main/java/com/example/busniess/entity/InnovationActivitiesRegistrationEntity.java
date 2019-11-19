package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 创新活动报名表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-15 14:59:00
 */
@Data
public class InnovationActivitiesRegistrationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = {UserValidator.UpDate.class})
    private Integer id;
    /**
     * 活动id
     */
    @NotNull(message = "活动id不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private Integer innovationId;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String username;
    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String realname;
    /**
     * 电话号码
     */
    @NotNull(message = "电话号码不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String tel;
    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String email;
    /**
     * 省份
     */
    @NotNull(message = "省份不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String province;
    /**
     * 城市
     */
    @NotNull(message = "城市不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String city;
    /**
     * 区县
     */
    @NotNull(message = "区县不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String district;
    /**
     * 工作单位
     */
    @NotNull(message = "工作单位不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String companyName;
    /**
     * 职务
     */
    private String positions;
    /**
     * 活动参与目的
     */
    private String context;
    /**
     * 删除标记:0未删除,1已删除
     */
    private Integer delFlag;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8" )
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8" )
    private Date updateTime;

}
