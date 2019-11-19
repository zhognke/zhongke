package com.example.busniess.service.imp;

import com.example.busniess.dao.InnovationActivitiesDao;
import com.example.busniess.entity.InnovationActivitiesEntity;
import com.example.busniess.service.InnovationActivitiesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}

