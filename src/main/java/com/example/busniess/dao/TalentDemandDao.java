package com.example.busniess.dao;

import com.example.busniess.entity.TalentDemandEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 人才需求表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-23 09:29:27
 */
public interface TalentDemandDao {
    /**
     * 新增
     *
     * @param talentDemandEntity
     * @return
     */
    @Insert("insert into talent_demand (user_name,company_name,province,city,district,title,content,requires,engaged_industry,engaged_industry_detail,industry_experience,technology_scope,research_direction,demands_type,people_num,degree,salary,contact,phone_num,create_time)" +
            " values (#{userName},#{companyName},#{province},#{city},#{district},#{title},#{content},#{requires},#{engagedIndustry},#{engagedIndustryDetail},#{industryExperience},#{technologyScope},#{researchDirection},#{demandsType},#{peopleNum},#{degree},#{salary},#{contact},#{phoneNum},now())")
    public boolean add(TalentDemandEntity talentDemandEntity);

    /**
     * 删除(修改删除状态为1)
     * @param id
     * @return
     */
    @Update("update talent_demand set del_flag = 1 where id = #{id}")
    public boolean deleteById(@Param("id")Integer id);
    /**
     * 根据id删除
     * @return
     */
    @Delete("delete from talent_demand where id= #{id};")
    public boolean realDeleteById(@Param("id") Integer id);

    /**
     * 修改
     *
     * @param talentDemandEntity
     * @return
     */
    public boolean updateById(TalentDemandEntity talentDemandEntity);

    /**
     * 关闭需求
     * @param id
     * @param status
     * @param closeReason
     * @return
     */
    @Update("update talent_demand set status = #{status},close_reason=#{closeReason} where id = #{id}")
    public boolean closeById(@Param("id")Integer id,@Param("status")Integer status,@Param("closeReason")String closeReason);
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select d.id,d.user_name,d.title,d.content,d.requires,d.engaged_industry,d.engaged_industry_detail,d.industry_experience,d.technology_scope,d.research_direction," +
            "d.demands_type,d.people_num,d.salary,d.contact,d.phone_num,d.status,d.approval_status,d.approval_opinion,d.create_time," +
            "d.update_time,d.company_name,d.province,d.city,d.district " +
            "from talent_demand d ,user u " +
            "where d.user_name = u.username")
    public List<TalentDemandEntity> selectAll();

    /**
     * 根据id查找
     *
     * @return
     */
    @Select("select d.id,d.user_name,d.title,d.content,d.requires,d.engaged_industry,d.engaged_industry_detail,d.industry_experience,d.technology_scope,d.research_direction," +
            "d.demands_type,d.people_num,d.degree,d.salary,d.contact,d.phone_num,d.status,d.approval_status,d.approval_opinion,d.create_time," +
            "d.update_time,d.company_name,d.province,d.city,d.district,d.view_count " +
            "from talent_demand d ,user u " +
            "where d.user_name = u.username and d.id=#{id}")
    public TalentDemandEntity selectById(@Param("id")Integer id);

    /**
     * 根据条件查找
     *
     * @param talentDemandEntity
     * @return
     */
    public List<TalentDemandEntity> search(TalentDemandEntity talentDemandEntity);

    /**
     * 更新查看次数
     * @param id
     * @param viewCount
     */
    @Update("update talent_demand set view_count = #{viewCount} where id = ${id}")
    void updateArticleViewCount(@Param("id")Integer id, @Param("viewCount")Integer viewCount);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Update("update talent_demand set del_flag = 1 where id in ('${ids}');")
    boolean deleteBatch(@Param("ids")String ids);

    /**
     * 人才需求行业占比
     * @return
     */
    @Select("SELECT count(0) counts,engaged_industry FROM `talent_demand` where status =0 and approval_status = 1 and del_flag=0 group by engaged_industry order by counts desc limit #{size}")
    List<TalentDemandEntity> demandsIndustryProp(@Param("size")Integer size);

    @Select("select count(0) from talent_demand where `status` = 0 and approval_status =1 and del_flag =0")
    int getCounts();

    @Select("SELECT count(0) as counts,DATE_FORMAT(create_time,#{format}) companyName FROM `talent_demand` where del_flag = 0 and status = 0 and approval_status = 1 group by companyName desc limit #{size}")
    List<TalentDemandEntity> demandsRiseTrendByDate(@Param("format")String format, @Param("size")Integer size);
}
