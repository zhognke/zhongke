package com.example.busniess.dao;


import com.example.busniess.entity.DemandsEntity;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 企业需求表
 * 
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-24 10:05:26
 */
public interface DemandsDao {
    /**
     * 查询所有需求
     * @return
     */
    @Select("select d.`id`,d.user_name userName,b.firmName as companyName,`demand_type` as demandType,`cooperation_type` as cooperationType,`demand_industry` as demandIndustry,`demand_industry_detail` as demandIndustryDetail,`demand_outline` as demandOutline,`demand_content` as demandContent,`expected_result` as expectedResult,b.`city`,b.`district`,`contact`,`phone_num` as phoneNum,`pre_investment_amount` as preInvestmentAmount,`end_date` as endDate,`create_time` as createTime,`update_time` as updateTime,`remark`,`approval_status` as approvalStatus,`status` from `demands` d,businesscenter b where d.user_name = b.uname")
	public List<DemandsEntity> selectAll();

    /**
     * 查询所有未到期的需求(包含未审核,后端页面使用)
     * @return
     */
    @Select("select d.`id`,d.user_name userName,b.firmName as companyName,`demand_type` as demandType,`cooperation_type` as cooperationType,`demand_industry` as demandIndustry,`demand_industry_detail` as demandIndustryDetail,`demand_outline` as demandOutline,`demand_content` as demandContent,`expected_result` as expectedResult,b.`city`,b.`district`,`contact`,`phone_num` as phoneNum,`pre_investment_amount` as preInvestmentAmount,`end_date` as endDate,`create_time` as createTime,`update_time` as updateTime,`remark`,`approval_status` as approvalStatus,`status` from `demands` d,businesscenter b where d.user_name = b.uname and d.status 0 ")
    public List<DemandsEntity> selectAllShow();

    /**
     * 根据需求状态查询需求列表
     * @param status
     * @return
     */
    @Select("select d.`id`,d.user_name userName,b.firmName as companyName,`demand_type` as demandType,`cooperation_type` as cooperationType,`demand_industry` as demandIndustry,`demand_industry_detail` as demandIndustryDetail,`demand_outline` as demandOutline,`demand_content` as demandContent,`expected_result` as expectedResult,b.`city`,b.`district`,`contact`,`phone_num` as phoneNum,`pre_investment_amount` as preInvestmentAmount,`end_date` as endDate,`create_time` as createTime,`update_time` as updateTime,`remark`,`approval_status` as approvalStatus,`status` from `demands` d,businesscenter b where d.user_name = b.uname and  d.status =#{status} ")
    public List<DemandsEntity> selectByStatus(@Param("status") int status);

    /**
     * 筛选查询(sql在mapper文件)
     * @param demandsEntity
     * @return
     */
	public List<DemandsEntity> search(DemandsEntity demandsEntity);

    /**
     * 获取总条数(sql在mapper文件)
     * @param demandsEntity
     * @return
     */
    public int getCount(DemandsEntity demandsEntity);

    /**
     * 分页查询
     * @param demandsEntity
     * @param PageNum   当前页
     * @param Pagesize  每页显示条数
     * @return
     */
	//public List<DemandsEntity> showByPage(DemandsEntity demandsEntity,int PageNum,int Pagesize);

    /**
     * 修改需求(sql在mapper文件)
     * @param demandsEntity
     * @return
     */
	public boolean updateDemands(DemandsEntity demandsEntity);

    /**
     * 修改需求有效状态
     * @param status
     * @param id
     * @return
     */
	@Update("update `demands` set `status`=#{status},`update_time`=now() where `id` =#{id}")
	public boolean updateDemandsStatus(@Param("status") int status,@Param("id") int id);

    /**
     * 修改需求审批状态
     * @param approvalStatus
     * @param id
     * @return
     */
    @Update("update `demands` set `approval_status`=#{approvalStatus},`approval_opinion`=#{approvalOpinion},`update_time`=now() where `id` =#{id}")
    public boolean updateDemandsApprovalStatus(@Param("approvalStatus") int approvalStatus,@Param("approvalOpinion") String approvalOpinion,@Param("id") int id);


    /**
     * 根据id查找需求(详情)
     * @param id    需求id
     * @return
     */
    @Select("select d.`id`,d.user_name userName,b.firmName as companyName,`demand_type` as demandType,`cooperation_type` as cooperationType,`demand_industry` as demandIndustry,`demand_industry_detail` as demandIndustryDetail,`demand_outline` as demandOutline,`demand_content` as demandContent,`expected_result` as expectedResult,b.`city`,b.`district`,`contact`,`phone_num` as phoneNum,`pre_investment_amount` as preInvestmentAmount,`end_date` as endDate,`create_time` as createTime,`update_time` as updateTime,`remark`,`approval_status` as approvalStatus,`status` from `demands` d,businesscenter b where d.user_name = b.uname and d.id = #{id}")
	public DemandsEntity getByID(@Param("id") int id);

    /**
     * 根据id删除需求
     * @param id    需求id
     * @return
     */
    @Delete("delete from demands where id=#{id}")
	public boolean deleteDemandsByID(@Param("id") int id);

    /**
     * 新增需求
     * @param demandsEntity
     * @return
     */
    /*@Insert("insert into `demands` " +
            "(`company_name`,`demand_type`,`cooperation_type`,`cooperation_intention`,`demand_industry`,`demand_industry_detail`,`demand_outline`,`demand_content`," +
            "`expected_result`,`city`,`district`,`contact`,`phone_num`,`email`,`pre_investment_amount`,`end_date`," +
            "`create_time`,`update_time`,`remark`,`status`,`approval_status`) values(#{companyName},#{demandType},#{cooperationType},#{cooperationIntention},#{demandIndustry}," +
            "#{demandIndustryDetail},#{demandOutline},#{demandContent},#{expectedResult},#{city},#{district},#{contact},#{phoneNum},#{email}," +
            "#{preInvestmentAmount},#{endDate},#{createTime},#{updateTime},#{remark},#{status},#{approvalStatus})")*/
    @Insert("insert into `demands` " +
            "(`user_name`,`demand_type`,`cooperation_type`,`cooperation_intention`,`demand_industry`,`demand_industry_detail`,`demand_outline`,`demand_content`," +
            "`expected_result`,`contact`,`phone_num`,`pre_investment_amount`,`end_date`," +
            "`create_time`,`update_time`,`remark`,`status`,`approval_status`) values(#{userName},#{demandType},#{cooperationType},#{cooperationIntention},#{demandIndustry}," +
            "#{demandIndustryDetail},#{demandOutline},#{demandContent},#{expectedResult},#{contact},#{phoneNum}," +
            "#{preInvestmentAmount},#{endDate},#{createTime},#{updateTime},#{remark},#{status},#{approvalStatus})")
	public boolean insert(DemandsEntity demandsEntity);

    /**
     * 企业需求行业占比统计
     * @return
     */
    @Select("SELECT count(demand_industry) counts,demand_industry as demandIndustry FROM `demands` where status =0 and approval_status = 1 group by demand_industry")
    public List<DemandsEntity> demandsIndustryProp();

    /**
     * 企业需求增长趋势
     * @return
     */
    @Select("SELECT count(create_time) as counts,DATE_FORMAT(create_time,'%Y/%m') as companyName FROM `demands` where status =0 and approval_status = 1 group by DATE_FORMAT(create_time,'%y/%m')")
    public List<DemandsEntity> demandsRiseTrend();

    @Data
    class Financing implements Serializable {
        private int id;
        private  String uName;//关联用户的名字
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
        private BigDecimal income;//计划融资
        private  BigDecimal greater;//资金大于
        private  BigDecimal less;//资金小于
        private BigDecimal profit;//利润
        private String proportion;//所占比例
        private int ageLimit;//年限
        private String lunit;//退出单位
        private int statue;//发布状态
        private Date insertTime;//插入时间
        private Date updateTiem;//更新时间

    }
}
