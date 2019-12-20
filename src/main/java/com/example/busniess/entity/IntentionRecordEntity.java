package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 意向跟进表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-12-18 19:00:04
 */
@Data
public class IntentionRecordEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = {UserValidator.UpDate.class})
    private Integer id;
    /**
     * 意向id
     */
    @NotNull(message = "意向id不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private Integer intentionId;
    /**
     * 关联用户名
     */
    @NotNull(message = "关联用户名不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String username;
    /**
     * 跟进记录
     */
    @NotNull(message = "跟进记录不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String content;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 是否删除;0正常,1删除
     */
    private Integer delFlag;

}
