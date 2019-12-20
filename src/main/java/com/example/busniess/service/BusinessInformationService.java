package com.example.busniess.service;

import com.example.busniess.entity.BusinessInformation;
import com.github.pagehelper.PageInfo;

public interface BusinessInformationService {
    /**
     * 增加
     */
    public boolean addBusinessInformation(BusinessInformation businessInformation);

    /**
     * 删除
     */
    public Boolean delectBusinessInformation(Integer id);

    /**
     * 修改
     */
    public boolean upDateBusinessInformation(BusinessInformation businessInformation);

    /**
     * 查看自己
     */
    public BusinessInformation selectBusinessInformation(String uName);

    /**
     * 查看所有的
     */
    public PageInfo selectBusinessInformation(Integer pageNum, Integer pagesize);

    /**
     * 根据id查询
     */
    public BusinessInformation selectBusinessInformationById(Integer id);

    /**
     * 根据状态删除
     */
    public boolean delectInformationByStatue(Integer id, Integer statue);
}
