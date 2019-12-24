package com.example.busniess.search.dao;

import com.example.busniess.search.model.EsTalentDemandModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 人才信息表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-12-12 11:42:14
 */
public interface EsTalentDemandDao {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select d.id,d.user_name,d.title,d.content,d.engaged_industry,d.engaged_industry industry,d.engaged_industry_detail,d.engaged_industry_detail industry_detail,d.industry_experience,d.technology_scope,d.research_direction," +
            "d.demands_type,d.people_num,d.degree,d.salary,d.contact,d.phone_num,d.status,d.approval_status,d.approval_opinion,d.create_time,d.update_time,d.company_name," +
            "d.province,d.city,d.district,d.view_count,d.del_flag " +
            "from talent_demand d,user u " +
            "where d.user_name = u.username and u.statue != 3 and d.del_flag = 0")
    List<EsTalentDemandModel> selectAll();

    @Select("select d.id,d.user_name,d.title,d.content,d.engaged_industry,d.engaged_industry industry,d.engaged_industry_detail,d.engaged_industry_detail industry_detail,d.industry_experience,d.technology_scope,d.research_direction," +
            "d.demands_type,d.people_num,d.degree,d.salary,d.contact,d.phone_num,d.status,d.approval_status,d.approval_opinion,d.create_time,d.update_time,d.company_name," +
            "d.province,d.city,d.district,d.view_count,d.del_flag " +
            "from talent_demand d,user u " +
            "where d.user_name = u.username and u.statue != 3 and d.del_flag = 0 and d.id = #{id}")
    EsTalentDemandModel selectById(Integer id);
}
