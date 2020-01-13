package com.example.busniess.service;

import com.github.pagehelper.PageInfo;
import com.example.busniess.entity.ProductionInformationEntity;

import java.util.List;


/**
 * 企业生产信息
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2020-01-09 08:13:22
 */
public interface ProductionInformationService {

    /**
     * 分页查询显示
     * @return
     */
    PageInfo showByPage(ProductionInformationEntity productionInformationEntity, Integer pageNum, Integer pageSize);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public ProductionInformationEntity selectById(Integer id);

    /**
     * 新增
     * @param productionInformationEntity
     * @return
     */
    public boolean add(ProductionInformationEntity productionInformationEntity);

    /**
     * 根据id逻辑删除
     * @param id
     * @return
     */
    public boolean deleteById(Integer id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(String ids);

    /**
     * 更新
     * @param productionInformationEntity
     * @return
     */
    public boolean update(ProductionInformationEntity productionInformationEntity);

    ProductionInformationEntity selectByDate(ProductionInformationEntity productionInformationEntity);
}

