package com.example.busniess.search.dao;

import com.example.busniess.search.model.EsInnovationActivitiesModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 创新活动表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-12-12 11:42:14
 */
public interface EsInnovationActivitiesDao {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select id,activities_topic,activities_topic title,activities_content,activities_content content,activities_type,organizers,contractors,co_organizers,start_time,end_time,province,city,district," +
            "img_addrs,status,create_time,update_time,del_flag " +
            "from innovation_activities " +
            "where status != 3 and del_flag = 0")
    List<EsInnovationActivitiesModel> selectAll();

    @Select("select id,activities_topic,activities_topic title,activities_content,activities_content content,activities_type,organizers,contractors,co_organizers,start_time,end_time,province,city,district," +
            "img_addrs,status,create_time,update_time,del_flag " +
            "from innovation_activities " +
            "where status != 3 and del_flag = 0")
    EsInnovationActivitiesModel selectById(Integer id);
}
