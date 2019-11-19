package com.example.busniess.service;

import com.example.busniess.entity.InnovationActivitiesEntity;
import com.github.pagehelper.PageInfo;


/**
 * 创新活动表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-15 14:59:00
 */
public interface InnovationActivitiesService {

    /**
     * 分页查询显示
     *
     * @return
     */
    public PageInfo showByPage(InnovationActivitiesEntity innovationActivitiesEntity, Integer pageNum, Integer pageSize);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    public InnovationActivitiesEntity selectByID(Integer id);

    /**
     * 新增
     *
     * @param innovationActivitiesEntity
     * @return
     */
    public boolean addInnovationActivities(InnovationActivitiesEntity innovationActivitiesEntity);

    /**
     * 根据id逻辑删除
     *
     * @param id
     * @return
     */
    public boolean deleteByID(Integer id);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public boolean deleteBatch(String ids);

    /**
     * 根据id彻底删除
     *
     * @param id
     * @return
     */
    public boolean realDeleteByID(Integer id);

    /**
     * 更新
     *
     * @param innovationActivitiesEntity
     * @return
     */
    public boolean updateByID(InnovationActivitiesEntity innovationActivitiesEntity);

    /**
     * 修改状态
     * @param id
     * @param status
     * @return
     */
    public boolean updateStatus(Integer id,Integer status);

    /**
     * 根据id查询当前活动能否报名
     * @param id
     * @return
     */
    public boolean enbaleRegistration(Integer id);
}

