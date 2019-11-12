package com.example.busniess.service.imp;

import com.example.busniess.dao.IndustrialDeclarationDao;
import com.example.busniess.dao.IndustrialDeclarationDetailDao;
import com.example.busniess.entity.IndustrialDeclarationDetailEntity;
import com.example.busniess.entity.IndustrialDeclarationEntity;
import com.example.busniess.service.IndustrialDeclarationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service("industrialDeclarationService")
public class IndustrialDeclarationServiceImpl implements IndustrialDeclarationService {

    @Autowired
    IndustrialDeclarationDao industrialDeclarationDao;  //工业投资项目申报

    @Autowired
    IndustrialDeclarationDetailDao industrialDeclarationDetailDao;  //工业投资项目申报明细表

    @Override
    public List<IndustrialDeclarationEntity> selectAll() {
        return industrialDeclarationDao.selectAll();
    }

    @Override
    public List<IndustrialDeclarationEntity> search(IndustrialDeclarationEntity industrialDeclarationEntity) {
        return industrialDeclarationDao.search(industrialDeclarationEntity);
    }

    @Override
    public PageInfo showByPage(IndustrialDeclarationEntity industrialDeclarationEntity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<IndustrialDeclarationEntity> list = industrialDeclarationDao.search(industrialDeclarationEntity);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public IndustrialDeclarationEntity selectById(Integer id) {
        IndustrialDeclarationEntity industrialDeclaration = industrialDeclarationDao.selectById(id);
        if(industrialDeclaration!=null){
            IndustrialDeclarationDetailEntity detailEntity = industrialDeclarationDetailDao.getByDeclarationId(id);
            industrialDeclaration.setDetailEntity(detailEntity);
            return industrialDeclaration;
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public boolean add(IndustrialDeclarationEntity industrialDeclarationEntity) {
        industrialDeclarationEntity.setStatus(0);
        industrialDeclarationEntity.setApprovalStatus(0);
        industrialDeclarationDao.add(industrialDeclarationEntity);
        Integer id = industrialDeclarationEntity.getId();
        IndustrialDeclarationDetailEntity detailEntity = industrialDeclarationEntity.getDetailEntity();
        detailEntity.setDeclarationId(id);
        return industrialDeclarationDetailDao.add(detailEntity);
    }

    @Override
    public boolean delectById(Integer id) {
        return industrialDeclarationDao.deleteById(id);
    }

    @Override
    @Transactional
    public boolean realDeleteById(Integer id) {
        IndustrialDeclarationEntity industrialDeclaration = industrialDeclarationDao.selectById(id);
        if(industrialDeclaration!=null){
            industrialDeclarationDao.realDelectById(id);
            return industrialDeclarationDetailDao.realDeleteByDeclarationId(id);
        }else{
            return false;
        }
    }

    @Override
    @Transactional
    public boolean update(IndustrialDeclarationEntity industrialDeclarationEntity) {
        industrialDeclarationEntity.setStatus(0);
        industrialDeclarationEntity.setApprovalStatus(0);
        IndustrialDeclarationDetailEntity declarationDetailEntity = industrialDeclarationEntity.getDetailEntity();
        declarationDetailEntity.setDeclarationId(industrialDeclarationEntity.getId());
        industrialDeclarationDao.update(industrialDeclarationEntity);
        boolean flag = industrialDeclarationDetailDao.update(declarationDetailEntity);
        return flag;
    }

    @Override
    public boolean updateStatus(Integer id,Integer status,String closeReason) {
        return industrialDeclarationDao.updateStatus(id,status,closeReason);
    }

    @Override
    public boolean updateApprovalStatus(Integer id,Integer approvalStatus,String approvalOpinion) {
        IndustrialDeclarationEntity industrialDeclarationEntity = new IndustrialDeclarationEntity();
        industrialDeclarationEntity.setId(id);
        industrialDeclarationEntity.setApprovalStatus(approvalStatus);
        industrialDeclarationEntity.setApprovalOpinion(approvalOpinion);
        return industrialDeclarationDao.update(industrialDeclarationEntity);
    }

    /**
     * 工业申报行业占比统计(饼图)
     * @return
     */
    @Override
    public List<IndustrialDeclarationEntity> declartionsIndustryProp() {
        return industrialDeclarationDao.declartionsIndustryProp();
    }

    /**
     * 工业申报增长趋势(折线图)
     * @return
     */
    @Override
    public List<IndustrialDeclarationEntity> declartionsRiseTrend() {
        return industrialDeclarationDao.declartionsRiseTrend();
    }

    @Override
    public List<IndustrialDeclarationEntity> getCompanyList() {
        return industrialDeclarationDao.getCompanyList();
    }

    @Override
    public List<IndustrialDeclarationEntity> lastDeclarations(Integer size) {
        return industrialDeclarationDao.lastDeclarations(size);
    }


}
