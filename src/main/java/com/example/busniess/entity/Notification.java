package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Notification {
    @NotNull(message = "id号不能为空",groups ={UserValidator.UpDate.class} )
    private int id;//id
    @NotBlank(message = "标题不能为空",groups ={UserValidator.UpDate.class,UserValidator.InSet.class} )
    private String title;//标题
    @NotBlank(message = "内容不能为空",groups ={UserValidator.UpDate.class,UserValidator.InSet.class} )
    private String content;//内容
    @NotBlank(message = "添加时间不能为空",groups ={UserValidator.UpDate.class,UserValidator.InSet.class} )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addTime;//添加时间
    @NotBlank(message = "关联的用户名",groups ={UserValidator.UpDate.class,UserValidator.InSet.class} )
    private String uName;//关联的用户名
    private Date insertTime;//插入的时间
    private Date upTime;//更新的时间
    private int sort;//排序

}
