package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

//企业中心
@Data
public class BusinessCenter implements Serializable {

    @NotBlank(message = "主键不能为空", groups = {UserValidator.UpDate.class})
    private Integer id;//主键 1
    @NotBlank(message = "关联用户名不能为空", groups = {UserValidator.InSet.class, UserValidator.UpDate.class})
    private String uName;//关联的用户名2
    @NotBlank(message = "企业名称不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String firmName;//企业名字   企业名字
    @NotBlank(message = "所属行业不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String industry;//所属行业     行业领域
    @NotBlank(message = "企业类型不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String typeEnterprise;     //企业类型

    @NotBlank(message = "所属省不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String country;//省 8
    @NotBlank(message = "所属市不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String city;//市  9
//
    @NotBlank(message = "附件地址不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String address;//附件地址  附件
    @NotNull(message = "手机号不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigInteger phoneNumber;//手机号码  （手机号码
    @NotBlank(message = "统一社会信用代码不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String societyCode;//统一社会信用代码    统一社会信用代码
    @NotBlank(message = "法人不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String legalPerson;//法人 法人代表
    @NotBlank(message = "税务登记号不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String persionCode;//身份证号  税务登记号
    //////////////////////

    private Integer statue;//审核状态 17 0审核中 1审核通过 2审核驳回21
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date subTime;//提交时间22
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date upTime;//更新时间23
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date auditTime;//审核时间//24
    private Reject reject;//关联驳回

    private Integer scaleBegin; //范围搜索-从业人数最小值,非数据库字段
    private Integer scaleEnd;   //范围搜索-从业人数最大值,非数据库字段
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date recordDateBegin;   //范围搜索-注册时间最小值,非数据库字段
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date recordDateEnd; //范围搜索-注册时间最大值,非数据库字段

    private BigDecimal registeredCapitalBegin;  //范围搜索-注册资金最小值,非数据库字段
    private BigDecimal registeredCapitalEnd;    //范围搜索-注册资金最大值,非数据库字段
    private String keyWord; //搜索关键字,非数据库字段
    private List<Occupancy> occupancyList;  //科技成果列表,非数据库字段
    private BusinessInformation businessInformation;




//    @NotBlank(message = "所属区不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String district;//区 ··10
}
