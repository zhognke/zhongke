package com.example.busniess.service;

import com.github.pagehelper.PageInfo;
import com.example.busniess.entity.TalentDemandEntity;

import java.util.List;
import java.util.Map;


/**
 * 人才需求表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-23 09:29:27
 */
public interface TalentDemandService {
    /**
    * 查询所有
    * @return
    */
    public List<TalentDemandEntity> selectAll();

    /**
     * 根据条件查询
     * @param talentDemandEntity
     * @return
     */
    List<TalentDemandEntity> search(TalentDemandEntity talentDemandEntity);

    /**
     * 分页查询显示
     * @return
     */
    PageInfo showByPage(TalentDemandEntity talentDemandEntity, Integer pageNum, Integer pageSize);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    TalentDemandEntity selectById(Integer id);
    /**
     * 根据id查找_新
     * @param id
     * @param size
     * @return
     */
    TalentDemandEntity selectById(Integer id,Integer size);
    /**
     * 新增
     * @param talentDemandEntity
     * @return
     */
    boolean add(TalentDemandEntity talentDemandEntity);

    /**
     * 根据id逻辑删除
     * @param id
     * @return
     */
    boolean delectById(Integer id);

    /**
     * 根据id彻底删除
     * @param id
     * @return
     */
    boolean realDeleteById(Integer id);

    /**
     * 更新
     * @param talentDemandEntity
     * @return
     */
    boolean update(TalentDemandEntity talentDemandEntity);


    /**
     * 更新有效状态
     * @param id
     * @param status
     * @return
     */
    boolean updateStatus(Integer id, Integer status,String userName);


    /**
     * 更新审批状态
     * @param id
     * @param approvalStatus
     * @return
     */
    boolean updateApprovalStatus(Integer id, Integer approvalStatus, String approvalOpinion);

    boolean closeDemands(Integer id,String closeReason);

    boolean closeDemandsForManager(Integer id,String closeReason);

    void updateArticleViewCount(Integer articleId, Integer viewCount);

    boolean deleteBatch(String ids);

    Map<String, Object> demandsIndustryProp(Integer size);

    int getCounts();

    Map<String, Object> demandsRiseTrend(String type, Integer size);

    boolean updateHot(Integer id, Integer isHot);
}

