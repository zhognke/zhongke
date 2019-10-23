package com.example.busniess.dao;


import com.example.busniess.entity.DictEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 数据字典表
 * 
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-10 09:41:28
 */
public interface DictDao {

    public boolean add(DictEntity entity);

    public boolean realDeleteById(Integer id);

    public boolean update(DictEntity entity);

    public DictEntity getById(Integer id);

    public List<DictEntity> getByType(String type);

    public List<DictEntity> selectAll();

    public List<DictEntity> getByParentId(String parentId);
}
