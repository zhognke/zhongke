package com.example.busniess.service;

import com.example.busniess.entity.BusinessCenterInformationEntity;

public interface BusinessCenterInformationService {
    /**
     * 根据用户名查询企业信息
     * @param uname 用户名
     * @return
     */
    public BusinessCenterInformationEntity selectOnByUname(String uname);

    BusinessCenterInformationEntity selectOneByCompanyName(String companyName);

}
