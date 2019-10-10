package com.example.busniess.dao;

import com.example.busniess.entity.IndustrialDeclarationEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 企业需求表
 * 
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-28 10:31:37
 */
public interface IndustrialDeclarationDao {

    public List<IndustrialDeclarationEntity> select(@Param("table") String table);

    /**
     * 查询所有
     * @return
     */
    public List<IndustrialDeclarationEntity> selectAll();

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public IndustrialDeclarationEntity selectById(Integer id);

    /**
     * 新增
     * @param industrialDeclarationEntity
     * @return
     */
    public Integer add(IndustrialDeclarationEntity industrialDeclarationEntity);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Delete("delete from industrial_declaration where id= #{id};")
    public Integer realDelectById(@Param("id")Integer id);

    /**
     * 更新
     * @param industrialDeclarationEntity
     * @return
     */
    public boolean update(IndustrialDeclarationEntity industrialDeclarationEntity);

    /**
     * 查找
     * @param industrialDeclarationEntity
     * @return
     */
    public List<IndustrialDeclarationEntity> search(IndustrialDeclarationEntity industrialDeclarationEntity);

    /**
     * 工业申报行业占比统计(饼图)
     * @return
     */
    @Select("SELECT count(declaration_type) counts,declaration_type declarationType FROM `industrial_declaration` where status = 0 and approval_status = 1 group by declaration_type")
    public List<IndustrialDeclarationEntity> declartionsIndustryProp();

    /**
     * 工业申报增长趋势(折线图)
     * @return
     */
    @Select("SELECT count(create_time) as counts,DATE_FORMAT(create_time,'%Y/%m') companyName FROM `industrial_declaration` where status = 0 and approval_status = 1 group by DATE_FORMAT(create_time,'%y/%m')")
    public List<IndustrialDeclarationEntity> declartionsRiseTrend();

    /**
     * 工业申报表中的公司名称列表
     * @return
     */
    @Select("SELECT distinct company_name companyName FROM `industrial_declaration`")
    List<IndustrialDeclarationEntity> getCompanyList();
}
