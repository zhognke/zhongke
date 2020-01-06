package com.example.busniess.service;

import com.example.busniess.entity.AdviceBoxEntity;
import com.github.pagehelper.PageInfo;

import java.util.Map;


/**
 * 意见信箱表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2020-01-06 09:01:24
 */
public interface AdviceBoxService {

    /**
     * 分页查询显示
     *
     * @return
     */
    PageInfo showByPage(AdviceBoxEntity adviceBoxEntity, Integer pageNum, Integer pageSize);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    AdviceBoxEntity selectByID(Integer id);

    /**
     * 新增
     *
     * @param adviceBoxEntity
     * @return
     */
    boolean addAdviceBox(AdviceBoxEntity adviceBoxEntity);

    /**
     * 根据id逻辑删除
     *
     * @param id
     * @return
     */
    boolean deleteByID(Integer id);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    boolean deleteBatch(String ids);

    /**
     * 指派
     *
     * @param adviceBoxEntity
     * @return
     */
    boolean assignment(AdviceBoxEntity adviceBoxEntity);

    /**
     * 回信
     *
     * @param adviceBoxEntity
     * @return
     */
    boolean reply(AdviceBoxEntity adviceBoxEntity);

    /**
     * 是否显示
     *
     * @param adviceBoxEntity
     * @return
     */
    boolean isShow(AdviceBoxEntity adviceBoxEntity);

    Map<String,Integer> getCount();
}

