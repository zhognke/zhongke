package com.example.busniess.service;

import com.example.busniess.entity.IndustrialDeclarationEntity;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

/**
 * 企业需求表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-28 10:31:37
 */
public interface IndustrialDeclarationService {
    /**
     * 查询所有
     * @return
     */
    public List<IndustrialDeclarationEntity> selectAll();

    /**
     * 根据条件查询
     * @param industrialDeclarationEntity
     * @return
     */
    public List<IndustrialDeclarationEntity> search(IndustrialDeclarationEntity industrialDeclarationEntity);

    /**
     * 分页查询显示
     * @return
     */
    public PageInfo showByPage(IndustrialDeclarationEntity industrialDeclarationEntity,Integer pageNum,Integer pageSize);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public IndustrialDeclarationEntity selectById(Integer id);

    /**
     * 新增
     * @param industrialDeclarationEntity
     * @return
     */
    public boolean add(IndustrialDeclarationEntity industrialDeclarationEntity);

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
     * @param industrialDeclarationEntity
     * @return
     */
    public boolean update(IndustrialDeclarationEntity industrialDeclarationEntity);


    /**
     * 更新有效状态
     * @param id
     * @param status
     * @return
     */
    public boolean updateStatus(Integer id,Integer status,String closeReason);


    /**
     * 更新审批状态
     * @param id
     * @param approvalStatus
     * @return
     */
    public boolean updateApprovalStatus(Integer id,Integer approvalStatus,String approvalOpinion);

    /**
     * 工业申报行业占比统计(饼图)
     * @return
     */
    public List<IndustrialDeclarationEntity> declartionsIndustryProp();

    /**
     * 工业申报增长趋势(折线图)
     * @return
     */
    public List<IndustrialDeclarationEntity> declartionsRiseTrend();

    /**
     * 获取公司名称列表
     * @return
     */
    List<IndustrialDeclarationEntity> getCompanyList();

    List<IndustrialDeclarationEntity> lastDeclarations(Integer size);
}

