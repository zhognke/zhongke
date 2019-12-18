package com.example.busniess.dao;

import com.example.busniess.entity.IntentionEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 意向表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-14 16:19:00
 */
public interface IntentionDao {
    /**
     * 新增
     *
     * @param intentionEntity
     * @return
     */
    @Insert("insert into intention (user_name,project_id,project_name,project_type,contact,phone_num,email,company_name,address,context,create_time) values (#{userName},#{projectId},#{projectName},#{projectType},#{contact},#{phoneNum},#{email},#{companyName},#{address},#{context},now())")
    public boolean insert(IntentionEntity intentionEntity);

    /**
     * 逻辑删除
     * @return
     */
    @Update("update intention set del_flag = 1 where id= #{id};")
    public boolean deleteByID(@Param("id") Integer id);
    /**
     * 批量逻辑删除
     * @return
     */
    @Update("update intention set del_flag = 1 where id in ('${ids}');")
    public boolean deleteBatch(@Param("ids")String ids);
    /**
     * 根据id删除
     * @return
     */
    @Delete("delete from intention where id= #{id};")
    public boolean realDeleteByID(@Param("id") Integer id);

    /**
     * 修改
     *
     * @param intentionEntity
     * @return
     */
    @Update("update intention set user_name=#{userName},project_id=#{projectId},project_name=#{projectName},project_type=#{projectType},contact=#{contact},phone_num=#{phoneNum},email=#{email},company_name=#{companyName},address=#{address},context=#{context} where id = #{id}")
    public boolean updateByID(IntentionEntity intentionEntity);

    /**
     * 根据id查找
     *
     * @return
     */
    @Select("select id,user_name,project_id,project_name,project_type,contact,phone_num,email,company_name,address,context,create_time,del_flag from intention where id = #{id}")
    public IntentionEntity selectByID(@Param("id")Integer id);

    /**
     * 根据条件查找(sql在mapper文件)
     *
     * @param intentionEntity
     * @return
     */
    public List<IntentionEntity> search(IntentionEntity intentionEntity);

    /**
     * 平台意向数量统计
     * @return
     */
    @Select("select count(0) from intention where del_flag = 0 and statue !=0")
    int getCounts();
}
