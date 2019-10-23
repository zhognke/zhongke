package com.example.busniess.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IndustrialDeclarationDetailEntity {

    /**
     * 详细表id
     */
    private Integer aid;
    /**
     * 申报id
     */
    private Integer declarationId;
    /**
     * 项目所属行业:生产制造信息;互联网协同制造;经营管理信息;研发设计信息化;物流信息化综合信息平台;信息化综合集成创新等
     */
    private String projectIndustry;
    /**
     * 总投资-自筹(万)
     */
    private BigDecimal investmentSelfPrepare;
    /**
     * 总投资-贷款(万)
     */
    private BigDecimal investmentLoans;
    /**
     * 总投资-使用外汇(万美元)
     */
    private BigDecimal investmentUsingForex;
    /**
     * 已落实资金-总额
     */
    private BigDecimal implementationInvestment;
    /**
     * 已落实资金-贷款
     */
    private BigDecimal implementationLoans;
    /**
     * 待落实资金筹集意向(多选,分号隔开)
     */
    private String fundraisingIntention;
    /**
     * 累计完成投资
     */
    private BigDecimal completedInvestment;
    /**
     * 去年累计投资
     */
    private BigDecimal lastYearInvestment;
    /**
     * 当年投资
     */
    private BigDecimal currentYearInvestment;
    /**
     * 本年度预计投资总额
     */
    private BigDecimal projectedInvestment;
    /**
     * 预计投资-贷款
     */
    private BigDecimal projectedLoans;
    /**
     * 预计投资-设备投资
     */
    private BigDecimal projectedEquipment;
    /**
     * 新增经济效益-销售收入
     */
    private BigDecimal newSalesRevenue;
    /**
     * 新增经济效益-利润
     */
    private BigDecimal newProfits;
    /**
     * 新增经济效益-税金
     */
    private BigDecimal newTax;
    /**
     * 新增经济效益-出口创汇(万美元)
     */
    private BigDecimal newForex;
    /**
     * 是否属于民营企业:0否,1是
     */
    private Integer privateEnterprise;
    /**
     * 设备投资情况-项目设备投资额
     */
    private BigDecimal totalEquipmentInvestment;
    /**
     * 设备投资情况-主要设备名称及(台)套
     */
    private String equipmentNameNum;
    /**
     * 申请专利号及名称
     */
    private String patentsDeclaration;
    /**
     * 获得专利号及名称
     */
    private String patentsObtain;
    /**
     * 合作单位
     */
    private String partner;
    /**
     * 新产品投放市场时间
     */
    private String productLaunchDate;
    /**
     * 是否省级企业技术中心:0否,1是
     */
    private Integer provincialTechnologyCenter;
    /**
     * 社会效益-节能（吨标准煤/年）
     */
    private BigDecimal energySaving;
    /**
     * 社会效益-减排CO2（吨/年）
     */
    private BigDecimal co2EmissionReduction;
    /**
     * 社会效益-节水（万吨/年）
     */
    private BigDecimal waterConservation;
    /**
     * 社会效益-废弃物利用（吨/年）
     */
    private BigDecimal wasteUtilization;
    /**
     * 社会效益-消减COD（吨/年）
     */
    private BigDecimal codEmissionReduction;
    /**
     * 社会效益-削减氨氮（吨/年）
     */
    private BigDecimal ammoniaNitrogenEmissionReduction;
    /**
     * 社会效益-削减NOX（吨/年)
     */
    private BigDecimal noxEmissionReduction;
    /**
     * 社会效益-削减VOCS（吨/年）
     */
    private BigDecimal vocsEmissionReduction;
    /**
     * 社会效益-削减SO2（吨/年）
     */
    private BigDecimal so2EmissionReduction;
    /**
     * 社会效益-其他
     */
    private String otherEmissionReduction;
    /**
     * 截止去年底累计投资-贷款
     */
    private BigDecimal totalInvestmentLoans;
    /**
     * 截止去年底累计投资-自筹
     */
    private BigDecimal totalInvestmentSelfPrepare;
    /**
     * 本年度预计投资-自筹
     */
    private BigDecimal projectedSelfPrepare;
    /**
     * 建设性质
     */
    private String constructionNature;
    /**
     * 矿种
     */
    private String minerals;
    /**
     * 实施后社会效益
     */
    private String socialBenefitsAnalysis;
    /**
     * 备注(预留字段)
     */
    private String remark;
    /**
     * 高端制造业所属类别
     */
    private String advancedManufacturingType;
}
