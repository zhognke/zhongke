package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class Reject implements Serializable {
    private Integer id;//主id
    @NotBlank(message = "关联企业信息id不能为空", groups = {UserValidator.UpDate.class,UserValidator.InSet.class})
    private Integer bid;//关联的企业认证信息id
    @NotBlank(message = "驳回内容不能为空", groups = {UserValidator.UpDate.class,UserValidator.InSet.class})
    private String content;//驳回的内容
    private Date rejectTime;//驳回时间
}
