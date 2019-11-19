package com.example.busniess.dao;

import com.example.busniess.entity.InnovationActivitiesApplicationEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 创新活动申请表
 * 
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-15 14:59:00
 */
public interface InnovationActivitiesApplicationDao {
    /**
    * 新增
    * @param innovationActivitiesApplicationEntity
    * @return
    */
    @Insert("insert into innovation_activities_application " +
            "(username,activities_topic,activities_content,organizers,contractors,co_organizers,start_time,end_time,province,city,district,contact,tel,email,img_addr,create_time) " +
            "values (#{username},#{activitiesTopic},#{activitiesContent},#{organizers},#{contractors},#{coOrganizers},#{startTime},#{endTime},#{province},#{city},#{district}," +
            "#{contact},#{tel},#{email},#{imgAddr},now())")
    public boolean insert(InnovationActivitiesApplicationEntity innovationActivitiesApplicationEntity);
    /**
    * 逻辑删除
    * @return
    */
    @Update("update innovation_activities_application set del_flag = 1 where id= #{id};")
    public boolean deleteByID(@Param("id")Integer id);
    /**
     * 批量逻辑删除
     * @return
     */
    @Update("update innovation_activities_application set del_flag = 1 where id in ('${ids}');")
    public boolean deleteBatch(@Param("ids")String ids);
    /**
    * 彻底删除
    * @return
    */
    @Delete("delete from innovation_activities_application where id= #{id};")
    public boolean realDeleteByID(@Param("id")Integer id);
    /**
    * 修改
    * @param innovationActivitiesApplicationEntity
    * @return
    */
    @Update("update innovation_activities_application set username=#{username},activities_topic=#{activitiesTopic},activities_content=#{activitiesContent},organizers=#{organizers}," +
            "contractors=#{contractors},co_organizers=#{coOrganizers},start_time=#{startTime},end_time=#{endTime},province=#{province},city=#{city},district=#{district}," +
            "contact=#{contact},tel=#{tel},email=#{email},img_addr=#{imgAddr} " +
            "where id = #{id}")
    public boolean updateByID(InnovationActivitiesApplicationEntity innovationActivitiesApplicationEntity);
    /**
    * 根据id查找
    * @return
    */
    @Select("select id,username,activities_topic,activities_content,organizers,contractors,co_organizers,start_time,end_time,province,city,district,contact,tel,email,img_addr,create_time,update_time,del_flag " +
            "from innovation_activities_application where id = #{id}")
    public InnovationActivitiesApplicationEntity selectByID(@Param("id")Integer id);
    /**
    * 根据条件查找(sql在mapper文件)
    * @param innovationActivitiesApplicationEntity
    * @return
    */
    public List<InnovationActivitiesApplicationEntity> search(InnovationActivitiesApplicationEntity innovationActivitiesApplicationEntity);

}
