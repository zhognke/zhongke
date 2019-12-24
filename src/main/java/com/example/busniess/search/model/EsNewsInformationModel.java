package com.example.busniess.search.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(indexName = "news", type = "news",shards = 3)
public class EsNewsInformationModel extends EsModel implements Serializable {

    private String indexType="新闻政策"; //数据类型
    private String uName;//用户名
    private String category;//类别
    private String resource;//来源
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date publishDate;//发布时间
    private String cover;//封面地址//文件地址
    private String detail;//新闻详情
    private String summary;//摘要
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date submittime;//提交时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date upTime;// 更改时间
    private Integer status = 0;
    private Integer approvalStatus = 1;
    private String keyword;


}
