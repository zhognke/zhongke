package com.example.busniess.dao;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * 企业需求表
 * 
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-28 10:31:37
 */
public class IndustrialDeclarationProvider {
    public String getIndustrialDeclarationTypeByYear(Map<String, Object> para){
        return new SQL(){{
            SELECT("declaration_type","count(0) counts");
            FROM("industrial_declaration");
            if(StringUtils.isNotEmpty(String.valueOf(para.get("year")))){
                WHERE("year(create_time)="+para.get("year"));
            }
            if(para.get("district")!=null&&StringUtils.isNotEmpty(String.valueOf(para.get("district")))){
                WHERE("district='"+para.get("district")+"'");
            }
            if(para.get("projectType")!=null&&StringUtils.isNotEmpty(String.valueOf(para.get("projectType")))){
                WHERE("project_type='"+para.get("projectType")+"'");
            }
            GROUP_BY("declaration_type");
            ORDER_BY("counts desc");
        }}.toString();
    }
}
