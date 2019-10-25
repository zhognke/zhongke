package com.example.busniess.service;

import com.example.busniess.entity.Financing;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FinancingService {
    /**
     * 新增融资
     */
    public Boolean insertFinacing(Financing financing);
    /**
     * 删除
     */
    public Boolean delectFinacing(Integer id);
    /**
     * 修改
     */
    public  boolean updateFinacing(Financing financing);
    /**
     * 查看自己的
     */
    public PageInfo selectMyFiancing(String uName, Integer pageNumber, Integer pageSize);
    /**
     * 查看所有的
     * 根据条件查询
     */
    public PageInfo SelectAllFinacing(Financing financing,Integer pageNum,Integer pagesize);
    /**
     * 修改审核状态
     */
    public Boolean updateFinacingStatue(Integer id,Integer statue);
}
