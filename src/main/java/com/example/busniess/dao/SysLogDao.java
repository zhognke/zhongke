package com.example.busniess.dao;

import com.example.busniess.entity.SysLogEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 系统日志
 * 
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-28 11:29:08
 */
public interface SysLogDao {
    /**
    * 新增
    * @param sysLogEntity
    * @return
    */
    public boolean save(SysLogEntity sysLogEntity);
    /**
    * 根据id删除
    * @return
    */
    @Delete("delete from sys_log where id= #{id};")
    public boolean deleteById(@Param("id") Integer id);
    /**
    * 修改
    * @param sysLogEntity
    * @return
    */
    public boolean updateById(SysLogEntity sysLogEntity);
    /**
    * 查询所有
    * @return
    */
	public List<SysLogEntity> selectAll();
    /**
    * 根据id查找
    * @return
    */
    public SysLogEntity selectById(Integer id);
    /**
    * 根据条件查找
    * @param sysLogEntity
    * @return
    */
    public List<SysLogEntity> search(SysLogEntity sysLogEntity);

}
