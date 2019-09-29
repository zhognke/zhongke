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
    @Insert("INSERT INTO `businesscenter` ( `uname`, `identification`, `societycode`, " +
            "`codeStatue`, `firmName`, `industry`, " +
            "`scale`, `country`, `city`, " +
            "`district`, `legalperson`, `persioncode`, " +
            "`accessorydesc`, `address`, `agentperson`," +
            " `appersioncode`,`statue` `subtime`) VALUES (" +
            "#{uName}, #{identification}, #{societyCode}," +
            " #{codeStatue}, #{firmName}, #{industry}, " +
            "#{scale},#{country}, #{city}, " +
            "#{district}, #{legalPerson}, #{persionCode}, " +
            "#{accessoryDesc},#{address}, #{agentPerson}," +
            "#{apPersionCode},0,Now())")
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
     * @param statue//修改审核状态 0审核中 1 审核通过 2审核驳回
     * @param id   //企业信息id
     * @return
     */
    @Update("UPDATE `businesscenter` SET `statue`=#{statue}, `uptime`=NOW() WHERE (`id`=#{id}) ")
    public Boolean upStatue(@Param("statue") Integer statue, @Param("id") Integer id);


    /**
     * 修改审核信息
     */

    public Boolean updateBusinessCenter(BusinessCenter businessCenter);

    /**
     * 查看所有信息
     */
    @Select("SELECT * FROM businesscenter  ORDER BY subtime DESC")
    public List<BusinessCenter> selectAllBusinessCenter();

    /**
     * 根据状态查询
     * @param statue
     * @return
     */
    @Select("SELECT * FROM businesscenter WHERE statue=#{statue} ORDER BY subtime DESC")
    public List<BusinessCenter> selectBusinessCenterByStatue(Integer statue);

    /**
     * 查看单个
     */
    @Select("SELECT * FROM businesscenter WHERE uname=#{uName} ORDER BY subtime DESC")
    public BusinessCenter selectOneBusinessCenter(String uName);


}

