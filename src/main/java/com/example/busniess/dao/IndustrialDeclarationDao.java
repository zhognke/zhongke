package com.example.busniess.dao;

import com.example.busniess.entity.IndustrialDeclarationEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 工业申报
 * 
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-28 10:31:37
 */
public interface IndustrialDeclarationDao {

    /**
     * 查询所有
     * @return
     */
    @Select("select id,declaration_type,project_type,company_name,project_name,status,approval_status,DATE_FORMAT(create_time,'%Y-%m-%d') create_time from industrial_declaration where del_flag = 0")
    List<IndustrialDeclarationEntity> selectAll();

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @Select("select id,declaration_type,is_new,declaration_industry,industry_type_detail,project_type,project_type_detail,company_name,project_name,project_content," +
            "province,city,district,status,approval_status,approval_opinion,DATE_FORMAT(create_time,'%Y-%m-%d') create_time,start_date,end_date,total_investment " +
            "from industrial_declaration where del_flag = 0 and id=#{id};")
    IndustrialDeclarationEntity selectById(Integer id);

    /**
     * 新增
     * @param industrialDeclarationEntity
     * @return
     */
    @Insert("insert into industrial_declaration(declaration_type,is_new,declaration_industry,industry_type_detail,project_type,project_type_detail,company_name,project_name,project_content,province,city,district,start_date,end_date,total_investment) " +
            "value (#{declarationType},#{isNew},#{declarationIndustry},#{industryTypeDetail},#{projectType},#{projectTypeDetail},#{companyName},#{projectName},#{projectContent},#{province},#{city},#{district},#{startDate},#{endDate},#{totalInvestment})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    Integer add(IndustrialDeclarationEntity industrialDeclarationEntity);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Delete("delete from industrial_declaration where id= #{id};")
    Integer realDelectById(@Param("id") Integer id);

    /**
     * 根据id删除
     */
    @Update("update industrial_declaration set del_flag = 1 where id = #{id}")
    boolean deleteById(@Param("id") Integer id);
    /**
     * 更新
     * @param industrialDeclarationEntity
     * @return
     */
    boolean update(IndustrialDeclarationEntity industrialDeclarationEntity);
    /**
     * 修改状态
     * @param id
     * @param status
     * @param closeReason
     * @return
     */
    @Update("update industrial_declaration set status = #{status},close_reason = #{closeReason} where id = #{id}")
    boolean updateStatus(@Param("id") Integer id, @Param("id") Integer status, @Param("id") String closeReason);
    /**
     * 查找
     * @param industrialDeclarationEntity
     * @return
     */
    List<IndustrialDeclarationEntity> search(IndustrialDeclarationEntity industrialDeclarationEntity);

    /**
     * 工业申报行业占比统计(饼图)
     * @return
     */
    @Select("SELECT count(declaration_type) counts,declaration_type declarationType FROM `industrial_declaration` where del_flag = 0 and status = 0 and approval_status = 1 group by declaration_type")
    List<IndustrialDeclarationEntity> declartionsIndustryProp();

    /**
     * 工业申报增长趋势年(折线图)
     * @return
     */
    @Select("SELECT count(0) as counts,DATE_FORMAT(create_time,#{format}) companyName FROM `industrial_declaration` where del_flag = 0 and status = 0 and approval_status = 1 group by companyName desc limit #{size}")
    List<IndustrialDeclarationEntity> declartionsRiseTrend(@Param("format") String format,@Param("size") Integer size);

    /**
     * 工业申报表中的公司名称列表
     * @return
     */
    @Select("SELECT distinct company_name companyName FROM `industrial_declaration`")
    List<IndustrialDeclarationEntity> getCompanyList();

    @Select("select project_name,approval_status,project_type,create_time from industrial_declaration where del_flag = 0 and status=0 and approval_status =1 order by create_time desc limit #{size}")
    List<IndustrialDeclarationEntity> lastDeclarations(@Param("size")Integer size);

    @Update("update industrial_declaration set del_flag = 1 where id in ('${ids}');")
    boolean deleteBatch(@Param("ids")String ids);

    /**
     * 工业申报统计
     * @param year  年份
     * @return
     */
//    @Select("SELECT declaration_type,count(0) counts FROM `industrial_declaration` where year(create_time) = #{year} group by declaration_type order by counts desc;")
    @SelectProvider(type=IndustrialDeclarationProvider.class,method = "getIndustrialDeclarationTypeByYear")
    List<IndustrialDeclarationEntity> getIndustrialDeclarationTypeByYear(Integer year,String district,String projectType);
}
