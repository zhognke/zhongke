package com.example.busniess.dao;

import com.example.busniess.entity.IntentionRecordEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 意向跟进表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-12-18 19:00:04
 */
public interface IntentionRecordDao {
    /**
     * 新增
     *
     * @param intentionRecordEntity
     * @return
     */
    @Insert("insert into intention_record (intention_id,username,content,create_time) values (#{intentionId},#{username},#{content},now())")
    public boolean insert(IntentionRecordEntity intentionRecordEntity);

    /**
     * 逻辑删除
     *
     * @return
     */
    @Update("update intention_record set del_flag = 1 where id= #{id};")
    public boolean deleteByID(@Param("id") Integer id);

    /**
     * 修改
     *
     * @param intentionRecordEntity
     * @return
     */
    @Update("update intention_record set id=#{id},intention_id=#{intentionId},username=#{username},content=#{content},create_time=#{createTime},del_flag=#{delFlag}, where id = #{id}")
    public boolean updateByID(IntentionRecordEntity intentionRecordEntity);

    /**
     * 根据id查找
     *
     * @return
     */
    @Select("select id,intention_id,username,content,create_time,del_flag, from intention_record where id = #{id}")
    public IntentionRecordEntity selectByID(Integer id);

    /**
     * 根据意向id产看跟进记录
     * @param intentionId
     * @return
     */
    @Select("select username,content,create_time from intention_record where intention_id = #{intentionId} order by create_time desc")
    public List<IntentionRecordEntity> searchByIntentionId(Integer intentionId);
}
