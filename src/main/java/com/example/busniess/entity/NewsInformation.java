package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private  String resource;//来源
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private  Date publishDate;//发布时间
//  @NotBlank(message = "封面地址不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String cover;//封面地址
    @NotBlank(message = "新闻详情不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String detail;//新闻详情

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date submittime;//提交时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date upTime;// 更改时间


}
