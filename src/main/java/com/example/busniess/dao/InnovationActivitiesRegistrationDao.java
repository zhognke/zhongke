package com.example.busniess.dao;

import com.example.busniess.entity.InnovationActivitiesRegistrationEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 创新活动报名表
 * 
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-15 14:59:00
 */
public interface InnovationActivitiesRegistrationDao {
    /**
    * 新增
    * @param innovationActivitiesRegistrationEntity
    * @return
    */
    @Insert("insert into innovation_activities_registration " +
            "(innovation_id,username,realname,tel,email,province,city,district,company_name,positions,context,create_time) " +
            "values (#{innovationId},#{username},#{realname},#{tel},#{email},#{province},#{city},#{district},#{companyName},#{positions},#{context},now())")
    public boolean insert(InnovationActivitiesRegistrationEntity innovationActivitiesRegistrationEntity);
    /**
    * 逻辑删除
    * @return
    */
    @Update("update innovation_activities_registration set del_flag = 1 where id= #{id};")
    public boolean deleteByID(@Param("id")Integer id);
    /**
     * 批量逻辑删除
     * @return
     */
    @Update("update innovation_activities_registration set del_flag = 1 where id in ('${ids}');")
    public boolean deleteBatch(@Param("ids")String ids);
    /**
    * 彻底删除
    * @return
    */
    @Delete("delete from innovation_activities_registration where id= #{id};")
    public boolean realDeleteByID(@Param("id")Integer id);
    /**
    * 修改
    * @param innovationActivitiesRegistrationEntity
    * @return
    */
    @Update("update innovation_activities_registration set innovation_id=#{innovationId},username=#{username},realname=#{realname},tel=#{tel},email=#{email}," +
            "province=#{province},city=#{city},district=#{district},company_name=#{companyName},positions=#{positions},context=#{context} where id = #{id}")
    public boolean updateByID(InnovationActivitiesRegistrationEntity innovationActivitiesRegistrationEntity);
    /**
    * 根据id查找
    * @return
    */
    @Select("select id,innovation_id,username,realname,tel,email,province,city,district,company_name,positions,context,del_flag,create_time,update_time " +
            "from innovation_activities_registration where id = #{id}")
    public InnovationActivitiesRegistrationEntity selectByID(@Param("id")Integer id);
    /**
    * 根据条件查找(sql在mapper文件)
    * @param innovationActivitiesRegistrationEntity
    * @return
    */
    public List<InnovationActivitiesRegistrationEntity> search(InnovationActivitiesRegistrationEntity innovationActivitiesRegistrationEntity);

    /**
     * 判断是否已经报名
     * @param username
     * @param innovationId
     * @return
     */
    @Select("select id,realname from innovation_activities_registration where del_flag = 0 and innovation_id = #{innovationId} and username=#{username}")
    public InnovationActivitiesRegistrationEntity isRegistration(@Param("username")String username, @Param("innovationId")Integer innovationId);
}
