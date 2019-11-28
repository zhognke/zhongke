package com.example.busniess.dao;

import com.example.busniess.entity.BusinessCenterInformationEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-27 09:26:07
 */
public interface BusinessCenterInformationDao {

    @Select("select id,uname,company_name,industry,type_enterprise,province,city,district,identification,society_code,scale,logo,enterprise_profile,registration_date,registered_capital,statue,insert_time,update_time" +
            " from business_center_information where statue =1 and uname = #{uname} limit 1")
    public BusinessCenterInformationEntity selectOnByUname(@Param("uname") String uname);
}