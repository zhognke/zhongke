package com.example.busniess.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.example.busniess.entity.IntentionRecordEntity;


/**
 * 意向跟进表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-12-18 19:00:04
 */
public interface IntentionRecordService {

    /**
     * 分页显示
     * @return
     */
    public PageInfo showByPage(Integer intentionId, Integer pageNum, Integer pageSize);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public IntentionRecordEntity selectByID(Integer id);

    /**
     * 新增
     * @param intentionRecordEntity
     * @return
     */
    public boolean addIntentionRecord(IntentionRecordEntity intentionRecordEntity);

    /**
     * 根据id逻辑删除
     * @param id
     * @return
     */
    public boolean deleteByID(Integer id);

    /**
     * 更新
     * @param intentionRecordEntity
     * @return
     */
    public boolean updateByID(IntentionRecordEntity intentionRecordEntity);


}

