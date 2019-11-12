package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data

public class NewsInformation implements Serializable {
    @NotNull(message = "id参数不能为空", groups = UserValidator.UpDate.class)
    private int id; //新闻资讯id号
    @NotBlank(message = "用户名不能为空", groups = UserValidator.UpDate.class)
    private String uName;//用户名
    @NotBlank(message = "类别不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String category;//类别
    @NotBlank(message = "标题不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String title;//标题
    @NotBlank(message = "封面地址不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String cover;//封面地址
    @NotBlank(message = "新闻详情不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String detail;//新闻详情

    private Date submittime;//提交时间
    private Date upTime;// 更改时间


}
