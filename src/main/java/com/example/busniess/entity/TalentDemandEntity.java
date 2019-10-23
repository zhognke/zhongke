package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @NotNull(message = "用户名不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
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
    @NotNull(message = "技术领域不能为空", groups = {UserValidator.InSet.class})
    private String technologyScope;
    /**
     * 研究方向
     */
    @NotNull(message = "研究方向不能为空", groups = {UserValidator.InSet.class})
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
     * 待遇：0 面议;1 月薪;2 年薪;
     */
    @NotNull(message = "待遇不能为空", groups = {UserValidator.InSet.class})
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
     */
    @Pattern(regexp = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$", message = "手机号码格式错误", groups = {UserValidator.InSet.class})
    private String phoneNum;
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
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 关键字搜索(非数据库字段)
     */
    private String keyWord;

    /**
     * 关联查询
     */
    private String companyName;//公司名称
    private String province;//省
    private String city;//城市
    private String district;//区县
}
