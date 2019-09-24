package com.example.busniess.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 企业需求表
 * 
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-24 10:05:26
 */
@Data
public class DemandsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 需求id
	 */
	private Integer id;
	/**
	 * 企业名称
	 */
	private String companyName;
	/**
	 * 需求类别:1.技术需求;2.人才需求;3.生产需求;4.其他
	 */
	private String demandType;
	/**
	 * 合作方式:1.技术转让;2.专利许可;3.委托开发;4.合作开发;5.技术服务;6.技术入股;7.其他
	 */
	private String cooperationType;
	/**
	 * 需求行业
	 */
	private String demandIndustry;
	/**
	 * 需求行业二级分类
	 */
	private String demandIndustryDetail;
	/**
	 * 需求概述
	 */
	private String demandOutline;
	/**
	 * 需求详情
	 */
	private String demandContent;
	/**
	 * 望实现结果期
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
	 * 预投金额
	 */
	private BigDecimal preInvestmentAmount;
	/**
	 * 需求截止日期
	 */
	private Date endDate;
	/**
	 * 需求创建时间
	 */
	private Date createTime;
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
}
