package com.example.busniess.service.imp;

import com.example.busniess.dao.BusinessCenterInformationDao;
import com.example.busniess.entity.BusinessCenterInformationEntity;
import com.example.busniess.service.BusinessCenterInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("businessCenterInformationService")
public class BusinessCenterInformationServiceImpl implements BusinessCenterInformationService {

    @Autowired
    BusinessCenterInformationDao businessCenterInformationDao;
    @Override
    public BusinessCenterInformationEntity selectOnByUname(String uname) {
        return businessCenterInformationDao.selectOnByUname(uname);
    }
}
