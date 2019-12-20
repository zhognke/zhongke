package com.example.busniess.service.imp;

import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.busniess.dao.IntentionRecordDao;
import com.example.busniess.entity.IntentionRecordEntity;
import com.example.busniess.service.IntentionRecordService;

import java.util.List;

@Service("intentionRecordService" )
public class IntentionRecordServiceImpl implements IntentionRecordService {

    @Autowired
        IntentionRecordDao intentionRecordDao;

    /**
     * 分页展示
     * @param intentionId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo showByPage(Integer intentionId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<IntentionRecordEntity> list = intentionRecordDao.searchByIntentionId(intentionId);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
    * 根据id查找
    * @param id
    * @return
    */
    @Override
    public IntentionRecordEntity selectByID(Integer id) {
        return intentionRecordDao.selectByID(id);
    }

    /**
     * 新增
     * @param intentionRecordEntity
     * @return
     */
    @Override
    public boolean addIntentionRecord(IntentionRecordEntity intentionRecordEntity) {
        return intentionRecordDao.insert(intentionRecordEntity);
    }

    /**
    * 逻辑删除
    * @param id
    * @return
    */
    @Override
    public boolean deleteByID(Integer id) {
        return intentionRecordDao.deleteByID(id);
    }

    /**
     * 修改
     * @param intentionRecordEntity
     * @return
     */
    @Override
    public boolean updateByID(IntentionRecordEntity intentionRecordEntity) {
        return intentionRecordDao.updateByID(intentionRecordEntity);
    }

}
