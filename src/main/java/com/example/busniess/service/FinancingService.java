package com.example.busniess.service;

import com.example.busniess.entity.FinancingEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

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
    public boolean updateFinacing(FinancingEntity financing);

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
    public Boolean updateFinacingStatue(Integer id, Integer statue, String reject);

    /**
     * 查看具体的
     *
     * @param id
     * @return
     */
    public FinancingEntity selectFinancingById(Integer id);


    /**
     * 返回热门行业
     */
    public List<String> selectIndustry();

    /**
     * 用户停止
     */

    public boolean stopFiancing(Integer id,Integer statue);

    Map<String,Object> getIndustryProp(Integer size);

    int getCounts();

    Map<String, Object> financingRiseTrend(String dateType, Integer size);
}
