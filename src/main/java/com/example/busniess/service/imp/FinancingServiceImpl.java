package com.example.busniess.service.imp;

import com.example.busniess.dao.FinancingDao;
import com.example.busniess.entity.DemandsEntity;
import com.example.busniess.entity.FinancingEntity;
import com.example.busniess.entity.TalentDemandEntity;
import com.example.busniess.service.FinancingService;
import com.example.busniess.utiles.EchartsEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinancingServiceImpl implements FinancingService {
    @Autowired
    FinancingDao financingDao;


    /**
     * 增加
     * @param financing
     * @return
     */
    @Override
    public Boolean insertFinacing(FinancingEntity financing) {

        return  financingDao.insertFinancing(financing);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Boolean delectFinacing(Integer id) {

        return  financingDao.delectFinancing(id);
    }

    /**
     * 修改
     * @param
     * @return
     */
    @Override
    public boolean updateFinacing(FinancingEntity financing) {

        return financingDao.updateFinancing(financing);
    }

    /**
     * 查看具体的
     * @param id
     * @return
     */
    @Override
    public FinancingEntity selectFinancingById(Integer id) {

        return financingDao.seleOneFinancing(id);
    }

    /**
     * 热门行业
     * @return
     */
    @Override
    public List<String>  selectIndustry() {
        return financingDao.selectIndustry();
    }

    @Override
    public Map<String, Object> getIndustryProp(Integer size) {
        List<FinancingEntity> list = financingDao.getIndustryProp(size);
        Map<String, Object> map = new HashMap<>();
        if (list.isEmpty() || "[]".equals(list.toString())) {
            return null;
        } else {
            List<EchartsEntity> sdata = new ArrayList<>();
            String[] ldata = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                EchartsEntity echartsEntity = new EchartsEntity();
                ldata[i] = list.get(i).getIndustry();
                echartsEntity.setName(list.get(i).getIndustry());
                echartsEntity.setValue(list.get(i).getPeriod().doubleValue());
                sdata.add(echartsEntity);
            }
            map.put("sdata", sdata);
            map.put("ldata", ldata);
        }
        return map;
    }

    @Override
    public int getCounts() {
        return financingDao.getCounts();
    }

    @Override
    public Map<String, Object> financingRiseTrend(String dateType, Integer size) {
        String format;
        if ("month".equalsIgnoreCase(dateType)) {
            format = "%Y/%m";
            size = size == null ? 12 : size;
        } else if ("day".equalsIgnoreCase(dateType)) {
            format = "%Y/%m/%d";
            size = size == null ? 31 : size;
        } else {
            format = "%Y";
            size = size == null ? 10 : size;
        }
        List<FinancingEntity> list = financingDao.financingRiseTrend(format, size);
        if (list.isEmpty() || "[]".equals(list.toString())) {
            return null;
        } else {
            Map<String, Object> map = new HashMap<>();
            Integer[] sdata = new Integer[list.size()];
            String[] xdata = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                xdata[i] = list.get(i).getProjectName();
                sdata[i] = list.get(i).getAgeLimit();
            }
            map.put("sdata", sdata);
            map.put("xdata", xdata);
            return map;
        }
    }

    /**
     * 查询自己的
     * @param uName
     * @return
     */
    @Override
    public PageInfo selectMyFiancing(String uName,Integer pageNum,Integer pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List a=financingDao.selectMyFinancing(uName);
        PageInfo pageInfo=new PageInfo(a);
        return   pageInfo;
    }

    /**
     * 修改审核状态
     * @param id
     * @param statue
     * @return
     */
    @Override
    public Boolean updateFinacingStatue(Integer id, Integer statue,String reject) {

        return  financingDao.upFinacingStatue(id,statue,reject);
    }

    /**
     * 按条件查询
     * @param financing
     * @param pageNum
     * @param pagesize
     * @return
     */
    @Override
    public PageInfo SelectAllFinacing(FinancingEntity financing, Integer pageNum, Integer pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List a=financingDao.selectAllFinancing(financing);
        PageInfo pageInfo=new PageInfo(a);
        return pageInfo;
    }
}
