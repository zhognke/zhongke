package com.example.busniess.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FinancingEntity {
    private int id;
    private String uName;//关联用户的名字
    private String projectType;//项目类型名
    private String projectStatic;//项目状态
    private String goal;//目的
    private String type;//类型
    private String projectName;//项目名
    private String projectOutline;//项目概述
    private String advantage;//项目优势
    private String industry;//所属行业
    private String province;//省
    private String city;//市
    private String discribe;//区
    private String projecrPhase;//项目阶段
    private String pgreat;//周期最大值
    private String pless;//周期最小值
    private String period;//项目周期
    private String linkMan;//联系人
    private String phoneNumber;//电话号码
    private String extensive;//融资用途
    private String cost;//项目总投资
    private BigDecimal hascost;//以融资金额
    private String financing;//融资方式
    private String interest;//可承担利息
    private int time;//所占时间
    private String unit;//单位
    private BigDecimal projectFinancing;//计划融资
    private BigDecimal income;//计划融资所得
    private BigDecimal greater;//资金大于
    private BigDecimal less;//资金小于
    private BigDecimal profit;//利润
    private String proportion;//所占比例
    private int ageLimit;//年限
    private String lunit;//退出单位
    private int statue;//发布状态
    private Date insertTime;//插入时间
    private Date updateTiem;//更新时间
    private String reject;//驳回原因
}
