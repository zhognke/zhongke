package com.example.busniess.dao;

import com.example.busniess.entity.ProfessionalsEntity;
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
    @Insert("insert into professionals (user_name,real_name,pin_yin,institutions,positions,engaged_industry,engaged_industry_detail,industry_experience,technology_scope,technology_scope_detail," +
            "outstanding_contribution,research_direction,introduced,research_results,awards,phone_num,province,city,district,icon_address,certificate_address,approval_status,create_time) " +
            "values (#{userName},#{realName},#{pinYin},#{institutions},#{positions},#{engagedIndustry},#{engagedIndustryDetail},#{industryExperience},#{technologyScope},#{technologyScopeDetail}," +
            "#{outstandingContribution},#{researchDirection},#{introduced},#{researchResults},#{awards},#{phoneNum},#{province},#{city},#{district},#{iconAddress},#{certificateAddress},#{approvalStatus},now());")
    boolean add(ProfessionalsEntity professionalsEntity);
    /**
     * 逻辑删除
     * @return
     */
    @Update("update professionals set del_flag = 1 where id= #{id};")
    boolean deleteById(@Param("id") Integer id);
    /**
    * 根据id删除
    * @return
    */
    @Delete("delete from professionals where id= #{id};")
    boolean realDeleteById(@Param("id") Integer id);
    /**
    * 修改
    * @param professionalsEntity
    * @return
    */
    boolean updateById(ProfessionalsEntity professionalsEntity);
    /**
     * 根据id查找
     * @return
     */
    @Select("select id,user_name,real_name,pin_yin,institutions,positions,engaged_industry,engaged_industry_detail,industry_experience,technology_scope,technology_scope_detail," +
            "outstanding_contribution,research_direction,introduced,research_results,awards,phone_num,province,city,district,icon_address,certificate_address,status,approval_status,approval_opinion," +
            "create_time,update_time from professionals where id = #{id}")
    ProfessionalsEntity selectById(Integer id);
    /**
    * 查询所有
    * @return
    */
    @Select("select id,user_name,real_name,pin_yin,institutions,positions,engaged_industry,engaged_industry_detail,industry_experience,technology_scope,technology_scope_detail," +
            "outstanding_contribution,research_direction,introduced,research_results,awards,phone_num,province,city,district,icon_address,certificate_address,status,approval_status,approval_opinion," +
            "create_time,update_time from professionals")
    List<ProfessionalsEntity> selectAll();
    /**
    * 根据条件查找
    * @param professionalsEntity
    * @return
    */
    List<ProfessionalsEntity> search(ProfessionalsEntity professionalsEntity);
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

    @Update("update professionals set del_flag = 1 where id in ('${ids}');")
    boolean deleteBatch(@Param("ids")String ids);

    @Select("select id,real_name,pin_yin,institutions,positions,technology_scope,technology_scope_detail,research_direction,introduced,research_results,awards,icon_address from professionals where status=0 and approval_status=1 and del_flag = 0 and is_hot =1 order by sort")
    List<ProfessionalsEntity> showHot();

    @Update("update professionals set is_hot = #{isHot} where id = #{id}")
    boolean updateHot(Integer id, Integer isHot);

    @Update("update professionals set is_top = #{isTop} where id = #{id}")
    boolean updateTop(Integer id, Integer isTop);
}
