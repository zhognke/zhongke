package com.example.busniess.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 工业申报表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-28 10:31:37
 */
@Data
public class IndustrialDeclarationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 需求id
     */
    private Integer id;
    /**
     * 申报项目类别
     */
    private String declarationType;
    /**
     * 一级类目:'新开项目','续建项目'
     */
    private String isNew;
    /**
     * 二级类目
     */
    private String declarationIndustry;
    /**
     * 三级分类
     */
    private String industryTypeDetail;
    /**
     * 项目所属行业
     */
    private String projectType;
    /**
     * 项目所属行业细分类目
     */
    private String projectTypeDetail;
    /**
     * 企业名称
     */
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
    private Integer startDate;
    /**
     * 项目结束时间
     */
    private Integer endDate;
    /**
     * 总投资
     */
    private BigDecimal totalInvestment;
    /**
     * 删除标记
     */
    private Integer delFlag;
    /**
     * 明细信息
     */
    private IndustrialDeclarationDetailEntity detailEntity;
    /**
     * 根据申报年份筛选(非数据库储存字段)
     */
    private Integer declarationYearBegin;
    private Integer declarationYearEnd;
    /**
     * 统计(非数据库储存字段)
     */
    private Integer counts;
    /**
     * 搜索关键词(非数据库字段)
     */
    private String keyWord;

    private String userName;
}
