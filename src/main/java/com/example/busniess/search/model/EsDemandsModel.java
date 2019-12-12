package com.example.busniess.search.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = "demands", type = "demands",shards = 3)
@Data
public class EsDemandsModel extends EsModel implements Serializable {

    private String indexType="demands"; //数据类型

    // 中文分词器 -> https://github.com/medcl/elasticsearch-analysis-ik
    private String demandOutline;

    private String demandContent;

    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String companyName;

    private String demandIndustry;

    private String demandIndustryDetail;

    @Field(type = FieldType.Keyword)
    private String demandType;

    @Field(type = FieldType.Keyword)
    private String cooperationType;

    @Field(type = FieldType.Keyword)
    private String cooperationIntention;

    private String preInvestmentAmount;

    @Field(type = FieldType.Date)
    private Date endDate;

    @Field(type = FieldType.Date)
    private Date createTime;
    /**
     * 期望实现结果
     */
    private String expectedResult;
    /**
     * 省
     */
    private String province;
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
    @Field(type=FieldType.Date)
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 需求态状:0有效;1已到期;2用户关闭;3管理员关闭
     */
    @Field(type=FieldType.Integer)
    private Integer status;
    /**
     * 关闭原因
     */
    private String closeReason;
    /**
     * 审核态状:0待审核;1已审核;2驳回
     */
    @Field(type=FieldType.Integer)
    private Integer approvalStatus;
    /**
     * 审批时间
     */
    @Field(type=FieldType.Date)
    private Date approvalTime;
    /**
     * 审批意见(原因)
     */
    private String approvalOpinion;
    /**
     * 删除标记
     */
    @Field(type=FieldType.Integer)
    private Integer delFlag;
    private String keyword; //搜索关键字
    private String preInvestmentAmountBegin;    //预投金额范围查询
    private String preInvestmentAmountEnd;  //预投金额范围查询
}
