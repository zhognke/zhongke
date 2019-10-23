package com.example.busniess.service;

import com.example.busniess.entity.DictEntity;

import java.util.List;

/**
 * 数据字典表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-10 09:41:28
 */
public interface DictService {

    boolean add(DictEntity entity);

    boolean update(DictEntity entity);

    List<DictEntity> getAll();

    List<DictEntity> getByType(String type);

    List<DictEntity> getByParentId(String parentId);

    DictEntity getById(Integer id);

    boolean delById(Integer id);

    boolean realDeleteById(Integer id);
}

