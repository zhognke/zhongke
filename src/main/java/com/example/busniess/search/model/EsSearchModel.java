package com.example.busniess.search.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class EsSearchModel {
    private Integer id;
    private String title;
    private String outline;
    private String content;
    private String industry;    //项目所属行业
    private String industryDetail;  //项目所属行业细分类目
    private String companyName; //公司名称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;    //修改时间
    private String indexType;   //索引类型

    private String resource;
    private String publishDate;

}
