package com.example.busniess.service.imp;

import com.example.busniess.dao.AdviceBoxDao;
import com.example.busniess.entity.AdviceBoxEntity;
import com.example.busniess.service.AdviceBoxService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("adviceBoxService")
public class AdviceBoxServiceImpl implements AdviceBoxService {

    @Autowired
    AdviceBoxDao adviceBoxDao;

    /**
     * 分页展示
     *
     * @param adviceBoxEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo showByPage(AdviceBoxEntity adviceBoxEntity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AdviceBoxEntity> list = adviceBoxDao.search(adviceBoxEntity);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public AdviceBoxEntity selectByID(Integer id) {
        return adviceBoxDao.selectByID(id);
    }

    /**
     * 新增
     *
     * @param adviceBoxEntity
     * @return
     */
    @Override
    public boolean addAdviceBox(AdviceBoxEntity adviceBoxEntity) {
        return adviceBoxDao.insert(adviceBoxEntity);
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteByID(Integer id) {
        return adviceBoxDao.deleteByID(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return boolean
     */
    @Override
    public boolean deleteBatch(String ids) {
        ids = ids.replaceAll(",", "','");
        return adviceBoxDao.deleteBatch(ids);
    }

    @Override
    public boolean assignment(AdviceBoxEntity adviceBoxEntity) {
        return adviceBoxDao.assignment(adviceBoxEntity);
    }

    @Override
    public boolean reply(AdviceBoxEntity adviceBoxEntity) {
        return adviceBoxDao.reply(adviceBoxEntity);
    }

    @Override
    public boolean isShow(AdviceBoxEntity adviceBoxEntity) {
        return adviceBoxDao.isShow(adviceBoxEntity);
    }

    @Override
    public Map<String, Integer> getCount() {
        Map<String,Integer> map = new HashMap<>();
        map.put("counts",adviceBoxDao.counts());
        map.put("countsIsShow",adviceBoxDao.countsIsShow());
        map.put("countsToday",adviceBoxDao.countsToday());
        map.put("countsTodayReply",adviceBoxDao.countsTodayReply());
        return map;
    }

}
