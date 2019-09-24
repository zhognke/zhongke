package com.example.busniess.dao;


import com.example.busniess.entity.DemandsEntity;
import org.apache.ibatis.annotations.*;

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
    @Select("select `id`,`company_name`,`demand_type`,`cooperation_type`,`demand_industry`,`demand_outline`,`demand_content`,`expected_result`,`city`,`district`,`contact`,`phone_num`,`email`,`pre_investment_amount`,`end_date`,`create_time`,`update_time`,`remark`,`status`,`approval_status` from `demands`")
	public List<DemandsEntity> selectAll();

    /**
     * 查询所有未到期的需求(包含未审核,后端页面使用)
     * @return
     */
    @Select("select `id`,`company_name`,`demand_type`,`cooperation_type`,`demand_industry`,`demand_outline`,`demand_content`,`expected_result`,`city`,`district`,`contact`,`phone_num`,`email`,`pre_investment_amount`,`end_date`,`create_time`,`update_time`,`remark`,`status`,`approval_status` from `demands` where status 0 ")
    public List<DemandsEntity> selectAllShow();

    /**
     * 根据需求状态查询需求列表
     * @param status
     * @return
     */
    @Select("select `id`,`company_name`,`demand_type`,`cooperation_type`,`demand_industry`,`demand_outline`,`demand_content`,`expected_result`,`city`,`district`,`contact`,`phone_num`,`email`,`pre_investment_amount`,`end_date`,`create_time`,`update_time`,`remark`,`status` from `demands` where status =#{status} ")
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
    @Update("update `demands` set `approval_status`=#{approvalStatus},`update_time`=now() where `id` =#{id}")
    public boolean updateDemandsApprovalStatus(@Param("approvalStatus") int approvalStatus,@Param("id") int id);


    /**
     * 根据id查找需求(详情)
     * @param id    需求id
     * @return
     */
    @Select("select `id`,`company_name` as companyName,`demand_type` as demandType,`cooperation_type` as cooperationType,`demand_industry` as demandIndustry,`demand_industry_detail` as demandIndustryDetail,`demand_outline` as demandOutline,`demand_content` as demandContent,`expected_result` as expectedResult,`city`,`district`,`contact`,`phone_num` as phoneNum,`email`,`pre_investment_amount` as preInvestmentAmount,`end_date` as endDate,`create_time` as createTime,`update_time` as updateTime,`remark`,`approval_status` as approvalStatus,`status` from `demands` where id = #{id}")
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
    @Insert("insert into `demands` " +
            "(`company_name`,`demand_type`,`cooperation_type`,`demand_industry`,`demand_industry_detail`,`demand_outline`,`demand_content`," +
            "`expected_result`,`city`,`district`,`contact`,`phone_num`,`email`,`pre_investment_amount`,`end_date`," +
            "`create_time`,`update_time`,`remark`,`status`,`approval_status`) values(#{companyName},#{demandType},#{cooperationType},#{demandIndustry}," +
            "#{demandIndustryDetail},#{demandOutline},#{demandContent},#{expectedResult},#{city},#{district},#{contact},#{phoneNum},#{email}," +
            "#{preInvestmentAmount},#{endDate},#{createTime},#{updateTime},#{remark},#{status},#{approvalStatus})")
	public boolean insert(DemandsEntity demandsEntity);


}
