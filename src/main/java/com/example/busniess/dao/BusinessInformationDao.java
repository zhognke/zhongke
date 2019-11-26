package com.example.busniess.dao;

import com.example.busniess.entity.BusinessInformation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BusinessInformationDao {
    /**
     * 增加
     * @param businessInformation
     * @return
     */
    @Insert("INSERT INTO `businessInformation` (`uName`, `registrationDate`, `registeredCapita`, `scale`, `enterprise`, `logo`, `statue`, `insertTime`) VALUES (#{uName}, #{registrationDate}, #{registeredCapita}, #{scale}, #{enterprise}, #{logo}, 1, NoW())")
    public Boolean insertBusinessInformation(BusinessInformation businessInformation);

    /**
     * 删除
     * @param id
     * @return
     */
    @Delete("DELETE FROM businessInformation WHERE id=#{id}")
    public boolean delectBusinessInformationById(Integer id);

    /**
     *跟新
     * @param businessInformation
     * @return
     */
    @Update("UPDATE `businessInformation` SET `registrationDate`=#{registrationDate}, `registeredCapita`=#{registeredCapita}, `scale`=#{scale}, `enterprise`=#{enterprise}, `logo`=#{logo}, `updateTime`=#{updateTime} WHERE (`id`=#{id})")
    public Boolean updateBusinessInformation(BusinessInformation businessInformation);

    /**
     * 查看自己的
     * @param uName
     * @return
     */
    @Select("SELECT * from businessInformation WHERE uname=#{uName}")
    public BusinessInformation selectBusinessInformation(String uName);

    /**
     * 查看所有的
     * @return
     */
    @Select("SELECT * from businessInformation")
    public List<BusinessInformation> selectAllBusinessInformation();


}