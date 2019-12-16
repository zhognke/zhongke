package com.example.busniess.dao;


import com.example.busniess.entity.DictEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 数据字典表
 * 
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-10 09:41:28
 */
public interface DictDao {
    //新增
    //@Insert("insert into dict (name,type,code,value,order_num,remark,del_flag,parent_id) values (#{name},#{type},#{code},#{value},#{orderNum},#{remark},#{delFlag},#{parentId})")
    @Insert("insert into dict (type,code,value,order_num,remark,del_flag,parent_id) values (#{type},#{code},#{value},#{orderNum},#{remark},#{delFlag},#{parentId})")
    public boolean add(DictEntity entity);
    //删除
    @Update("update dict set del_flag = 1 where id = #{id};")
    boolean deleteById(@Param("id") Integer id);
    //彻底删除
    @Delete("delete from dict where id = #{id};")
    public boolean realDeleteById(@Param("id")Integer id);
    //修改
    public boolean update(DictEntity entity);
    //根据id搜索
    @Select("select id,type,code,value,order_num,remark,del_flag,parent_id from dict where del_flag = 0 id = #{id}")
    public DictEntity getById(@Param("id") Integer id);
    //根据type搜索
    @Select("select id,type,code,value,order_num,remark,del_flag,parent_id from dict where del_flag = 0 and type = #{type} order by order_num")
    public List<DictEntity> getByType(@Param("type") String type);
    //搜索所有
    @Select("select id,type,code,value,order_num,remark,del_flag,parent_id from dict where del_flag = 0")
    public List<DictEntity> selectAll();
    //根据父id搜索
    @Select("select id,type,code,value,order_num,remark,del_flag,parent_id from dict where del_flag = 0 and parent_id =#{parentId} order by order_num")
    public List<DictEntity> getByParentId(@Param("parentId") Integer parentId);
}
