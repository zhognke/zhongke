package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    @NotNull(message = "主键id不能为空", groups = { UserValidator.UpDate.class})
    private Integer id;
    /**
     * 关联用户名
     */
    @NotNull(message = "用户名不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String userName;
    /**
     * 专家姓名
     */
    @NotNull(message = "专家姓名不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String realName;
    /**
     * 所在单位
     */
    @NotNull(message = "所在单位不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String institutions;
	/**
	 * 专家职务
	 */
    @NotNull(message = "专家职务不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
	private String positions;
    /**
     * 从事行业
     */
//    @NotNull(message = "从事行业不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String engagedIndustry;
    /**
     * 从事行业二级目录
     */
//    @NotNull(message = "从事行业明细不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String engagedIndustryDetail;
    /**
     * 行业经验
     */
    @NotNull(message = "行业经验不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String industryExperience;
    /**
     * 技术领域
     */
    @NotNull(message = "技术领域不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String technologyScope;
    /**
     * 技术领域二级目录
     */
    @NotNull(message = "技术领域二级目录不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String technologyScopeDetail;
    /**
     * 突出贡献
     */
//    @NotNull(message = "突出贡献不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String outstandingContribution;
    /**
     * 研究方向
     */
    @NotNull(message = "研究方向不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String researchDirection;
    /**
     * 专家介绍
     */
    @NotNull(message = "专家介绍不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String introduced;
    /**
     * 研究成果
     */
    @NotNull(message = "研究成果不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String researchResults;
    /**
     * 获奖荣誉
     */
    @NotNull(message = "获奖荣誉不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String awards;
    /**
     * 联系电话
     */
    @NotNull(message = "联系电话不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String phoneNum;
    /**
     * 省份
     */
    @NotNull(message = "所在省份不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String province;//省
    /**
     * 城市
     */
    @NotNull(message = "所在城市不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String city;//城市
    /**
     * 区县
     */
//    @NotNull(message = "所在区县不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String district;//区县
    /**
     * 头像地址
     */
    @NotNull(message = "头像不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
    private String iconAddress;
    /**
     * 附件地址
     */
    @NotNull(message = "附件地址不能为空", groups = { UserValidator.InSet.class,UserValidator.UpDate.class})
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approvalTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 删除标记
     */
    private Integer delFlag;
    /**
     * 搜索关键字,非数据库字段
     */
    private String keyWord;
    /**
     * 是否是热门,0否,1是
     */
    private Integer isHot;
    /**
     * 是否置顶,0否,1是
     */
    private Integer isTop;
    /**
     * 排序(热门/置顶使用)
     */
    private Integer sort;
    /**
     * 专家科技成果,非数据库字段
     */
    List<Occupancy> occupancyList;
    /**
     * 拼音
     */
    private String pinYin;

    private String orderField;  //排序字段

    private String orderType;   //排序方式
}
