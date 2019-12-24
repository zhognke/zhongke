package com.example.busniess.search.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * 人才需求表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-23 09:29:27
 */
@Data
@Document(indexName = "talent", type = "talent",shards = 3)
public class EsTalentDemandModel extends EsModel implements Serializable {

    private String indexType="人才需求"; //数据类型

    /**
     * 关联的用户名
     */
    private String userName;
    /**
     * 任职要求
     */
    private String requires;
    /**
     * 从事行业
     */
    private String engagedIndustry;
    /**
     * 从事行业二级目录
     */
    private String engagedIndustryDetail;
    /**
     * 行业经验
     */
    private String industryExperience;
    /**
     * 需求类型
     */
    private String demandsType;
    /**
     * 所需人数
     */
    private Integer peopleNum;
    /**
     * 学历
     */
    private String degree;
    /**
     * 薪资
     */
    private String salary;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系电话
     * 改成邮箱字段
     */
    private String phoneNum;
    /**
     * 状态: 0 正常;1用户关闭;2管理员关闭;
     */
    private Integer status;
    /**
     * 关闭原因
     */
    private String closeReason;
    /**
     * 审核状态:0待审核,1审核通过,2审核驳回
     */
    private Integer approvalStatus;
    /**
     * 审批意见
     */
    private String approvalOpinion;
    /**
     * 审批时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date approvalTime;
    /**
     * 是否删除;0未删除,1已删除
     */
    private Integer delFlag;

    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String companyName;//公司名称
    private String province;//省
    private String city;//城市
    private String district;//区县
    /**
     * 浏览量统计
     */
    private int viewCount;
    private String keyword;
}
