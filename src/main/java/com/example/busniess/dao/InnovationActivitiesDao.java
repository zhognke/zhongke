package com.example.busniess.dao;

import com.example.busniess.entity.InnovationActivitiesEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 创新活动表
 * 
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-15 14:59:00
 */
public interface InnovationActivitiesDao {
    /**
    * 新增
    * @param innovationActivitiesEntity
    * @return
    */
    @Insert("insert into innovation_activities " +
            "(activities_topic,activities_content,activities_type,organizers,contractors,co_organizers,start_time,end_time,province,city,district,address,img_addrs,status,create_time)" +
            " values (#{activitiesTopic},#{activitiesContent},#{activitiesType},#{organizers},#{contractors},#{coOrganizers},#{startTime},#{endTime}," +
            "#{province},#{city},#{district},#{address},#{imgAddrs},#{status},now())")
    public boolean insert(InnovationActivitiesEntity innovationActivitiesEntity);
    /**
    * 逻辑删除
    * @return
    */
    @Update("update innovation_activities set del_flag = 1 where id= #{id};")
    public boolean deleteByID(@Param("id") Integer id);
    /**
     * 批量逻辑删除
     * @return
     */
    @Update("update innovation_activities set del_flag = 1 where id in ('${ids}');")
    public boolean deleteBatch(@Param("ids") String ids);
    /**
    * 彻底删除
    * @return
    */
    @Delete("delete from innovation_activities where id= #{id};")
    public boolean realDeleteByID(@Param("id") Integer id);
    /**
    * 修改
    * @param innovationActivitiesEntity
    * @return
    */
    @Update("update innovation_activities set activities_topic=#{activitiesTopic},activities_content=#{activitiesContent},activities_type=#{activitiesType},organizers=#{organizers}," +
            "contractors=#{contractors},co_organizers=#{coOrganizers},start_time=#{startTime},end_time=#{endTime},province=#{province},city=#{city},district=#{district}," +
            "address=#{address},img_addrs=#{imgAddrs},status=#{status} where id = #{id}")
    public boolean updateByID(InnovationActivitiesEntity innovationActivitiesEntity);

    /**
     * 更新状态
     * @param id
     * @param status
     * @return
     */
    @Update("update innovation_activities set status = #{status} where id= #{id};")
    public boolean updateStatus(@Param("id")Integer id,@Param("status")Integer status);
    /**
    * 根据id查找
    * @return
    */
    @Select("select id,activities_topic,activities_content,activities_type,organizers,contractors,co_organizers,start_time,end_time,province,city,district,address,img_addrs,status,create_time,update_time,del_flag " +
            "from innovation_activities where id = #{id}")
    public InnovationActivitiesEntity selectByID(@Param("id")Integer id);
    /**
    * 根据条件查找(sql在mapper文件)
    * @param innovationActivitiesEntity
    * @return
    */
    public List<InnovationActivitiesEntity> search(InnovationActivitiesEntity innovationActivitiesEntity);

    /**
     * 根据id查询活动能否报名(未删除且阶段处于报名中)
     * @param id
     * @return
     */
    @Select("select count(id) from innovation_activities where del_flag=0 and status =0 and id = #{id}")
    int enbaleRegistration(@Param("id")Integer id);

    @Select("SELECT count(0) as counts,DATE_FORMAT(create_time,#{format}) activitiesTopic FROM `innovation_activities` where del_flag = 0 and status!=3 group by activitiesTopic limit #{size}")
    List<InnovationActivitiesEntity> activitiesRiseTrend(String format, Integer size);
}
