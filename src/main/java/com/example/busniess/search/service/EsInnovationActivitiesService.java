package com.example.busniess.search.service;

import com.example.busniess.search.model.EsInnovationActivitiesModel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 人才需求搜索管理Service
 * Created by macro on 2018/6/19.
 */
public interface EsInnovationActivitiesService {
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
    EsInnovationActivitiesModel create(Integer id);

    /**
     * 新增信息
     * @param esDemandsModel
     * @return
     */
    boolean create(EsInnovationActivitiesModel esDemandsModel);
    /**
     * 批量删除商品
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    EsInnovationActivitiesModel selectById(Integer id);
    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<EsInnovationActivitiesModel> search(EsInnovationActivitiesModel keyword, Integer pageNum, Integer pageSize);

}
