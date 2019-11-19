package com.example.busniess.service;

import com.example.busniess.entity.InnovationActivitiesRegistrationEntity;
import com.github.pagehelper.PageInfo;


/**
 * 创新活动报名表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-15 14:59:00
 */
public interface InnovationActivitiesRegistrationService {

    /**
     * 分页查询显示
     * @return
     */
    public PageInfo showByPage(InnovationActivitiesRegistrationEntity innovationActivitiesRegistrationEntity, Integer pageNum, Integer pageSize);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public InnovationActivitiesRegistrationEntity selectByID(Integer id);

    /**
     * 新增
     * @param innovationActivitiesRegistrationEntity
     * @return
     */
    public boolean addInnovationActivitiesRegistration(InnovationActivitiesRegistrationEntity innovationActivitiesRegistrationEntity);

    /**
     * 根据id逻辑删除
     * @param id
     * @return
     */
    public boolean deleteByID(Integer id);
    /**
     * 批量删除
     * @param ids
     * @return
     */
    public boolean deleteBatch(String ids);
    /**
     * 根据id彻底删除
     * @param id
     * @return
     */
    public boolean realDeleteByID(Integer id);

    /**
     * 更新
     * @param innovationActivitiesRegistrationEntity
     * @return
     */
    public boolean updateByID(InnovationActivitiesRegistrationEntity innovationActivitiesRegistrationEntity);

    /**
     * 判断是否已经报名
     * @param username
     * @param innovationId
     * @return
     */
    public boolean isRegistration(String username,Integer innovationId);

}

