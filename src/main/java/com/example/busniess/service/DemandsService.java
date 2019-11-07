package com.example.busniess.service;


import com.example.busniess.entity.DemandsEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 企业需求表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-24 10:05:26
 */
public interface DemandsService {
    /**
     * 查询所有需求
     *
     * @return
     */
    public List<DemandsEntity> selectAll();

    /**
     * 查询所有未到期的需求(包含未审核,后端页面使用)
     *
     * @return
     */
    public List<DemandsEntity> selectAllShow();

    /**
     * 根据需求状态查询需求列表
     *
     * @param status
     * @return
     */
    public List<DemandsEntity> selectByStatus(int status);

    /**
     * 筛选查询(用户端)
     *
     * @param demandsEntity
     * @return
     */
    public List<DemandsEntity> search(DemandsEntity demandsEntity);

    /**
     * 筛选查询(管理端)
     *
     * @param demandsEntity
     * @return
     */
    public List<DemandsEntity> searchForManager(DemandsEntity demandsEntity);

    /**
     * 获取总条数(sql在mapper文件)
     *
     * @param demandsEntity
     * @return
     */
    public int getCount(DemandsEntity demandsEntity);

    /**
     * 分页查询(用户端)
     * @param demandsEntity
     * @param pageNum   当前页
     * @param pagesize  每页显示条数
     * @return
     */
    public PageInfo showByPage(DemandsEntity demandsEntity, int pageNum, int pagesize);

    /**
     * 最新需求
     * @return
     */
    public List<DemandsEntity> lastDemandsShow(Integer size);
    /**
     * 最新需求
     * @return
     */
    public List<String> hotDemandsIndustry(Integer size);
    /**
     * 分页查询(用户端)
     * @param demandsEntity
     * @param pageNum   当前页
     * @param pagesize  每页显示条数
     * @return
     */
    public PageInfo showByPageForManager(DemandsEntity demandsEntity, int pageNum, int pagesize);

    /**
     * 修改需求(sql在mapper文件)
     *
     * @param demandsEntity
     * @return
     */
    public boolean updateDemands(DemandsEntity demandsEntity);

    /**
     * 修改需求有效状态
     *
     * @param status
     * @param id
     * @return
     */
    public boolean updateDemandsStatus(Integer id,Integer status,String reason);

    /**
     * 修改需求审批状态
     * @param approvalStatus
     * @param id
     * @return
     */
    public boolean updateDemandsApprovalStatus(int approvalStatus,String approvalOpinion,int id);

    /**
     * 根据id查找需求(详情)
     *
     * @param id 需求id
     * @return
     */
    public DemandsEntity getByID(int id);

    /**
     * 根据id删除需求(逻辑删除)
     *
     * @param id 需求id
     * @return
     */
    public boolean deleteDemandsByID(int id);
    /**
     * 根据id删除需求(删除数据库)
     *
     * @param id 需求id
     * @return
     */
    boolean realDeleteDemandsByID(Integer id);

    /**
     * 新增需求
     *
     * @param demandsEntity
     * @return
     */
    public boolean insert(DemandsEntity demandsEntity);
    /**
     * 企业需求行业占比统计
     * @return
     */
    public List<DemandsEntity> demandsIndustryProp();
    /**
     * 企业需求增长趋势
     * @return
     */
    public List<DemandsEntity> demandsRiseTrend();
}

