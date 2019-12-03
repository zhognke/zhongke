package com.example.busniess.dao;

import com.example.busniess.search.model.EsIndustryDeclareModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 搜索系统中的商品管理自定义Dao
 * Created by macro on 2018/6/19.
 */
public interface EsIndustryDeclareDao {
    List<EsIndustryDeclareModel> getAllEsDemandsList(@Param("id") Integer id);
    /**
     * 根据id查找需求(详情)
     * @param id    需求id
     * @return
     */
    @Select("select id,declaration_type,is_new,declaration_industry,industry_type_detail,project_type,project_type_detail,company_name,project_name,project_content," +
            "province,city,district,status,approval_status,approval_opinion,DATE_FORMAT(create_time,'%Y-%m-%d') create_time,start_date,end_date,total_investment " +
            "from industrial_declaration where id=#{id};")
    public EsIndustryDeclareModel getByID(@Param("id") Integer id);
}
