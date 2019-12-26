package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 创新活动表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-15 14:59:00
 */
@Data
public class InnovationActivitiesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = {UserValidator.UpDate.class})
    private Integer id;
    /**
     * 活动主题
     */
    @NotNull(message = "活动主题不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String activitiesTopic;
    /**
     * 活动内容500字以内
     */
    @NotNull(message = "活动内容500字以内不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String activitiesContent;
    /**
     * 活动类型
     */
    //@NotNull(message = "活动类型不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String activitiesType;
    /**
     * 主办单位
     */
    @NotNull(message = "主办单位不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String organizers;
    /**
     * 承办单位
     */
    @NotNull(message = "承办单位不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String contractors;
    /**
     * 协办单位
     */
    private String coOrganizers;
    /**
     * 活动开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8" )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "活动开始时间不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private Date startTime;
    /**
     * 活动结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8" )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "活动结束时间不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private Date endTime;
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
     * 详细地址
     */
    private String address;
    /**
     * 活动图片地址(多个图片用","隔开)
     */
    private String imgAddrs;
    /**
     * 状态:0报名中;1进行中;2已结束;3活动取消
     */
    @NotNull(message = "活动状态不能为空", groups = {UserValidator.UpDate.class})
    private String status;
    private String[] statusArr;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8" )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8" )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 0未删除;1删除
     */
    private Integer delFlag;

    private String keyword;

    private Integer counts; //统计-非数据库字段
}
