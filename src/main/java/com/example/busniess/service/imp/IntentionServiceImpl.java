package com.example.busniess.service.imp;

import com.example.busniess.dao.IntentionDao;
import com.example.busniess.entity.IntentionEntity;
import com.example.busniess.service.IntentionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service("intentionService")
public class IntentionServiceImpl implements IntentionService {

    @Autowired
    IntentionDao intentionDao;

    /**
    * 分页展示
    * @param intentionEntity
    * @param pageNum
    * @param pageSize
    * @return
    */
    @Override
    public PageInfo showByPage(IntentionEntity intentionEntity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if(intentionEntity.getEndDate()!=null){
            Calendar c = Calendar.getInstance();
            c.setTime(intentionEntity.getEndDate());
            c.add(Calendar.DAY_OF_MONTH, 1);
            c.add(Calendar.SECOND,-1);
            intentionEntity.setEndDate(c.getTime());
        }
        List<IntentionEntity> list = intentionDao.search(intentionEntity);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
    * 根据id查找
    * @param id
    * @return
    */
    @Override
    public IntentionEntity selectByID(Integer id) {
        return intentionDao.selectByID(id);
    }

    /**
    * 新增
    * @param intentionEntity
    * @return
    */
    @Override
    public boolean addIntention(IntentionEntity intentionEntity) {
        return intentionDao.insert(intentionEntity);
    }

    /**
    * 逻辑删除
    * @param id
    * @return
    */
    @Override
    public boolean deleteByID(Integer id) {
        return intentionDao.deleteByID(id);
    }

    /**
    * 从数据库中删除
    * @param id
    * @return
    */
    @Override
    public boolean realDeleteByID(Integer id) {
        return intentionDao.realDeleteByID(id);
    }

    /**
    * 修改
    * @param intentionEntity
    * @return
    */
    @Override
    public boolean updateByID(IntentionEntity intentionEntity) {
        return intentionDao.updateByID(intentionEntity);
    }

    @Override
    public boolean deleteBatch(String ids) {
        ids = ids.replaceAll(",","','");
        return intentionDao.deleteBatch(ids);
    }

    @Override
    public int getCounts() {
        return intentionDao.getCounts();
    }

    @Override
    public boolean updateStatue(Integer id, Integer statue) {
        return intentionDao.updateStatue(id,statue);
    }

    @Override
    public boolean updateValid(Integer id, Integer isValid) {
        return intentionDao.updateValid(id,isValid);
    }

}
