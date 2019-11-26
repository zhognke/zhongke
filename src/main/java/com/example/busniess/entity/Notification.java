package com.example.busniess.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Notification {
    private int id;//id
    private String title;//标题
    private String content;//内容
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addTime;//添加时间
    private String uName;//关联的用户名
    private Date insertTime;//插入的时间
    private Date upTime;//更新的时间
    private int sort;//排序

}
