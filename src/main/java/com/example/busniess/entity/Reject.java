package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class Reject implements Serializable {
    private Integer id;//主id
    @NotNull(message = "关联企业信息id不能为空", groups = {UserValidator.UpDate.class})
    private Integer bId;//关联的企业认证信息id
    @NotNull(message = "关私人信息id不能为空", groups = {UserValidator.InSet.class})
    private Integer PId;//关联的私人认证
    @NotBlank(message = "驳回内容不能为空", groups = {UserValidator.UpDate.class,UserValidator.InSet.class})
    private String content;//驳回的内容
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date rejectTime;//驳回时间
}
