package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-24 17:14:07
 */
@Data
public class DemandsCommentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 留言id
     */
    @NotNull(message = "留言id不能为空", groups = UserValidator.UpDate.class)
    private Integer id;
    /**
     * 需求id
     */
    @NotNull(message = "需求id不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private Integer demandsId;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String username;
    /**
     * 留言内容
     */
    @NotNull(message = "留言内容不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String commentContent;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
