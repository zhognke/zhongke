package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业生产信息
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2020-01-09 08:13:22
 */
@Data
public class ProductionInformationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = {UserValidator.UpDate.class})
    private Integer id;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String username;
    /**
     * 公司名称
     */
    @NotNull(message = "公司名称不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String companyName;
    /**
     * 省份
     */
    @NotNull(message = "省份不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String province;
    /**
     * 城市
     */
    @NotNull(message = "城市不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String city;
    /**
     * 区县
     */
    @NotNull(message = "区县不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String district;
    /**
     * 行业领域
     */
    @NotNull(message = "行业领域不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String industry;
    /**
     * 行业领域二级目录
     */
    @NotNull(message = "行业领域二级目录不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String industryDetail;
    /**
     * 统计时间
     */
    @NotNull(message = "统计时间不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String subDate;
//    /**
//     * 统计年份
//     */
//    @NotNull(message = "统计年份不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
//    private Integer subYear;
//    /**
//     * 统计月份
//     */
//    @NotNull(message = "统计月份不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
//    private Integer subMonth;

    /**
     * 月采购投入
     */
    @NotNull(message = "采购投入不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal purchaseInput;
    /**
     * 月生产物料投入
     */
    @NotNull(message = "生产物料投入不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal materialInput;
    /**
     * 月人员投入
     */
    @NotNull(message = "人员投入不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal staffInput;
    /**
     * 月生产总投入
     */
    @NotNull(message = "生产投入不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal totalInput;
    /**
     * 月营业额
     */
    @NotNull(message = "营业额不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal turnover;
    /**
     * 毛利润
     */
    @NotNull(message = "毛利润不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigDecimal profit;
    /**
     * 月产量
     */
    private Long productionValue;
    /**
     * 商品不良率
     */
    private Float defectiveRate;
    /**
     * 月销售数量
     */
    private Long salesNum;
    /**
     * 月用电量
     */
    private BigDecimal electricityConsumption;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 删除标记 0正常;1已删除
     */
    private Integer delFlag;

    @JsonFormat(pattern = "yyyyMM", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyyMM")
    private String startDate;   //开始时间
    @JsonFormat(pattern = "yyyyMM", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyyMM")
    private String endDate;     //结束日期
    private String typeEnterprise;
}
