package com.example.busniess.service;

import com.github.pagehelper.PageInfo;
import com.example.busniess.entity.ProfessionalsEntity;

import java.util.List;


/**
 * 专家信息表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-24 11:42:14
 */
public interface ProfessionalsService {
    /**
    * 查询所有
    * @return
    */
    public List<ProfessionalsEntity> selectAll();

    /**
     * 根据条件查询
     * @param professionalsEntity
     * @return
     */
    public List<ProfessionalsEntity> search(ProfessionalsEntity professionalsEntity);

    /**
     * 分页查询显示
     * @return
     */
    public PageInfo showByPage(ProfessionalsEntity professionalsEntity, Integer pageNum, Integer pageSize);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public ProfessionalsEntity selectById(Integer id);

    /**
     * 新增
     * @param professionalsEntity
     * @return
     */
    public boolean add(ProfessionalsEntity professionalsEntity);

    /**
     * 根据id逻辑删除
     * @param id
     * @return
     */
    public boolean delectById(Integer id);

    /**
     * 根据id彻底删除
     * @param id
     * @return
     */
    public boolean realDeleteById(Integer id);

    /**
     * 更新
     * @param professionalsEntity
     * @return
     */
    public boolean update(ProfessionalsEntity professionalsEntity);


    /**
     * 更新有效状态
     * @param id
     * @param status
     * @return
     */
    public boolean updateStatus(Integer id, Integer status);


    /**
     * 更新审批状态
     * @param id
     * @param approvalStatus
     * @return
     */
    public boolean updateApprovalStatus(Integer id, Integer approvalStatus, String approvalOpinion);

}

