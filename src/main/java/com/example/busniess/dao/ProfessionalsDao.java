package com.example.busniess.dao;

import com.example.busniess.entity.ProfessionalsEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 专家信息表
 * 
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-24 11:42:14
 */
public interface ProfessionalsDao {
    /**
    * 新增
    * @param professionalsEntity
    * @return
    */
    public boolean add(ProfessionalsEntity professionalsEntity);
    /**
    * 根据id删除
    * @return
    */
    @Delete("delete from professionals where id= #{id};")
    public boolean realDelectById(@Param("id") Integer id);
    /**
    * 修改
    * @param professionalsEntity
    * @return
    */
    public boolean updateById(ProfessionalsEntity professionalsEntity);
    /**
    * 查询所有
    * @return
    */
	public List<ProfessionalsEntity> selectAll();
    /**
    * 根据id查找
    * @return
    */
    public ProfessionalsEntity selectById(Integer id);
    /**
    * 根据条件查找
    * @param professionalsEntity
    * @return
    */
    public List<ProfessionalsEntity> search(ProfessionalsEntity professionalsEntity);

}
