package com.example.busniess.dao;

import com.example.busniess.entity.Occupancy;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OccupancyDao {

    /**
     * 查询所有的
     *
     * @return
     */
    @Select("SELECT * FROM occupancy o INNER JOIN imageaddress i ON o.id=i.oid  ORDER BY creattime DESC")
    public List<Occupancy> selectAllOccupancy();

    /**
     * 查看可发布的
     *
     * @return
     */
    @Select("SELECT * FROM occupancy WHERE `statue`=1 AND `kstatue`=1 ORDER BY creattime DESC")
    public List<Occupancy> selectOnShow();

    /**
     * 查询所有的状态审核的
     */

    @Select("SELECT * FROM occupancy WHERE statue=#{statue} ORDER BY creattime DESC")
    public List<Occupancy> selectOnStatueOccupancy(Integer statue);

    /**
     * 查询自己发布的
     *
     * @param userName
     * @return
     */
    @Select("SELECT * FROM occupancy WHERE username=#{userName} ORDER BY creattime DESC")
    public List<Occupancy> selectMyOccupancy(String userName);

    /**
     * 根据id 查询单个
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM occupancy o INNER JOIN imageaddress i ON o.id =i.oid AND o.id=#{id}")
    public Occupancy selectOneOccupancy(Integer id);

    /**
     * 根据条件搜索id
     *id
     * @param occupancy
     * @return
     */
    public List<Occupancy> searchOccupancy(Occupancy occupancy);

    /**
     * 修改入住信息
     */

    @Update("UPDATE `occupancy` SET `namefirm`=#{nameFirm}, `resulttechnolo`=#{resultTechnolo}, " +
            "`describe`=#{describe}, " +
            "`patennumber`=#{patentNumber}, `serialnumber`=#{serialNumber}, " +
            "`industry`=#{industry}, " +
            "`spindustries`=#{spIndustries}, `country`=#{country}, " +
            "`city`=#{city}, `district`=#{district}, " +
            "`uptime`=NOW() WHERE (`id`=#{id}) ")
    public Boolean upDataOccupancy(Occupancy occupancy);

    /**
     * 删除科技成果
     */

    @Delete("DELETE FROM occupancy WHERE id=#{id}")
    public Boolean delectOccupancy(Integer id);

    /**
     * id
     * 增加科技成果
     * 增加一个科技发布
     *
     * @param occupancy
     * @return
     */

    @Insert("INSERT INTO `occupancy` (`username`, `namefirm`," +
            "`resulttechnolo`, `describe`, " +
            "`patennumber`, `serialnumber`," +
            " `industry`," +
            "`spindustries`, `country`," +
            "`city`, `district`," +
            "`creattime`,`kstatue`," +
            "`statue`) VALUES(#{userName},#{nameFirm},#{resultTechnolo}," +
            "#{describe},#{patentNumber},#{serialNumber}," +
            "#{industry},#{spIndustries}," +
            "#{country},#{city},#{district},NOW(),1" +
            ",0)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public Boolean insertOccupancy(Occupancy occupancy);

    /**
     * 用户更改发布状态
     *
     * @param kstatue
     * @return
     */
    @Update("UPDATE `occupancy` SET `kstatue`=#{kstatue},stoptime=NOW() WHERE id=#{id}")
    public Boolean updateKstatue(@Param("kstatue") Integer kstatue, @Param("id") Integer id);

    /**
     * 更改审核状态
     *
     * @param statue
     * @return
     */
    @Update("UPDATE `occupancy` SET `statue`=#{staue},audittime=NOW() WHERE =#{id}")
    public Boolean updateStatue(@Param("statue") Integer statue, @Param("id") Integer id);

}
