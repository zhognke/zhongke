package com.example.busniess.service;

import com.example.busniess.entity.FinancingEntity;
import com.github.pagehelper.PageInfo;

public interface FinancingService {
    /**
     * 新增融资
     */
    public Boolean insertFinacing(FinancingEntity financing);
    /**
     * 删除
     */
    public Boolean delectFinacing(Integer id);
    /**
     * 修改
     */
    public  boolean updateFinacing(FinancingEntity financing);
    /**
     * 查看自己的
     */
    public PageInfo selectMyFiancing(String uName, Integer pageNumber, Integer pageSize);
    /**
     * 查看所有的
     * 根据条件查询
     */
    public PageInfo SelectAllFinacing(FinancingEntity financing, Integer pageNum, Integer pagesize);
    /**
     * 修改审核状态
     */
    public Boolean updateFinacingStatue(Integer id,Integer statue);
}
