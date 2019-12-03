package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "所属区不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String district;//区 ··10
    @NotBlank(message = "附件地址不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String address;//附件地址  附件
    @NotBlank(message = "手机号不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private BigInteger phoneNumber;//手机号码  （手机号码
    @NotBlank(message = "统一社会信用代码不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String societyCode;//统一社会信用代码    统一社会信用代码
    @NotBlank(message = "法人不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String legalPerson;//法人 法人代表
    @NotBlank(message = "税务登记号不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String persionCode;//身份证号  税务登记号
    //////////////////////

    /**
     * //    @NotNull(message = "规模不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
     * private Integer scale;//规模 5 从业人数
     *
     * @DateTimeFormat(pattern = "yyyy-MM-dd")
     * //    @Past(message = "注册日期必须必须小于当前日期", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
     * private Date recordDate;//注册日期   注册时间recordDate
     * //    @NotBlank(message = "注册资金不能为空",groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
     * //    @DecimalMin(value = "1", message = "注册资金不能小于1", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
     * private BigDecimal registeredCapital;//注册资金    注册资金
     * ///////////////////////////////////////
     * <p>
     * <p>
     * //    @NotBlank(message = "企业简介不能为空", groups = {UserValidator.UpDate.class, UserValidat   @NotBlank(message = "企业简介不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
     * private String enterpriseProfile;//企业简介/  企业简介
     * //    @NotBlank(message = "logo地址不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
     * private String logo;//企业logo 企业logo
     * <p>
     * //    @NotBlank(message = "身份不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
     * private String identification;//身份识别  1.法人2.代理人 2 //13
     * ///////////////////////////
     * //    @NotBlank(message = "统一社会信用不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
     * <p>
     * //    @NotBlank(message = "法人姓名不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
     * <p>
     * <p>
     * <p>
     * //    @NotBlank(message = "附件描述不能为空", groups = {UserValidator.InSet.class})
     * //    private String accessoryDesc;//附件描述 17
     * <p>
     * //    @NotNull(message = "联系电话不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
     * //    @Pattern(regexp = "((13[0-9])|(15[0-9])|(18[0,5-9]))\\d{8}",message = "输入手机号码有误")
     * <p>
     * <p>
     * <p>
     * //    @NotBlank(message = "代理人姓名不能为空",groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
     * private String agentPerson;//代理人姓名 19
     * //    @NotBlank(message = "代理人身份证号不能为空",groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
     * private String apPersionCode;//代理人身份证号  20
     */
    private Integer statue;//审核状态 17 0审核中 1审核通过 2审核驳回21
    private Date subTime;//提交时间22
    private Date upTime;//更新时间23
    private Date auditTime;//审核时间//24
    private Reject reject;//关联驳回

    private Integer scaleBegin; //范围搜索-从业人数最小值,非数据库字段
    private Integer scaleEnd;   //范围搜索-从业人数最大值,非数据库字段
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recordDateBegin;   //范围搜索-注册时间最小值,非数据库字段
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recordDateEnd; //范围搜索-注册时间最大值,非数据库字段

    private BigDecimal registeredCapitalBegin;  //范围搜索-注册资金最小值,非数据库字段
    private BigDecimal registeredCapitalEnd;    //范围搜索-注册资金最大值,非数据库字段
    private String keyWord; //搜索关键字,非数据库字段
    private List<Occupancy> occupancyList;  //科技成果列表,非数据库字段
    private BusinessInformation businessInformation;
}
