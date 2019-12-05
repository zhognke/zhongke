package com.example.busniess.search.model;

import com.example.busniess.entity.IndustrialDeclarationDetailEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Document(indexName = "industrydeclare", type = "industrydeclare",shards = 3)
@Data
public class EsIndustryDeclareModel implements Serializable {

    /**
     * 需求id
     */
    @Id
    private Integer id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String title;   //标题
    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String content; //内容
    private String industry;    //项目所属行业
    private String industryDetail;  //项目所属行业细分类目
    /**
     * 申报项目类别
     */
    @Field(type = FieldType.Keyword)
    private String declarationType;
    /**
     * 一级类目:'新开项目','续建项目'
     */
    private String isNew;
    /**
     * 二级类目
     */
    @Field(type = FieldType.Keyword)
    private String declarationIndustry;
    /**
     * 三级分类
     */
    @Field(type = FieldType.Keyword)
    private String industryTypeDetail;
    /**
     * 项目所属行业
     */
    @Field(type = FieldType.Keyword)
    private String projectType;
    /**
     * 项目所属行业细分类目
     */
    @Field(type = FieldType.Keyword)
    private String projectTypeDetail;
    /**
     * 企业名称
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String companyName;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目内容
     */
    private String projectContent;
    /**
     * 省
     */
    @Field(type = FieldType.Keyword)
    private String province;
    /**
     * 市
     */
    @Field(type = FieldType.Keyword)
    private String city;
    /**
     * 区
     */
    private String district;
    /**
     * 需求态状:0有效;1已到期;2管理员关闭;3用户关闭;
     */
    private Integer status;
    /**
     * 关闭原因
     */
    private String closeReason;
    /**
     * 审核态状:0待审核;1已审核;2驳回
     */
    private Integer approvalStatus;
    /**
     * 审批意见(原因)
     */
    private String approvalOpinion;
    /**
     * 审批时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date approvalTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 项目开始时间
     */
    @Field(type = FieldType.Date)
    private Integer startDate;
    /**
     * 项目结束时间
     */
    @Field(type = FieldType.Date)
    private Integer endDate;
    /**
     * 总投资
     */
    @Field(type = FieldType.Double)
    private BigDecimal totalInvestment;
    /**
     * 删除标记
     */
    private Integer delFlag;
    /**
     * 明细信息
     */
    @Field(type = FieldType.Object)
    private IndustrialDeclarationDetailEntity detailEntity;

    private String keyword;
    private String indexType="industrydeclare";
}
