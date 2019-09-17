package com.example.busniess.dao;

import com.example.busniess.entity.BusinessCenter;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
            " `appersioncode`, `subtime`) VALUES (" +
            "#{uName}, #{identification}, #{societyCode}," +
            " #{codeStatue}, #{firmName}, #{industry}, " +
            "#{scale},#{country}, #{city}, " +
            "#{district}, #{legalPerson}, #{persionCode}, " +
            "#{accessoryDesc},#{address}, #{agentPerson}," +
            "#{apPersionCode},Now())")
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
     * @return
     */
    @Update("UPDATE `businesscenter` SET `state`=#{state}, `uptime`=NOW() WHERE (`id`=#{id}) ")
    public Boolean upStatue(Map map);


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
     * 查看单个
     */
    @Select("SELECT * FROM businesscenter WHERE uname=#{uName} ORDER BY subtime DESC")
    public BusinessCenter selectOneBusinessCenter(String uName);


}

