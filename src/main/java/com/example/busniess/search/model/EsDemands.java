package com.example.busniess.search.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Document(indexName = "casita", type = "demands")
@Data
public class EsDemands implements Serializable {

    @Id
    private Integer id;

    // 中文分词器 -> https://github.com/medcl/elasticsearch-analysis-ik
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String demandOutline;

//    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
//    private String content;

    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String demandContent;
    @Field(type = FieldType.Keyword)
    private String companyName;

    @Field(type = FieldType.Keyword)
    private String demandIndustry;

    private String demandIndustryDetail;

    private String demandType;

    private String cooperationType;

    private BigDecimal preInvestmentAmount;

    private Date endDate;

    @Field(type = FieldType.Date)
    private Date createTime;
    /**
     * 期望实现结果
     */
    private String expectedResult;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String district;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 电话
     */
    private String phoneNum;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 需求更新时间
     */
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 需求态状:0有效;1已到期;2用户关闭;3管理员关闭
     */
    private Integer status;
    /**
     * 审核态状:0待审核;1已审核;2驳回
     */
    private Integer approvalStatus;
    /**
     * 审批意见(原因)
     */
    private String approvalOpinion;

}
