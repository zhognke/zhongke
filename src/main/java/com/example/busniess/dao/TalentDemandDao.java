package com.example.busniess.dao;

import com.example.busniess.entity.TalentDemandEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 人才需求表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-23 09:29:27
 */
public interface TalentDemandDao {
    /**
     * 新增
     *
     * @param talentDemandEntity
     * @return
     */
    public boolean add(TalentDemandEntity talentDemandEntity);

    /**
     * 根据id删除
     *
     * @return
     */
    @Delete("delete from talent_demand where id= #{id};")
    public boolean realDelectById(@Param("id") Integer id);

    /**
     * 修改
     *
     * @param talentDemandEntity
     * @return
     */
    public boolean updateById(TalentDemandEntity talentDemandEntity);

    /**
     * 查询所有
     *
     * @return
     */
    public List<TalentDemandEntity> selectAll();

    /**
     * 根据id查找
     *
     * @return
     */
    public TalentDemandEntity selectById(Integer id);

    /**
     * 根据条件查找
     *
     * @param talentDemandEntity
     * @return
     */
    public List<TalentDemandEntity> search(TalentDemandEntity talentDemandEntity);

}
