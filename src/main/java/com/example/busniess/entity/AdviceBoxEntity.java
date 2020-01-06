package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 意见信箱表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2020-01-06 09:01:24
 */
@Data
public class AdviceBoxEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = {UserValidator.UpDate.class})
    private Integer id;
    /**
     * 关联用户名
     */
    @NotNull(message = "关联用户名不能为空", groups = {UserValidator.InSet.class})
    private String username;
    /**
     * 信件类别
     */
    @NotNull(message = "信件类别不能为空", groups = {UserValidator.InSet.class})
    private String type;
    /**
     * 真实姓名
     */
    @NotNull(message = "真实姓名不能为空", groups = {UserValidator.InSet.class})
    private String realName;
    /**
     * 联系电话
     */
    @NotNull(message = "联系电话不能为空", groups = {UserValidator.InSet.class})
    private String phone;
    /**
     * 信件标题
     */
    @NotNull(message = "信件标题不能为空", groups = {UserValidator.InSet.class})
    private String title;
    /**
     * 信件内容
     */
    @NotNull(message = "信件内容不能为空", groups = {UserValidator.InSet.class})
    private String content;
    /**
     * 指派人员用户名
     */
    private String assignmentUsername;
    /**
     * 回信
     */
    private String reply;
    /**
     * 省份
     */
    @NotNull(message = "省份不能为空", groups = {UserValidator.InSet.class})
    private String province;
    /**
     * 城市
     */
    @NotNull(message = "城市不能为空", groups = {UserValidator.InSet.class})
    private String city;
    /**
     * 区县
     */
    @NotNull(message = "区县不能为空", groups = {UserValidator.InSet.class})
    private String district;
    /**
     * 详细地址
     */
    @NotNull(message = "详细地址不能为空", groups = {UserValidator.InSet.class})
    private String address;
    /**
     * 是否显示,0不显示,1显示
     */
    private Integer isShow;
    /**
     * 处理状态:0未处理;1已回复
     */
    private Integer statue;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 回信时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date replyTime;
    /**
     * 是否删除:0正常,1删除
     */
    private int delFlag;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate; //搜索条件-写信开始时间,非数据库字段

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;   //搜索条件-写信结束时间,非数据库字段
}
