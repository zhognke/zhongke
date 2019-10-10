package com.example.busniess.service;

import com.example.busniess.entity.Occupancy;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OccupancyService {
    /**
     * 科技成果入住
     * @param occupancy
     * @return
     */
    Boolean addOccupancy(Occupancy occupancy);

    /**
     * 删除
     * @param id
     * @return
     */
    Boolean delectOccupancy(Integer id);

    /**
     * 更改发布状态
     * @param kStatue
     * @param id
     * @return
     */
    Boolean upDateKstatue(Integer kStatue,Integer id);

    /**
     * 更新发布状态

     * @param userName
     * @param roleId
     * @return
     */

    Boolean upDateStatue(String userName, Integer roleId);

    /**
     * 查看可发布的科技成果入住
     * @param pageNum
     * @param pagesize
     * @return
     */
    PageInfo selectOnShowOccupancy(Integer pageNum, Integer pagesize);

    /**
     * 查看自己发布的
     * @param userName
     * @param pageNum
     * @param pagesize
     * @return
     */
    PageInfo selectMyOccupancy(String userName,Integer pageNum, Integer pagesize);

    /**
     * 根据行业查询
     * @param occupancy
     * @return
     */
    PageInfo selectOccupancyByIndustry(Occupancy occupancy,Integer pageNum, Integer pagesize);

}
