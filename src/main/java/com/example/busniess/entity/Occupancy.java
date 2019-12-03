package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//科技成果入住信息
@Data
public class Occupancy implements Serializable {
    @NotNull(message = "id参数不能为空", groups = UserValidator.UpDate.class)
    private Integer id;//id
    @NotNull(message = "用户不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String userName;//关联的用户
    @NotNull(message = "技成术果不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String resultTechnolo;//科技成果名
    @NotNull(message = "科技成果阶段不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String stage;//科技成果阶段
    @NotNull(message = "科技成果优势不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String advantages;//成果优势
    @NotNull(message = "所属行业不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String industry;//所属行业
    @NotNull(message = "所属行业二级类目不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String industryDetail;//所属行业二级类目
    @NotBlank(message = "成果属性不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String attribute;//成果属性
    @NotBlank(message = "专利名称不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String patenNname;//专利名称
    @NotBlank(message = "专利号不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String patenNumber;//专利号
    //@NotNull(message = "专利价格不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String price;//专利价格

    private String registerNumber;//软件登记号
    @NotNull(message = "详情描述不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String describe;//详情描述
    @NotNull(message = "应用范围不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String appliedRange;//应用范围

    @NotNull(message = "转让方式不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String transferType;//转让方式

    @NotNull(message = "联系人姓名不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String linkMan;//联系人
    @NotNull(message = "联系电话不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
//    @Pattern(regexp = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$", message = "手机号码格式错误", groups = {UserValidator.InSet.class})
    private String phoneNumber;//联系人电话
    @NotNull(message = "联系电话不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String  province;//省 8
    @NotNull(message = "联系电话不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String city;//市  9
    @NotNull(message = "联系电话不能为空", groups = {UserValidator.UpDate.class, UserValidator.InSet.class})
    private String district;//区 ··10

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date stopTime;//停止发布时间（过期）
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date creatTime;//创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date upTiem;//修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date auditTime;//审核时间
    private Integer kstatue;//用户的修改状态
    private Integer statue;//入住状态
    @Valid
    private List<ImageAddress> imgAddress;//图片地址
    private BigDecimal priceBegin;//专利价格
    private BigDecimal priceEnd;//专利价格
    private String keyWord; //搜索关键字(非数据库存储字段)
    private String closeReason; //关闭原因


    private String companyName;
    private BusinessCenter businessCenter;  //存储对应企业的基本信息(名称,logo,地址,类型等)
    private BusinessInformation businessInformation;//对应

    private String hot;//热门数据
}
