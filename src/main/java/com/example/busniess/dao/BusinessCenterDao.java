package com.example.busniess.dao;

import com.example.busniess.entity.BusinessCenter;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BusinessCenterDao {
    /**
     * 创建认证信息
     *
     * @param businessCenter
     * @return
     */
    @Insert("INSERT INTO `businesscenter`" +
            " (`uname`, `firmname`, `industry`, " +
            "`typeEnterprise`,  " +
            " `country`, `city`, " +
            "`district`," +
            "`societycode`, " +
            "`legalperson`, `address`," +
            " `phonenumber`,`persionCode`," +
            " `statue`, `subtime`,`kstatue`" +
            ") VALUES (#{uName}, #{firmName}, #{industry}," +
            " #{typeEnterprise}," +
            " #{country}, #{city}, " +
            "#{district}," +
            "#{societyCode}, #{legalPerson}," +
            " #{address}, #{phoneNumber},#{persionCode}," +
            " 0, NOW(),1" +
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
     * 用户修改状态
     * @param statue
     * @param id
     * @return
     */
    @Update("UPDATE `businesscenter` SET `statue`=#{statue}, `audittime`=NOW() WHERE (`id`=#{id}) ")
    public Boolean upKstatue(@Param("statue") Integer statue, @Param("id") Integer id);

    /**
     * 修改审核信息
     */
    @Update("UPDATE `businesscenter` SET `firmname`=#{firmName}, `industry`=#{industry}, " +
            "`typeEnterprise`=#{typeEnterprise}," +
            "`country`=#{country}, `city`=#{city}, `district`=#{district}, " +
            "`societycode`=#{societyCode}, " +
            "`legalperson`=#{legalPerson}," +
            "`address`=#{address}, `phonenumber`=#{phoneNumber},`persionCode`=#{persionCode}," +
            "`uptime`=NOW(),`statue`=0 WHERE (`id`=#{id})")
    public Boolean updateBusinessCenter(BusinessCenter businessCenter);

    /**
     * 查看所有信息
     * 按条件查询所有信息
     * 行业，人数，企业名，审核状态
     */

    public List<BusinessCenter> selectAllBusinessCenter(BusinessCenter businessCenter);

    /**
     * 根据状态查询
     *
     * @param statue
     * @return
     */
    @Select("SELECT * FROM businesscenter WHERE statue=#{statue} AND uname NOT in (SELECT uname FROM businessInformation  WHERE statue!=3) ORDER BY subtime desc")
    public List<BusinessCenter> selectBusinessCenterByStatue(Integer statue);

    /**
     * 查看自己的
     */
    @Select("SELECT * FROM businesscenter WHERE uname=#{uName} ")
    public BusinessCenter selectOneBusinessCenter(String uName);

    /**
     * 根据id查询企业中心的具体情况
     */
    @Select("SELECT * FROM businesscenter WHERE id=#{id}")
    @Results({
            @Result(property = "occupancyList", column = "uname", many = @Many(select = "com.example.busniess.dao.OccupancyDao.selectByname")),
            @Result(property = "businessInformation", column = "uname", one = @One(select = "com.example.busniess.dao.BusinessInformationDao.selectBusinessInformation"))
    })
    public BusinessCenter selectBussinessByid(Integer id);

    /**
     * 根据名字查找企业名
     *
     * @param firmName
     * @return
     */
    @Select("SELECT firmName FROM businesscenter WHERE firmName LIKE '%${value}%'")
    public List<String> selectFirmName(String firmName);

    @Select("SELECT b.typeEnterprise,b.firmname,b.industry,bu.logo,b.country,b.city,b.district FROM `businesscenter` b LEFT JOIN businessInformation bu on b.uname = bu.uname where b.uname =#{uname} limit 1")
    BusinessCenter selectBussinessByUname(@Param("uname")String uname);

    List<BusinessCenter> search(BusinessCenter businessCenter);

}

