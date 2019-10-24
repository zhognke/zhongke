package com.example.busniess.dao;

import com.example.busniess.entity.Occupancy;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface OccupancyDao {

    /**
     * 查询所有的
     *
     * @return
     */
    @Select("SELECT * FROM occupancy ")
//    @Results({
//            @Result(property = "imgAddress", column = "oId", many = @Many(select = "com.example.busniess.dao.selectimgAddress"))
//    })
    public List<Occupancy> selectAllOccupancy();

    /**
     * 查看具体的
     * @param id
     * @return
     */
    @Select("SELECT * FROM occupancy  WHERE id=#{id} ")
    @Results({
         @Result(property = "imgAddress", column = "id", many = @Many(select = "com.example.busniess.dao.ImageAddressDao.selectimgAddress"))
    })
    public Occupancy selectOneById(Integer id);

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
     * @return imgAddress
     */
    @Select("SELECT * FROM occupancy WHERE id=#{id}")
    @Results({
            @Result(property = "imgAddress", column = "id", one = @One(select = "com.example.busniess.dao.ImageAddressDao.selectimgAddress"))
    })
    public Occupancy selectOneOccupancy(Integer id);

    /**
     * 根据条件搜索id
     * id
     *
     * @param occupancy
     * @return
     */
    public List<Occupancy> searchOccupancy(Occupancy occupancy);

    /**
     * 修改科技入住信息
     */

    @Update("UPDATE `occupancy` SET `resultTechnolo`=#{resultTechnolo}, `stage`=#{stage}, " +
            "`advantages`=#{advantages}, `industry`=#{industry}, `attribute`=#{attribute}," +
            " `patenNname`=#{patenNname}, `patenNumber`=#{patenNumber}, `price`=#{price}," +
            "`registerNumber`=#{registerNumber}, `describe`=#{describe}, `appliedRange`=#{appliedRange}," +
            "`linkman`=#{linkMan}, `phonenumber`=#{phoneNumber}, " +
            "`uptime`=NOW() WHERE (`id`=#{id})")
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
     * 0 1 2
     *
     * @param occupancy
     * @return
     */
    @Insert("INSERT INTO `occupancy` (`username`, `resultTechnolo`, `stage`, " +
            "`advantages`, `industry`, `attribute`, `patenNname`, `patenNumber`," +
            " `price`, `describe`, `appliedRange`, `linkman`, `phonenumber`," +
            "`creattime`,`kstatue`, " +
            "`statue`) VALUES (#{userName}, #{resultTechnolo}, #{stage}, #{advantages}, " +
            "#{industry}, #{attribute}, #{patenNname}, #{patenNumber}, " +
            "#{price}, #{describe}, #{appliedRange}, #{linkMan}, #{phoneNumber}," +
            "NOW(), '1', '0')")
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
    @Update("UPDATE `occupancy` SET `statue`=#{statue},`audittime`=NOW() WHERE id=#{id}")
    public Boolean updateStatue(@Param("statue") Integer statue, @Param("id") Integer id);

    /**
     * 根据行业查询
     *
     * @param occupancy
     * @return
     */
    public List<Occupancy> selectOccupancyByIndustry(Occupancy occupancy);

    /**
     * 查询饼状图
     *
     * @returnAND kstatue=1
     */

    @Select("SELECT COUNT(industry) , industry FROM occupancy WHERE " +
            "statue=1 GROUP BY industry ")
    public List<Map> selectPieImg();

    /**
     * 查询折线图
     *
     * @return
     */
    @Select("SELECT  COUNT(industry),DATE_FORMAT(creattime,'%Y年%m月') FROM occupancy WHERE  statue=1 GROUP BY DATE_FORMAT(creattime,'%Y年%m月')")
    public List<Map> selectBrokenImg();

    /**
     * 统计总数
     * @param
     * @return
     */
    @Select("SELECT COUNT(industry) FROM occupancy WHERE statue=1")
    public Integer countIndustry();


}
