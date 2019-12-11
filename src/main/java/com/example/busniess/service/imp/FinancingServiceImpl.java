package com.example.busniess.service.imp;

import com.example.busniess.dao.FinancingDao;
import com.example.busniess.entity.FinancingEntity;
import com.example.busniess.service.FinancingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public String selectIndustry() {
        return financingDao.selectIndustry();
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
