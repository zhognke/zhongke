package com.example.busniess.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 专家信息表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-24 11:42:14
 */
@Data
public class ProfessionalsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 关联用户名
     */
    private String userName;
    /**
     * 专家姓名
     */
    private String realName;
    /**
     * 所在单位
     */
    private String institutions;
	/**
	 * 专家职务
	 */
	private String positions;
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
     * 技术领域
     */
    private String technologyScope;
    /**
     * 突出贡献
     */
    private String outstandingContribution;
    /**
     * 研究方向
     */
    private String researchDirection;
    /**
     * 专家介绍
     */
    private String introduced;
    /**
     * 联系电话
     */
    private String phoneNum;
    /**
     * 头像地址
     */
    private String iconAddress;
    /**
     * 附件地址
     */
    private String certificateAddress;
	/**
	 * 附件地址数组(非数据库字段)
	 */
	private String[] certificateAddresss;
    /**
     * 状态: 0 正常;1用户关闭;2管理员关闭;4逻辑删除
     */
    private Integer status;
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
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 删除标记
     */
    private Integer delFlag;
}
