package com.example.busniess.dao;

import com.example.busniess.entity.System;
import org.apache.ibatis.annotations.Delete;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SystemDao {

    /**
     * 新增
     */
    public Boolean insertSystem(List<String> a);

    /**
     * 查询
     */
    @Select("SELECT * FROM system")
    public List<System> selectSystem();

    /**
     * 修改
     */
    public Boolean updateSystem(List<System> system);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM system WHERE id=#{id}")
    public Boolean delectSysem(Integer id);


}
