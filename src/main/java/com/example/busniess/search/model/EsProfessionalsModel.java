package com.example.busniess.search.model;

import com.example.busniess.entity.Occupancy;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 专家信息表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-24 11:42:14
 */
@Data
@Document(indexName = "professional", type = "professional",shards = 3)
public class EsProfessionalsModel extends EsModel implements Serializable {

    private String indexType="推荐专家"; //数据类型
    /**
     * 关联用户名
     */
    @Field(type = FieldType.Keyword)
    private String userName;
    /**
     * 专家姓名
     */
    @Field(type = FieldType.Keyword)
    private String realName;
    /**
     * 所在单位
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String institutions;
	/**
	 * 专家职务
	 */
	private String positions;
    /**
     * 行业经验
     */
    private String industryExperience;
    /**
     * 技术领域
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String technologyScope;
    /**
     * 技术领域二级目录
     */
    private String technologyScopeDetail;
    /**
     * 研究方向
     */
    private String researchDirection;
    /**
     * 专家介绍
     */
    private String introduced;
    /**
     * 联系电话
     */
    private String phoneNum;
    /**
     * 省份
     */
    private String province;//省
    /**
     * 城市
     */
    private String city;//城市
    /**
     * 区县
     */
    private String district;//区县
    /**
     * 头像地址
     */
    private String iconAddress;
    /**
     * 附件地址
     */
    private String certificateAddress;
	/**
	 * 附件地址数组(非数据库字段)
	 */
	private String[] certificateAddresss;
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
     * 审批时间
     */
    @Field(type = FieldType.Date)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date approvalTime;
    /**
     * 删除标记
     */
    private Integer delFlag;
    /**
     * 搜索关键字,非数据库字段
     */
    private String keyword;
    /**
     * 是否是热门,0否,1是
     */
    private Integer isHot;
    /**
     * 是否置顶,0否,1是
     */
    private Integer isTop;
    /**
     * 排序(热门/置顶使用)
     */
    private Integer sort;
    /**
     * 专家科技成果,非数据库字段
     */
    List<Occupancy> occupancyList;
    /**
     * 拼音
     */
    private String pinYin;
}
