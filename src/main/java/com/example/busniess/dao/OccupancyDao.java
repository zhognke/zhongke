package com.example.busniess.dao;

import com.example.busniess.entity.Occupancy;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OccupancyDao {
/**
 * 查询
 */

@Select("")
    public List<Occupancy> selectOccupancy(String userName);

/**
 * 修改
 */
@Update("")
public Boolean upDataOccupancy(Occupancy occupancy);
/**
 * 删除科技成果
 */

@Delete("")
public Boolean delectOccupancy(Integer id);

    /**
     *增加科技成果
     * @param occupancy
     * @return
     */
    @Insert("")
public Boolean insertOccupancy(Occupancy occupancy);
}
