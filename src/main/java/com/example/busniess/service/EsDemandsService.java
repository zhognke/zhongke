package com.example.busniess.service;

import com.example.busniess.search.model.EsDemands;


import java.util.List;

/**
 * 商品搜索管理Service
 * Created by macro on 2018/6/19.
 */
public interface EsDemandsService {
    /**
     * 从数据库中导入所有商品到ES
     */
    int importAll();

    /**
     * 根据id删除商品
     */
    void delete(Integer id);

    /**
     * 根据id创建商品
     */
    EsDemands create(Integer id);

    /**
     * 批量删除商品
     */
    void delete(List<Integer> ids);

    /**
     * 根据关键字搜索名称或者副标题
     */
//    Page<EsDemands> search(String keyword, Integer pageNum, Integer pageSize);

}
