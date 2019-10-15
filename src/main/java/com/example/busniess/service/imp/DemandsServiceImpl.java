package com.example.busniess.service.imp;

import com.example.busniess.dao.DemandsDao;
import com.example.busniess.entity.DemandsEntity;
import com.example.busniess.service.DemandsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service("demandsService")
public class DemandsServiceImpl implements DemandsService {

    @Autowired
    DemandsDao demandsDao;

    @Override
    public List<DemandsEntity> selectAll() {
        return demandsDao.selectAll();
    }

    @Override
    public List<DemandsEntity> selectAllShow() {
        return demandsDao.selectAllShow();
    }

    @Override
    public List<DemandsEntity> selectByStatus(int status) {
        return demandsDao.selectByStatus(status);
    }

    @Override
    public List<DemandsEntity> search(DemandsEntity demandsEntity) {
        demandsEntity.setStatus(0);
        demandsEntity.setApprovalStatus(1);
        return demandsDao.search(demandsEntity);
    }

    @Override
    public List<DemandsEntity> searchForManager(DemandsEntity demandsEntity) {
        return demandsDao.search(demandsEntity);
    }

    @Override
    public int getCount(DemandsEntity demandsEntity) {
        return demandsDao.getCount(demandsEntity);
    }

    /**
     * 前端展示页分页展示
     * @param demandsEntity
     * @param pageNum   当前页
     * @param pagesize  每页显示条数
     * @return
     */
    @Override
    public PageInfo showByPage(DemandsEntity demandsEntity, int pageNum, int pagesize) {
        demandsEntity.setStatus(0);
        demandsEntity.setApprovalStatus(1);
        PageHelper.startPage(pageNum, pagesize);
        List<DemandsEntity> list = demandsDao.search(demandsEntity);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo showByPageForManager(DemandsEntity demandsEntity, int pageNum, int pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List<DemandsEntity> list = demandsDao.search(demandsEntity);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    @Override
    public boolean updateDemands(DemandsEntity demandsEntity) {
        return demandsDao.updateDemands(demandsEntity);
    }

    @Override
    public boolean updateDemandsStatus(int status, int id) {
        return demandsDao.updateDemandsStatus(status,id);
    }


    @Override
    public boolean updateDemandsApprovalStatus(int approvalStatus,String approvalOpinion,int id) {
        return demandsDao.updateDemandsApprovalStatus(approvalStatus,approvalOpinion,id);
    }

    @Override
    public DemandsEntity getByID(int id) {
        return demandsDao.getByID(id);
    }

    @Override
    public boolean deleteDemandsByID(int id) {
        return demandsDao.deleteDemandsByID(id);
    }

    @Override
    public boolean insert(DemandsEntity demandsEntity) {
        return demandsDao.insert(demandsEntity);
    }

    @Override
    public List<DemandsEntity> demandsIndustryProp(){
        return demandsDao.demandsIndustryProp();
    }

    @Override
    public List<DemandsEntity> demandsRiseTrend(){
        return demandsDao.demandsRiseTrend();
    }
}
