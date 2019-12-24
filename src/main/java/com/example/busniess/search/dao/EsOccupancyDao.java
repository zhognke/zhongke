package com.example.busniess.search.dao;

import com.example.busniess.search.model.EsOccupancyModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EsOccupancyDao {

    @Select("SELECT id, username, companyName,resultTechnolo, resultTechnolo title, stage, advantages,advantages outline, industry, industryDetail, attribute, patenNname, patenNumber, price, registerNumber, `describe`,`describe` content,  appliedRange, transferType, linkman, phonenumber, province, city, district, stoptime, creattime createTime, uptime updateTime, audittime approvalTime, CASE WHEN kstatue = 1 THEN 0 ELSE 1 END status, statue approvalStatus, close_reason, reject, hot, negotiable FROM occupancy WHERE kstatue != 3")
    List<EsOccupancyModel> selectAll();

    @Select("SELECT id, username, companyName, resultTechnolo, resultTechnolo title, stage, advantages,advantages outline, industry, industryDetail, attribute, patenNname, patenNumber, price, registerNumber, `describe`,`describe` content, appliedRange, transferType, linkman, phonenumber, province, city, district, stoptime, creattime createTime, uptime updateTime, audittime approvalTime, CASE WHEN kstatue = 1 THEN 0 ELSE 1 END status, statue approvalStatus, close_reason, reject, hot, negotiable FROM occupancy WHERE kstatue != 3 and id = #{id}")
    EsOccupancyModel selectById(Integer id);
}
