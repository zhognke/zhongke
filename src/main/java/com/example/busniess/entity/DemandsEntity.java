package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
@ApiModel(value = "需求实体类",description="传递参数类")
public class DemandsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 需求id
     */
    @NotNull(message = "id参数不能为空", groups = UserValidator.UpDate.class)
    private Integer id;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String userName;
    /**
     * 企业名称
     */
    private String companyName;
    /**
     * 需求类别:1.技术需求;2.人才需求;3.生产需求;4.其他
     */
    @NotNull(message = "需求类别不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String demandType;
    private String[] demandTypes;
    /**
     * 合作方式:1.技术转让;2.专利许可;3.委托开发;4.合作开发;5.技术服务;6.技术入股;7.其他
     */
    @NotNull(message = "合作方式不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String cooperationType;

    private String[] cooperationTypes;
    /**
     * 合作倾向:不限;高新技术企业;技术先进型服务企业;新产品备案企业;其他
     */
    private String cooperationIntention;

    private String[] cooperationIntentions;
    /**
     * 需求行业
     */
    @NotNull(message = "需求行业不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String demandIndustry;
    /**
     * 需求行业二级分类
     */
    @NotNull(message = "需求行业二级分类不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String demandIndustryDetail;
    /**
     * 需求概述
     */
    @NotNull(message = "需求概述不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String demandOutline;
    /**
     * 需求详情
     */
    @NotNull(message = "需求详情不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String demandContent;
    /**
     * 期望实现结果
     */
    @NotNull(message = "期望实现结果不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
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
     * 邮箱
     */
    //private String email;
    /**
     * 联系人
     */
    @NotNull(message = "联系人不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String contact;
    /**
     * 电话
     */
    @Pattern(regexp = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$", message = "手机号码格式错误", groups = {UserValidator.InSet.class})
    private String phoneNum;
    /**
     * 预投金额
     */
    @NotNull(message = "预投金额不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal preInvestmentAmount;
    /**
     * 需求截止日期
     */
    @NotNull(message = "需求截止时间不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String endDate;
    /**
     * 需求创建时间
     */
    private Date createTime;
    /**
     * 需求更新时间
     */
    private Date updateTime;
    /**
     * 浏览次数
     */
    private Integer viewCount;
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
    /**
     * 统计(echarts表格需要,非数据库表格字段)
     */
    private int counts;
    /**
     * 关键字搜索(非数据库字段)
     */
    private String keyWord;

    private BigDecimal preInvestmentAmountBegin;

    private BigDecimal preInvestmentAmountEnd;
}
