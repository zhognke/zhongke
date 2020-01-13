package com.example.busniess.service.imp;

import com.example.busniess.dao.BusinessCenterInformationDao;
import com.example.busniess.dao.DemandsDao;
import com.example.busniess.dao.PersonDao;
import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.BusinessCenterInformationEntity;
import com.example.busniess.entity.DemandsEntity;
import com.example.busniess.entity.Person;
import com.example.busniess.entity.User;
import com.example.busniess.service.DemandsService;
import com.example.busniess.utiles.EchartsEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("demandsService")
public class DemandsServiceImpl implements DemandsService {

    @Autowired
    DemandsDao demandsDao;
    @Autowired
    UserDao userDao;
    @Autowired
    BusinessCenterInformationDao businessCenterInformationDao;
    @Autowired
    PersonDao personDao;

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
        if(StringUtils.isNotEmpty(demandsEntity.getDemandIndustry())){
            demandsEntity.setDemandIndustry(demandsEntity.getDemandIndustry().replaceAll(",","','"));
        }
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
        if(entity!=null){
            String userName = entity.getUserName();
            User user = userDao.selectUserByName(userName);
            entity.setIsPerson(user.getPersion());
            if(user.getPersion()==2){
                BusinessCenterInformationEntity businessCenterInformationEntity = businessCenterInformationDao.selectOnByUname(entity.getUserName());
                if (businessCenterInformationEntity!=null){
                    entity.setLogo(businessCenterInformationEntity.getLogo());
                    entity.setTypeEnterprise(businessCenterInformationEntity.getTypeEnterprise());
                }
            }else{
                Person person = personDao.selectPerson(userName);
                if(person!=null){
                    entity.setLogo(person.getAddress());
                }
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
    public Map<String, Object> demandsIndustryProp(Integer size){
        List<DemandsEntity> list = demandsDao.demandsIndustryProp(size);
        if (list.isEmpty() || "[]".equals(list.toString())) {
            return null;
        } else {
            Map<String, Object> map = new HashMap<>();
            List<EchartsEntity> sdata = new ArrayList<>();
            String[] ldata = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                EchartsEntity echartsEntity = new EchartsEntity();
                ldata[i] = list.get(i).getDemandIndustry();
                echartsEntity.setName(list.get(i).getDemandIndustry());
                echartsEntity.setValue(Double.parseDouble(String.valueOf(list.get(i).getCounts())));
                sdata.add(echartsEntity);
            }
            map.put("sdata", sdata);
            map.put("ldata", ldata);
            return map;
        }
    }

    @Override
    public Map<String, Object> demandsRiseTrend(String type, Integer size) {
        String format;
        if ("month".equalsIgnoreCase(type)) {
            format = "%Y/%m";
            size = size == null ? 12 : size;
        } else if ("day".equalsIgnoreCase(type)) {
            format = "%Y/%m/%d";
            size = size == null ? 31 : size;
        } else {
            format = "%Y";
            size = size == null ? 10 : size;
        }
        List<DemandsEntity> list = demandsDao.demandsRiseTrendByDate(format, size);
        if (list.isEmpty() || "[]".equals(list.toString())) {
            return null;
        } else {
            Map<String, Object> map = new HashMap<>();
            Integer[] sdata = new Integer[list.size()];
            String[] xdata = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                xdata[i] = list.get(i).getCompanyName();
                sdata[i] = list.get(i).getCounts();
            }
            map.put("sdata", sdata);
            map.put("xdata", xdata);
            return map;
        }
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
