package com.example.busniess.search.model;

import lombok.Data;

@Data
public class EsSearchModel {
    private Integer id;
    private String title;
    private String outline;
    private String content;
    private String industry;    //项目所属行业
    private String industryDetail;  //项目所属行业细分类目

    private String indexType;   //索引类型
}
