package com.example.busniess.dao;

import com.example.busniess.entity.ProfessionalsEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 专家信息表
 * 
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-24 11:42:14
 */
public interface ProfessionalsDao {
    /**
    * 新增
    * @param professionalsEntity
    * @return
    */
    @Insert("insert into professionals (user_name,real_name,institutions,positions,engaged_industry,engaged_industry_detail,industry_experience,technology_scope," +
            "outstanding_contribution,research_direction,introduced,phone_num,icon_address,certificate_address,create_time) " +
            "values (#{userName},#{realName},#{institutions},#{positions},#{engagedIndustry},#{engagedIndustryDetail},#{industryExperience},#{technologyScope}," +
            "#{outstandingContribution},#{researchDirection},#{introduced},#{phoneNum},#{iconAddress},#{certificateAddress},now());")
    public boolean add(ProfessionalsEntity professionalsEntity);

    /**
     * 逻辑删除
     * @return
     */
    @Delete("update professionals set del_flag = 1 where id= #{id};")
    public boolean deleteById(@Param("id") Integer id);

    /**
    * 根据id删除
    * @return
    */
    @Delete("delete from professionals where id= #{id};")
    public boolean realDeleteById(@Param("id") Integer id);

    /**
    * 修改
    * @param professionalsEntity
    * @return
    */
    public boolean updateById(ProfessionalsEntity professionalsEntity);
    /**
     * 根据id查找
     * @return
     */
    @Select("select id,user_name,real_name,institutions,positions,engaged_industry,engaged_industry_detail,industry_experience,technology_scope,outstanding_contribution,research_direction,introduced,phone_num,icon_address,certificate_address,status,approval_status,approval_opinion,create_time,update_time from professionals where id = #{id}")
    public ProfessionalsEntity selectById(Integer id);

    /**
    * 查询所有
    * @return
    */
    @Select("select id,user_name,real_name,institutions,positions,engaged_industry,engaged_industry_detail,industry_experience,technology_scope,outstanding_contribution,research_direction,introduced,phone_num,icon_address,certificate_address,status,approval_status,approval_opinion,create_time,update_time from professionals")
	public List<ProfessionalsEntity> selectAll();

    /**
    * 根据条件查找
    * @param professionalsEntity
    * @return
    */
    public List<ProfessionalsEntity> search(ProfessionalsEntity professionalsEntity);
    /**
     * 关闭
     * @param id
     * @param status
     * @param closeReason
     * @return
     */
    @Update("update professionals set status = #{status},close_reason=#{closeReason} where id = #{id}")
    boolean closeById(@Param("id")Integer id, @Param("status")Integer status, @Param("closeReason")String closeReason);

    /**
     * 修改审批状态
     * @param id
     * @param approvalStatus
     * @param approvalOpinion
     * @return
     */
    @Update("update professionals set approval_status = #{approvalStatus},approval_opinion=#{approvalOpinion},approval_time=now() where id = #{id}")
    boolean updateApprovalStatus(Integer id,Integer approvalStatus,String approvalOpinion);
}
