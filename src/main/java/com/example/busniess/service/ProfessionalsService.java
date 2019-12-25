package com.example.busniess.service;

import com.example.busniess.entity.ProfessionalsEntity;
import com.github.pagehelper.PageInfo;

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
    List<ProfessionalsEntity> selectAll();

    /**
     * 根据条件查询
     * @param professionalsEntity
     * @return
     */
    List<ProfessionalsEntity> search(ProfessionalsEntity professionalsEntity);

    /**
     * 分页查询显示
     * @return
     */
    PageInfo showByPage(ProfessionalsEntity professionalsEntity, Integer pageNum, Integer pageSize);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    ProfessionalsEntity selectById(Integer id);

    /**
     * 根据id查找_新
     * @param id
     * @return
     */
    ProfessionalsEntity selectById(Integer id,Integer size);

    /**
     * 新增
     * @param professionalsEntity
     * @return
     */
    boolean add(ProfessionalsEntity professionalsEntity);

    /**
     * 根据id逻辑删除
     * @param id
     * @return
     */
    boolean deleteById(Integer id);
    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(String ids);
    /**
     * 根据id彻底删除
     * @param id
     * @return
     */
    boolean realDeleteById(Integer id);

    /**
     * 更新
     * @param professionalsEntity
     * @return
     */
    boolean update(ProfessionalsEntity professionalsEntity);


    /**
     * 更新有效状态
     * @param id
     * @param status
     * @return
     */
    boolean updateStatus(Integer id, Integer status);


    /**
     * 更新审批状态
     * @param id
     * @param approvalStatus
     * @return
     */
    boolean updateApprovalStatus(Integer id, Integer approvalStatus, String approvalOpinion);

    boolean closeById(Integer id, String closeReason);

    boolean closeByIdForManager(Integer id, String closeReason);

    PageInfo showHot(Integer pageNum, Integer pageSize);

    boolean updateHot(Integer id, Integer isHot);

    boolean updateTop(Integer id, Integer isTop);
}

