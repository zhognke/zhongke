package com.example.busniess.service;

import com.example.busniess.entity.InnovationActivitiesApplicationEntity;
import com.github.pagehelper.PageInfo;


/**
 * 创新活动申请表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-15 14:59:00
 */
public interface InnovationActivitiesApplicationService {

    /**
     * 分页查询显示
     *
     * @return
     */
    public PageInfo showByPage(InnovationActivitiesApplicationEntity innovationActivitiesApplicationEntity, Integer pageNum, Integer pageSize);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    public InnovationActivitiesApplicationEntity selectByID(Integer id);

    /**
     * 新增
     *
     * @param innovationActivitiesApplicationEntity
     * @return
     */
    public boolean addInnovationActivitiesApplication(InnovationActivitiesApplicationEntity innovationActivitiesApplicationEntity);

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
     * @param innovationActivitiesApplicationEntity
     * @return
     */
    public boolean updateByID(InnovationActivitiesApplicationEntity innovationActivitiesApplicationEntity);

    /**
     * 审批通过
     * @param id
     * @return
     */
    boolean updateApprovalStatusPass(Integer id);

    /**
     * 审批通过
     * @param id
     * @return
     */
    boolean updateApprovalStatusRejected(Integer id,String approvalOpinion);
}

