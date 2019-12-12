package com.example.busniess.search.service;

import com.example.busniess.search.model.EsDemandsModel;
import com.example.busniess.search.model.EsOccupancyModel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 搜索管理Service
 * Created by macro on 2018/6/19.
 */
public interface EsOccupancyService {
    /**
     * 从数据库中导入所有企业需求到ES
     */
    int importAll();

    /**
     * 根据id删除信息
     */
    void delete(Integer id);

    /**
     * 删除所有数据
     */
    void deleteAll();

    /**
     * 根据id创建信息
     */
    EsOccupancyModel create(Integer id);

    /**
     * 新增信息
     * @param esOccupancyModel
     * @return
     */
    boolean create(EsOccupancyModel esOccupancyModel);
    /**
     * 批量删除商品
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    EsOccupancyModel selectById(Integer id);
    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<EsOccupancyModel> search(EsOccupancyModel keyword, Integer pageNum, Integer pageSize);

}
