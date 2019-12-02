package com.example.busniess.service.imp;

import com.example.busniess.dao.BusinessCenterInformationDao;
import com.example.busniess.dao.OccupancyDao;
import com.example.busniess.dao.TalentDemandDao;
import com.example.busniess.entity.BusinessCenterInformationEntity;
import com.example.busniess.entity.TalentDemandEntity;
import com.example.busniess.service.TalentDemandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("talentDemandService")
public class TalentDemandServiceImpl implements TalentDemandService {
    @Autowired
    OccupancyDao occupancyDao;
    @Autowired
    TalentDemandDao talentDemandDao;
    @Autowired
    BusinessCenterInformationDao businessCenterInformationDao;

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<TalentDemandEntity> selectAll() {
        return talentDemandDao.selectAll();
    }

    /**
     * 根据条件搜索
     * @param talentDemandEntity
     * @return
     */
    @Override
    public List<TalentDemandEntity> search(TalentDemandEntity talentDemandEntity) {
        return talentDemandDao.search(talentDemandEntity);
    }

    /**
     * 分页展示
     * @param talentDemandEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo showByPage(TalentDemandEntity talentDemandEntity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (talentDemandEntity.getTechnologyScope() != null) {
            talentDemandEntity.setTechnologyScope(talentDemandEntity.getTechnologyScope().replaceAll(",", "','"));
        }
        if (talentDemandEntity.getDemandsType() != null) {
            talentDemandEntity.setDemandsType(talentDemandEntity.getDemandsType().replaceAll(",", "','"));
        }
        if (talentDemandEntity.getSalary() != null) {
            talentDemandEntity.setSalary(talentDemandEntity.getSalary().replaceAll(",", "','"));
        }
        if (talentDemandEntity.getDegree() != null) {
            talentDemandEntity.setDegree(talentDemandEntity.getDegree().replaceAll(",", "','"));
        }
        String industryExperience = talentDemandEntity.getIndustryExperience();
        if(industryExperience!=null&&industryExperience!=""){
            String srr[] = industryExperience.split("-");
            talentDemandEntity.setIndustryExperienceBegin(srr[0]);
            if(srr.length>1){
                talentDemandEntity.setIndustryExperienceEnd(srr[1]);
            }
        }
        List<TalentDemandEntity> list = talentDemandDao.search(talentDemandEntity);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @Override
    public TalentDemandEntity selectById(Integer id) {
        return talentDemandDao.selectById(id);
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @Override
    public TalentDemandEntity selectById(Integer id,Integer size) {
        TalentDemandEntity entity = talentDemandDao.selectById(id);
        if (entity != null && entity.getUserName() != null) {
            BusinessCenterInformationEntity businessCenterInformationEntity = businessCenterInformationDao.selectOnByUname(entity.getUserName());
            entity.setBusinessCenter(businessCenterInformationEntity);
            entity.setOccupancyList(occupancyDao.getOccupanyForProfessional(entity.getUserName(),size));
        }
        return entity;
    }

    /**
     * 新增
     * @param talentDemandEntity
     * @return
     */
    @Override
    public boolean add(TalentDemandEntity talentDemandEntity) {
        return talentDemandDao.add(talentDemandEntity);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @Override
    public boolean delectById(Integer id) {
        TalentDemandEntity talentDemandEntity = new TalentDemandEntity();
        talentDemandEntity.setId(id);
        talentDemandEntity.setStatus(4);
        return talentDemandDao.updateById(talentDemandEntity);
    }

    /**
     * 从数据库中删除
     * @param id
     * @return
     */
    @Override
    public boolean realDeleteById(Integer id) {
        return talentDemandDao.realDeleteById(id);
    }

    /**
     * 修改
     * @param talentDemandEntity
     * @return
     */
    @Override
    public boolean update(TalentDemandEntity talentDemandEntity) {
        talentDemandEntity.setApprovalStatus(0);
        return talentDemandDao.updateById(talentDemandEntity);
    }

    /**
     * 修改状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public boolean updateStatus(Integer id, Integer status, String userName) {
        TalentDemandEntity talentDemandEntity = new TalentDemandEntity();
        talentDemandEntity.setId(id);
        talentDemandEntity.setStatus(status);
        return talentDemandDao.updateById(talentDemandEntity);
    }

    /**
     * 修改审批状态
     * @param id
     * @param approvalStatus
     * @param approvalOpinion
     * @return
     */
    @Override
    public boolean updateApprovalStatus(Integer id, Integer approvalStatus, String approvalOpinion) {
        TalentDemandEntity talentDemandEntity = new TalentDemandEntity();
        talentDemandEntity.setId(id);
        talentDemandEntity.setApprovalStatus(approvalStatus);
        talentDemandEntity.setApprovalOpinion(approvalOpinion);
        return talentDemandDao.updateById(talentDemandEntity);
    }

    /**
     * 关闭需求
     * @param id
     * @param closeReason
     * @return
     */
    @Override
    public boolean closeDemands(Integer id, String closeReason) {
        Integer status = 3;
        return talentDemandDao.closeById(id, status, closeReason);
    }

    /**
     * 关闭需求-管理员
     * @param id
     * @param closeReason
     * @return
     */
    @Override
    public boolean closeDemandsForManager(Integer id, String closeReason) {
        Integer status = 2;
        return talentDemandDao.closeById(id, status, closeReason);
    }

    @Override
    public void updateArticleViewCount(Integer articleId, Integer viewCount) {
        talentDemandDao.updateArticleViewCount(articleId, viewCount);
    }

    @Override
    public boolean deleteBatch(String ids) {
        ids = ids.replaceAll(",","','");
        return talentDemandDao.deleteBatch(ids);
    }

}
