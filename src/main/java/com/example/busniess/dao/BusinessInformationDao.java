package com.example.busniess.dao;

import com.example.busniess.entity.BusinessInformation;
import org.apache.ibatis.annotations.Insert;

public interface BusinessInformationDao {
    /**
     * 增加
     * @param businessInformation
     * @return
     */
    @Insert("INSERT INTO `businessInformation` (`uname`, `registrationDate`, `registeredCapita`, `scale`, `enterprise`, `logo`, `statue`, `insertTime`) VALUES (#{uname}, #{registrationDate}, #{registeredCapita}, #{scale}, #{enterprise}, #{logo}, 1, NoW())")
    public Boolean insertBusinessInformation(BusinessInformation businessInformation);
    public boolean delectBusinessInformationById(Integer id);
    public Boolean updateBusinessInformation(BusinessInformation businessInformation);
    public BusinessInformation selectBusinessInformation(String uName);


}
