package com.example.busniess.dao;

import com.example.busniess.entity.BusinessCenter;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface BusinessCenterDao {
    /**
     * 创建认证信息
     * @param businessCenter
     * @return
     */
    @Insert("INSERT INTO `businesscenter` ( `uname`, `identification`, `societycode`, `codeStatue`, `firmName`, `industry`, `scale`, `country`, `city`, `district`, `legalperson`, `persioncode`, `accessorydesc`, `address`, `agentperson`, `appersioncode`, `subtime`) VALUES (#{uName}, #{identification}, #{societycode}, #{codeStatue}, #{firmName}, #{industry}, #{scale}, #{country}, #{district}, #{legalperson}, #{persioncode}, #{accessorydesc}, #{address}, #{agentperson}, #{appersioncode},NOW())")
    public  Boolean insertBusinessCenter(BusinessCenter businessCenter);

    /**
     * 删除认证信息
     * @param id
     * @return
     */
    public  Boolean delectBusinessCenter(Integer id);

    /**
     * 修改审核状态
     * @return
     */
    public  Boolean upStatue(Integer id);


    /**
     * 修改审核信息
     */

    public  Boolean updateBusinessCenter(BusinessCenter businessCenter);

/**
 * 查看所有信息
 */
public List<BusinessCenter> selectAllBusinessCenter();
/**
 * 查看单个
 */

public BusinessCenter selectOneBusinessCenter(Integer id);


}

