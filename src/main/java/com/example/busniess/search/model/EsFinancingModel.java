package com.example.busniess.search.model;

import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.BusinessInformation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Document(indexName = "financial", type = "financial",shards = 3)
public class EsFinancingModel  extends EsModel implements Serializable {

    private String indexType="项目融资"; //数据类型

    @Field(type = FieldType.Keyword)
    private String uName;//关联用户的名字24

    private String projectName;//项目名 1

    private String projectOutline;//项目概述2

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String advantage;//项目优势3

    @Field(type = FieldType.Keyword)
    private String province;//省5

    @Field(type = FieldType.Keyword)
    private String city;//市 6

    @Field(type = FieldType.Keyword)
    private String discribe;//区 7

    @Field(type = FieldType.Keyword)
    private String projecrPhase;//项目阶段8

    private BigDecimal period;//项目周期9

    private String linkMan;//联系人 10

    private String phoneNumber;//电话号码11

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String extensive;//融资用途12

    private BigDecimal cost;//项目总投资13

    private BigDecimal hascost;//以融资金额14

    private String financing;//融资方式15

    private String interest;//可承担利息16

    private BigDecimal time;//所占时间17

    private String unit;//单位18

    private String lunit;//资金占用时长单位19

    private BigDecimal projectFinancing;//计划融资20

    private BigDecimal income;//计划融资所得21

    private BigDecimal profit;//利润22

    private String proportion;//所占比例 23///////

    private String projectType;//项目类型名

    private String projectStatic;//项目状态

    private String goal;//目的

    private String type;//类型


    private BigDecimal pgreat;//周期最大值
    private BigDecimal pless;//周期最小值
    private BigDecimal greater;//资金大于
    private BigDecimal less;//资金小于
    @Field(type = FieldType.Short)
    private int ageLimit;//年限
    @Field(type = FieldType.Short)
    private Integer approvalStatus;//发布状态
    @Field(type = FieldType.Short)
    private Short status=0;//发布状态
    private String reject;//驳回原因
    private BusinessInformation businessInformation;//关联企业补充信息
    private BusinessCenter businessCenter;//关联企业
    private String keyword; //搜索关键字(非数据库存储字段)
}
