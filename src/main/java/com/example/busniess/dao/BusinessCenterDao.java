package com.example.busniess.dao;

import com.example.busniess.entity.BusinessCenter;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface BusinessCenterDao {
    /**
     * 创建认证信息
     *
     * @param businessCenter
     * @return
     */
    @Insert("INSERT INTO `businesscenter`" +
            " (`uname`, `firmname`, `industry`, " +
            "`typeEnterprise`, `scale`, `recorddate`, " +
            "`registeredcapital`, `country`, `city`, " +
            "`district`, `enterpriseprofile`," +
            " `logo`, `identification`, `societycode`, " +
            "`legalperson`, `persioncode`, `address`," +
            " `phonenumber`, `agentperson`, `appersioncode`," +
            " `statue`, `subtime`" +
            ") VALUES (#{uName}, #{firmName}, #{industry}," +
            " #{typeEnterprise}, #{scale},#{recordDate}," +
            " #{registeredCapital}, #{country}, #{city}, " +
            "#{district}, #{enterpriseProfile}, #{logo}, " +
            "#{identification}, #{societyCode}, #{legalPerson}, #{persionCode}," +
            " #{address}, #{phnoeNumber}, #{agentPerson}, #{apPersionCode}," +
            " 0, NOW()" +
            ")")
    public Boolean insertBusinessCenter(BusinessCenter businessCenter);

    /**
     * 删除认证信息
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM businesscenter WHERE id=#{id}")
    public Boolean delectBusinessCenter(Integer id);

    /**
     * 修改审核状态
     *
     * @param statue//修改审核状态 0审核中 1 审核通过 2审核驳回
     * @param id             //企业信息id
     * @return
     */
    @Update("UPDATE `businesscenter` SET `statue`=#{statue}, `audittime`=NOW() WHERE (`id`=#{id}) ")
    public Boolean upStatue(@Param("statue") Integer statue, @Param("id") Integer id);


    /**
     * 修改审核信息
     */
    @Update("UPDATE `businesscenter` SET `firmname`=#{firmName}, `industry`=#{industry}, " +
            "`typeEnterprise`=#{typeEnterprise}, `scale`=#{scale}, " +
            "`recordate`=#{recordDate}, `registeredcapital`=#{registeredCapital}, " +
            "`country`=#{country}, `city`=#{city}, `district`=#{district}, " +
            "`enterpriseprofile`=#{enterpriseProfile}, `logo`=#{logo}, " +
            "`identification`=#{identification}, `societycode`=#{societyCode}, " +
            "`legalperson`=#{legalPerson}, `persioncode`=#{persionCode}, " +
            "`address`=#{address}, `phonenumber`=#{phoneNumber}, `agentperson`=#{agentPerson}, " +
            "`appersioncode`=#{apPersionCode},`uptime`=NOW() WHERE (`id`=#{id})")
    public Boolean updateBusinessCenter(BusinessCenter businessCenter);

    /**
     * 查看所有信息
     */
    @Select("SELECT * FROM businesscenter  ORDER BY subtime DESC")
    public BusinessCenter selectAllBusinessCenter();

    /**
     * 根据状态查询
     *
     * @param statue
     * @return
     */
    @Select("SELECT * FROM businesscenter WHERE statue=#{statue} ORDER BY subtime DESC")
    public List<BusinessCenter> selectBusinessCenterByStatue(Integer statue);

    /**
     * 查看自己的
     */
    @Select("SELECT * FROM businesscenter WHERE uname=#{uName} ORDER BY subtime DESC")
    public List<BusinessCenter> selectOneBusinessCenter(String uName);

    /**
     * 根据名字查找企业名
     *
     * @param firmName
     * @return
     */
    @Select("SELECT firmName FROM businesscenter WHERE firmName LIKE '%${value}%'")
    public List<String> selectFirmName(String firmName);
}

