package com.example.busniess.search.model;

import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.BusinessInformation;
import com.example.busniess.entity.ImageAddress;
import com.example.busniess.entity.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//科技成果入住信息
@Data
@Document(indexName = "occupancy", type = "occupancy",shards = 3)
public class EsOccupancyModel extends EsModel implements Serializable {

    private String indexType="科技成果"; //数据类型

    @Field(type = FieldType.Keyword)
    private String userName;//关联的用户

    private String resultTechnolo;//科技成果名

    @Field(type = FieldType.Keyword)
    private String stage;//科技成果阶段

    @Field(type = FieldType.Keyword)
    private String advantages;//成果类型

    @Field(type = FieldType.Keyword)
    private String attribute;//成果属性

    private String patenNname;//专利名称
    private String patenNumber;//专利号
    private BigDecimal price;//专利价格
    private String negotiable;//面议

    private String registerNumber;//软件登记号
    private String describe;//详情描述
    private String appliedRange;//应用范围

    @Field(type = FieldType.Keyword)
    private String transferType;//转让方式

    private String linkMan;//联系人
    private String phoneNumber;//联系人电话
    private String province;//省 8
    private String city;//市  9
    private String district;//区 ··10

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stopTime;//停止发布时间（过期）

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date approvalTime;//审核时间

    @Field(type = FieldType.Short)
    private Integer status;//用户的修改状态
    @Field(type = FieldType.Short)
    private Integer approvalStatus;//入住状态
    private List<ImageAddress> imgAddress;//图片地址
    private BigDecimal priceBegin;//专利价格
    private BigDecimal priceEnd;//专利价格
    private String keyword; //搜索关键字(非数据库存储字段)
    private String closeReason; //关闭原因


    private String companyName;
    private BusinessCenter businessCenter;  //存储对应企业的基本信息(名称,logo,地址,类型等)
    private BusinessInformation businessInformation;//对应

    private String hot;//热门数据



    private  Person person;//用户类别1.私人2.个人



}
