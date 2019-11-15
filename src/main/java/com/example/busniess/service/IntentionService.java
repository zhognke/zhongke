package com.example.busniess.service;

import com.example.busniess.entity.IntentionEntity;
import com.github.pagehelper.PageInfo;


/**
 * 意向表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-14 16:19:00
 */
public interface IntentionService {

    /**
     * 分页查询显示
     * @return
     */
    public PageInfo showByPage(IntentionEntity intentionEntity, Integer pageNum, Integer pageSize);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public IntentionEntity selectByID(Integer id);

    /**
     * 新增
     * @param intentionEntity
     * @return
     */
    public boolean addIntention(IntentionEntity intentionEntity);

    /**
     * 根据id逻辑删除
     * @param id
     * @return
     */
    public boolean deleteByID(Integer id);

    /**
     * 根据id彻底删除
     * @param id
     * @return
     */
    public boolean realDeleteByID(Integer id);

    /**
     * 更新
     * @param intentionEntity
     * @return
     */
    public boolean updateByID(IntentionEntity intentionEntity);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public boolean deleteBatch(String ids);
}

