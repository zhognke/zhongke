package com.example.busniess.service;

import com.example.busniess.entity.BusinessInformation;
import com.github.pagehelper.PageInfo;

import java.util.List;

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
    public PageInfo selectBusinessInformation(Integer pageNum,Integer pagesize);
}