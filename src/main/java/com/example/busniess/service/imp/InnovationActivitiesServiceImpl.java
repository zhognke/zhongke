package com.example.busniess.service.imp;

import com.example.busniess.dao.InnovationActivitiesDao;
import com.example.busniess.entity.InnovationActivitiesEntity;
import com.example.busniess.entity.TalentDemandEntity;
import com.example.busniess.service.InnovationActivitiesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("innovationActivitiesService" )
public class InnovationActivitiesServiceImpl implements InnovationActivitiesService {

    @Autowired
    InnovationActivitiesDao innovationActivitiesDao;

    /**
     * 分页展示
     * @param innovationActivitiesEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo showByPage(InnovationActivitiesEntity innovationActivitiesEntity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if(innovationActivitiesEntity.getStartTime()!=null&&innovationActivitiesEntity.getStartTime()==innovationActivitiesEntity.getEndTime()){
            Calendar c = Calendar.getInstance();
            c.setTime(innovationActivitiesEntity.getEndTime());
            c.add(Calendar.DAY_OF_MONTH, 1);
            c.add(Calendar.SECOND,-1);
            innovationActivitiesEntity.setEndTime(c.getTime());
        }
        if(innovationActivitiesEntity.getStatus()!=null){
            innovationActivitiesEntity.setStatusArr(innovationActivitiesEntity.getStatus().split(","));
        }
        List<InnovationActivitiesEntity> list = innovationActivitiesDao.search(innovationActivitiesEntity);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @Override
    public InnovationActivitiesEntity selectByID(Integer id) {
        return innovationActivitiesDao.selectByID(id);
    }

    /**
     * 新增
     * @param innovationActivitiesEntity
     * @return
     */
    @Override
    public boolean addInnovationActivities(InnovationActivitiesEntity innovationActivitiesEntity) {
        return innovationActivitiesDao.insert(innovationActivitiesEntity);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @Override
    public boolean deleteByID(Integer id) {
        return innovationActivitiesDao.deleteByID(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return boolean
     */
    @Override
    public boolean deleteBatch(String ids) {
        ids = ids.replaceAll(",", "','" );
        return innovationActivitiesDao.deleteBatch(ids);
    }

    /**
     * 从数据库中删除
     * @param id
     * @return
     */
    @Override
    public boolean realDeleteByID(Integer id) {
        return innovationActivitiesDao.realDeleteByID(id);
    }

    /**
     * 修改
     * @param innovationActivitiesEntity
     * @return
     */
    @Override
    public boolean updateByID(InnovationActivitiesEntity innovationActivitiesEntity) {
        return innovationActivitiesDao.updateByID(innovationActivitiesEntity);
    }

    @Override
    public boolean updateStatus(Integer id, Integer status) {
        return innovationActivitiesDao.updateStatus(id, status);
    }

    @Override
    public boolean enbaleRegistration(Integer id) {
        return innovationActivitiesDao.enbaleRegistration(id)>0;
    }

    @Override
    public Map<String, Object> activitiesRiseTrend(String dateType, Integer size) {
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
        List<InnovationActivitiesEntity> list = innovationActivitiesDao.activitiesRiseTrend(format, size);
        if (list.isEmpty() || "[]".equals(list.toString())) {
            return null;
        } else {
            Map<String, Object> map = new HashMap<>();
            Integer[] sdata = new Integer[list.size()];
            String[] xdata = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                xdata[i] = list.get(i).getActivitiesTopic();
                sdata[i] = list.get(i).getCounts();
            }
            map.put("sdata", sdata);
            map.put("xdata", xdata);
            return map;
        }
    }


}

