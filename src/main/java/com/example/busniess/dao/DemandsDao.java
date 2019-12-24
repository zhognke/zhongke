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
    @Select("select d.`id`,d.user_name userName,b.firmName as companyName,`demand_type` as demandType,`cooperation_type` as cooperationType,`demand_industry` as demandIndustry,`demand_industry_detail` as demandIndustryDetail,`demand_outline` as demandOutline,`demand_content` as demandContent,`expected_result` as expectedResult,b.`city`,b.`district`,`contact`,`phone_num` as phoneNum,`pre_investment_amount` as preInvestmentAmount,`end_date` as endDate,`create_time` as createTime,`update_time` as updateTime,`remark`,`approval_status` as approvalStatus,`status` from `demands` d,businesscenter b where d.user_name = b.uname and d.del_flag=0")
	public List<DemandsEntity> selectAll();

    /**
     * 查询所有未到期的需求(包含未审核,后端页面使用)
     * @return
     */
    @Select("select d.`id`,d.user_name userName,b.firmName as companyName,`demand_type` as demandType,`cooperation_type` as cooperationType,`demand_industry` as demandIndustry,`demand_industry_detail` as demandIndustryDetail,`demand_outline` as demandOutline,`demand_content` as demandContent,`expected_result` as expectedResult,b.`city`,b.`district`,`contact`,`phone_num` as phoneNum,`pre_investment_amount` as preInvestmentAmount,`end_date` as endDate,`create_time` as createTime,`update_time` as updateTime,`remark`,`approval_status` as approvalStatus,`status` from `demands` d,businesscenter b where d.user_name = b.uname and d.status = 0 and d.del_flag=0")
    public List<DemandsEntity> selectAllShow();

    /**
     * 根据需求状态查询需求列表
     * @param status
     * @return
     */
    @Select("select d.`id`,d.user_name userName,b.firmName as companyName,`demand_type` as demandType,`cooperation_type` as cooperationType,`demand_industry` as demandIndustry,`demand_industry_detail` as demandIndustryDetail,`demand_outline` as demandOutline,`demand_content` as demandContent,`expected_result` as expectedResult,b.`city`,b.`district`,`contact`,`phone_num` as phoneNum,`pre_investment_amount` as preInvestmentAmount,`end_date` as endDate,`create_time` as createTime,`update_time` as updateTime,`remark`,`approval_status` as approvalStatus,`status` from `demands` d,businesscenter b where d.user_name = b.uname and  d.status =#{status} and d.del_flag=0")
    public List<DemandsEntity> selectByStatus(@Param("status") int status);

    /**
     * 筛选查询_关联企业认证表(sql在mapper文件)
     * @param demandsEntity
     * @return
     */
	public List<DemandsEntity> search(DemandsEntity demandsEntity);

    /**
     * 筛选查询_新_关联用户表(sql在mapper文件)
     * @param demandsEntity
     * @return
     */
    public List<DemandsEntity> searchNew(DemandsEntity demandsEntity);

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
	@Update("update `demands` set `status`=#{status},close_reason=#{reason},`update_time`=now() where `id` =#{id}")
	public boolean updateDemandsStatus(@Param("id") Integer id,@Param("status") Integer status,@Param("reason")String reason);

    /**
     * 修改记录状态为删除
     * @param id
     * @return
     */
    @Update("update `demands` set `del_flag`=1,`update_time`=now() where `id` =#{id}")
	public boolean deleteById(@Param("id") Integer id);

    /**
     * 修改需求审批状态
     * @param approvalStatus
     * @param id
     * @return
     */
    @Update("update `demands` set `approval_status`=#{approvalStatus},`approval_opinion`=#{approvalOpinion},`approval_time`=now() where `id` =#{id}")
    public boolean updateDemandsApprovalStatus(@Param("approvalStatus") Integer approvalStatus,@Param("approvalOpinion") String approvalOpinion,@Param("id") Integer id);


    /**
     * 根据id查找需求(详情)
     * @param id    需求id
     * @return
     */
    /*@Select("select d.`id`,d.user_name userName,b.firmName as companyName,`demand_type`,`cooperation_type`,`cooperation_intention`,`demand_industry`,`demand_industry_detail`," +
            "`demand_outline`,`demand_content`,`expected_result`,b.country province,b.`city`,b.`district`,`contact`,`phone_num`,`pre_investment_amount`,`end_date`,`create_time`,`update_time`," +
            "`remark`,`approval_status`,d.`status`,b.logo,b.typeEnterprise from `demands` d,businesscenter b where d.user_name = b.uname and d.id = #{id} and b.statue=1 ")*/
    @Select("select d.`id`,`user_name`,d.company_name,`demand_type`,`cooperation_type`,`cooperation_intention`,`demand_industry`,`demand_industry_detail`," +
            "`demand_outline`,`demand_content`,`expected_result`,d.`province`,d.`city`,d.`district`,`contact`,`phone_num`,`pre_investment_amount`,`end_date`,`create_time`,`update_time`," +
            "`remark`,`approval_status`,d.`status` from `demands` d,user u where d.user_name = u.username and d.id = #{id} and u.statue!=3 ")
	public DemandsEntity getByID(@Param("id") Integer id);

    /**
     * 最新需求
     * @return
     */
    //@Select("select d.`id`,d.user_name,`demand_type`,`demand_industry`,`demand_industry_detail`,`demand_outline`,b.country province,b.`city`,b.`district`,`create_time` from `demands` d,businesscenter b where d.user_name = b.uname and d.status=0 and d.approval_status=1 and b.statue=1 and d.del_flag=0 order by create_time desc limit #{size}")
    @Select("select d.`id`,d.user_name,`demand_type`,`cooperation_type`,`demand_industry`,`demand_industry_detail`,`demand_outline`,d.province,d.`city`,d.`district`,`create_time`,d.pre_investment_amount from `demands` d,user u where d.user_name = u.username and d.status=0 and d.approval_status=1 and u.statue!=3 and d.del_flag=0 order by create_time desc limit #{size}")
    public List<DemandsEntity> lastDemandsShow(@Param("size")Integer size);
    /**
     * 热门需求行业
     */
    @Select("SELECT demand_industry from `demands` where status = 0 and approval_status=1 and del_flag=0 group by demand_industry order by count(demand_industry) desc limit #{size}")
    public List<String> hotDemandsIndustry(@Param("size") Integer size);
    /**
     * 根据id删除需求
     * @param id    需求id
     * @return
     */
    @Delete("delete from demands where id=#{id}")
	public boolean deleteDemandsByID(@Param("id") Integer id);

    /**
     * 新增需求
     * @param demandsEntity
     * @return
     */
    /*@Insert("insert into `demands` " +
            "(`user_name`,`demand_type`,`cooperation_type`,`cooperation_intention`,`demand_industry`,`demand_industry_detail`,`demand_outline`,`demand_content`," +
            "`expected_result`,`contact`,`phone_num`,`pre_investment_amount`,`end_date`," +
            "`remark`,`status`,`approval_status`) values(#{userName},#{demandType},#{cooperationType},#{cooperationIntention},#{demandIndustry}," +
            "#{demandIndustryDetail},#{demandOutline},#{demandContent},#{expectedResult},#{contact},#{phoneNum}," +
            "#{preInvestmentAmount},#{endDate},#{remark},#{status},#{approvalStatus})")*/
    @Insert("insert into `demands` " +
            "(`user_name`,`company_name`,`province`,`city`,`district`,`demand_type`,`cooperation_type`,`cooperation_intention`,`demand_industry`,`demand_industry_detail`,`demand_outline`,`demand_content`," +
            "`expected_result`,`contact`,`phone_num`,`pre_investment_amount`,`end_date`," +
            "`remark`,`status`,`approval_status`) values(#{userName},#{companyName},#{province},#{city},#{district},#{demandType},#{cooperationType},#{cooperationIntention},#{demandIndustry}," +
            "#{demandIndustryDetail},#{demandOutline},#{demandContent},#{expectedResult},#{contact},#{phoneNum}," +
            "#{preInvestmentAmount},#{endDate},#{remark},#{status},#{approvalStatus})")
	public boolean insert(DemandsEntity demandsEntity);

    /**
     * 企业需求行业占比统计
     * @return
     */
    @Select("SELECT count(demand_industry) counts,demand_industry FROM `demands` where status =0 and approval_status = 1 and del_flag=0 group by demand_industry order by counts desc limit #{size}")
    public List<DemandsEntity> demandsIndustryProp(@Param("size") Integer size);

    /**
     * 企业需求增长趋势
     * @return
     */
    @Select("SELECT count(create_time) counts,DATE_FORMAT(create_time,'%Y/%m') as companyName FROM `demands` where status =0 and approval_status = 1 and del_flag=0 group by DATE_FORMAT(create_time,'%y/%m')")
    public List<DemandsEntity> demandsRiseTrend();

    @Select("SELECT count(0) as counts,DATE_FORMAT(create_time,#{format}) companyName FROM `demands` where del_flag = 0 and status = 0 and approval_status = 1 group by companyName limit #{size}")
    List<DemandsEntity> demandsRiseTrendByDate(@Param("format")String format, @Param("size")Integer size);

    @Update("update demands set del_flag = 1 where id in ('${ids}');")
    boolean deleteBatch(@Param("ids")String ids);

}
