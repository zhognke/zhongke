package com.example.busniess.search.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 创新活动表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-15 14:59:00
 */
@Data
@Document(indexName = "activities", type = "activities",shards = 3)
public class EsInnovationActivitiesModel extends EsModel implements Serializable {

    private String indexType="创新活动"; //数据类型
    /**
     * 活动主题
     */
    private String activitiesTopic;
    /**
     * 活动内容500字以内
     */
    private String activitiesContent;
    /**
     * 活动类型
     */
    private String activitiesType;
    /**
     * 主办单位
     */
    private String organizers;
    /**
     * 承办单位
     */
    private String contractors;
    /**
     * 协办单位
     */
    private String coOrganizers;
    /**
     * 活动开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8" )
    private Date startTime;
    /**
     * 活动结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8" )
    private Date endTime;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区县
     */
    private String district;
    /**
     * 活动图片地址(多个图片用","隔开)
     */
    private String imgAddrs;
    /**
     * 状态:0报名中;1进行中;2已结束;3活动取消
     */
    private String status;
    /**
     * 0未删除;1删除
     */
    private Integer delFlag;

    private Integer approvalStatus = 1;
    private String keyword;
}
