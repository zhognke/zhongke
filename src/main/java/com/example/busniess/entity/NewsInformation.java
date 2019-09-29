package com.example.busniess.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class NewsInformation implements Serializable {
    private int id; //新闻资讯id号
    private String uname;//用户名

    private String category;//类别

    private String title;//标题

    private String cover;//封面地址

    private String detail;//新闻详情

    private Date submittime;//提交时间
    private Date upTime;// 更改时间

}
