package com.example.busniess.service.imp;

import com.example.busniess.dao.BusinessCenterDao;
import com.example.busniess.dao.DemandsDao;
import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.DemandsEntity;
import com.example.busniess.service.DemandsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


@Service("demandsService")
public class DemandsServiceImpl implements DemandsService {

    @Autowired
    DemandsDao demandsDao;

    @Autowired
    BusinessCenterDao businessCenterDao;

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
    public List<DemandsEntity> searchNew(DemandsEntity demandsEntity) {
        demandsEntity.setStatus(0);
        demandsEntity.setApprovalStatus(1);
        return demandsDao.searchNew(demandsEntity);
    }

    @Override
    public List<DemandsEntity> searchForManager(DemandsEntity demandsEntity) {
        return demandsDao.searchNew(demandsEntity);
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
        if(demandsEntity.getDemandType()!=null){
            demandsEntity.setDemandType(demandsEntity.getDemandType().replaceAll(",","','"));
        }
        if(demandsEntity.getCooperationType()!=null){
            demandsEntity.setCooperationType(demandsEntity.getCooperationType().replaceAll(",","','"));
        }
        if(demandsEntity.getCooperationIntention()!=null){
            demandsEntity.setCooperationIntention(demandsEntity.getCooperationIntention().replaceAll(",","','"));
        }
        PageHelper.startPage(pageNum, pagesize);
        List<DemandsEntity> list = demandsDao.searchNew(demandsEntity);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<DemandsEntity> lastDemandsShow(Integer size) {
        return demandsDao.lastDemandsShow(size);
    }

    @Override
    public List<String> hotDemandsIndustry(Integer size) {
        return demandsDao.hotDemandsIndustry(size);
    }

    @Override
    public PageInfo showByPageForManager(DemandsEntity demandsEntity, int pageNum, int pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List<DemandsEntity> list = demandsDao.searchNew(demandsEntity);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    @Override
    public boolean updateDemands(DemandsEntity demandsEntity) {
        demandsEntity.setStatus(0);
        demandsEntity.setApprovalStatus(0);
        return demandsDao.updateDemands(demandsEntity);
    }

    @Override
    public boolean updateDemandsStatus(Integer id,Integer status,String reason) {
        return demandsDao.updateDemandsStatus(id,status,reason);
    }


    @Override
    public boolean updateDemandsApprovalStatus(int approvalStatus,String approvalOpinion,int id) {
        return demandsDao.updateDemandsApprovalStatus(approvalStatus,approvalOpinion,id);
    }

    @Override
    public DemandsEntity getByIDOld(int id) {
        return demandsDao.getByID(id);
    }

    @Override
    public DemandsEntity getByID(int id) {
        DemandsEntity entity = demandsDao.getByID(id);
        if(entity!=null&&entity.getUserName()!=null){
            BusinessCenter businessCenter = businessCenterDao.selectBussinessByUname(entity.getUserName());
            if (businessCenter!=null){
                entity.setLogo(businessCenter.getLogo());
                entity.setTypeEnterprise(businessCenter.getTypeEnterprise());
            }
        }
        return entity;
    }

    @Override
    public boolean deleteDemandsByID(int id) {
        return demandsDao.deleteById(id);
    }

    @Override
    public boolean realDeleteDemandsByID(Integer id) {
        return demandsDao.deleteDemandsByID(id);
    }

    @Override
    public boolean insert(DemandsEntity demandsEntity) {
        demandsEntity.setStatus(0);
        demandsEntity.setApprovalStatus(0);
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

    @Override
    public boolean deleteBatch(String ids) {
        ids = ids.replaceAll(",","','");
        return demandsDao.deleteBatch(ids);
    }
}
