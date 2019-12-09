package com.example.busniess.service.imp;

import com.example.busniess.dao.BusinessInformationDao;
import com.example.busniess.entity.BusinessInformation;
import com.example.busniess.service.BusinessInformationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessInformationServiceImpl implements BusinessInformationService {
    @Autowired
    BusinessInformationDao businessInformationDao;

    /**
     * 添加信息
     * @param businessInformation
     * @return
     */
    @Override
    public boolean addBusinessInformation(BusinessInformation businessInformation) {

        return businessInformationDao.insertBusinessInformation(businessInformation);
    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @Override
    public Boolean delectBusinessInformation(Integer id) {

        return businessInformationDao.delectBusinessInformationById(id);
    }

    /**
     * 跟新信息
     * @param businessInformation
     * @return
     */
    @Override
    public boolean upDateBusinessInformation(BusinessInformation businessInformation) {
        return businessInformationDao.updateBusinessInformation(businessInformation);
    }

    /**
     * 查看自己的
     * @param uName
     * @return
     */
    @Override
    public BusinessInformation selectBusinessInformation(String uName) {
        return businessInformationDao.selectBusinessInformation(uName);
    }

    /**
     * 查看所有的
     * @return
     */
    @Override
    public PageInfo selectBusinessInformation(Integer pageNum,Integer pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List o=   businessInformationDao.selectAllBusinessInformation();
        PageInfo pageInfo = new PageInfo(o);
        return pageInfo;
    }

    /**
     * 根据id号查看企业补充信息
     * @param id
     * @return
     */
    @Override
    public BusinessInformation selectBusinessInformationById(Integer id) {
        return  businessInformationDao.selectBusinessInformationById(id);
    }
}
