package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 人才需求表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-23 09:29:27
 */
@Data
public class TalentDemandEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @NotNull(message = "id不能为空", groups = {UserValidator.UpDate.class})
    private Integer id;
    /**
     * 关联的用户名
     */
    @NotNull(message = "标题不能为空", groups = {UserValidator.InSet.class,UserValidator.UpDate.class})
    private String userName;
    /**
     * 标题
     */
    @NotNull(message = "标题不能为空", groups = {UserValidator.InSet.class})
    private String title;
    /**
     * 详细描述
     */
    @NotNull(message = "详细描述不能为空", groups = {UserValidator.InSet.class})
    private String content;
    /**
     * 任职要求
     */
    @NotNull(message = "任职要求不能为空", groups = {UserValidator.InSet.class})
    private String requires;
    /**
     * 从事行业
     */
    @NotNull(message = "从事行业不能为空", groups = {UserValidator.InSet.class})
    private String engagedIndustry;
    /**
     * 从事行业二级目录
     */
    private String engagedIndustryDetail;
    /**
     * 行业经验
     */
    @NotNull(message = "行业经验不能为空", groups = {UserValidator.InSet.class})
    private String industryExperience;
    /**
     * 技术领域
     */
//    @NotNull(message = "技术领域不能为空", groups = {UserValidator.InSet.class})
    private String technologyScope;
    /**
     * 研究方向
     */
//    @NotNull(message = "研究方向不能为空", groups = {UserValidator.InSet.class})
    private String researchDirection;
    /**
     * 需求类型
     */
    @NotNull(message = "需求类型不能为空", groups = {UserValidator.InSet.class})
    private String demandsType;
    /**
     * 所需人数
     */
    @NotNull(message = "所需人数不能为空", groups = {UserValidator.InSet.class})
    private Integer peopleNum;
    /**
     * 学历
     */
    @NotNull(message = "学历不能为空", groups = {UserValidator.InSet.class})
    private String degree;
    /**
     * 待遇：0 面议;1 月薪;2 年薪;
     */
//    @NotNull(message = "待遇不能为空", groups = {UserValidator.InSet.class})
    private String remuneration;
    /**
     * 薪资
     */
    private String salary;
    /**
     * 联系人
     */
    @NotNull(message = "联系人不能为空", groups = { UserValidator.InSet.class})
    private String contact;
    /**
     * 联系电话
     * 改成邮箱字段
     */
//    @Pattern(regexp = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$", message = "手机号码格式错误", groups = {UserValidator.InSet.class})

    @Email(message = "邮箱格式错误", groups = { UserValidator.InSet.class})
    @NotBlank(message = "邮箱不能为空",groups = { UserValidator.InSet.class})
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
     * 是否删除;0未删除,1已删除
     */
    private Integer delFlag;
    /**
     * 关键字搜索(非数据库字段)
     */
    private String keyWord;

    @NotNull(message = "公司名称不能为空", groups = { UserValidator.InSet.class})
    private String companyName;//公司名称
    @NotNull(message = "所在省份不能为空", groups = { UserValidator.InSet.class})
    private String province;//省
    @NotNull(message = "所在城市不能为空", groups = { UserValidator.InSet.class})
    private String city;//城市
//    @NotNull(message = "所在区县不能为空", groups = { UserValidator.InSet.class})
    private String district;//区县
    /**
     * 浏览量统计
     */
    private int viewCount;
    /**
     * 关联查询
     */
    private String logo;//公司logo
    private String typeEnterprise;//企业类型
    BusinessCenterInformationEntity businessCenter;
    List<Occupancy> occupancyList;

    String industryExperienceBegin;
    String industryExperienceEnd;
    private int counts;

    private String orderField;  //排序字段

    private String orderType;   //排序方式
}
