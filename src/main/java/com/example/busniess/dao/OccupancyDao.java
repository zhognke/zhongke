package com.example.busniess.dao;

import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.BusinessInformation;
import com.example.busniess.entity.Occupancy;
import org.apache.ibatis.annotations.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

public interface OccupancyDao {

//    /**
//     * 查询所有的
//     *
//     * @return
//     */
//    @Select("SELECT * FROM occupancy ")
////    @Results({
////            @Result(property = "imgAddress", column = "oId", many = @Many(select = "com.example.busniess.dao.selectimgAddress"))
////    })
//    public List<Occupancy> selectAllOccupancy();

    /**
     * 查看具体的
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM occupancy  WHERE id=#{id} ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userName", column = "userName"),
            @Result(property = "imgAddress", column = "id", many = @Many(select = "com.example.busniess.dao.ImageAddressDao.selectimgAddress")),
            @Result(property = "businessInformation", column = "username", one = @One(select = "com.example.busniess.dao.BusinessInformationDao.selectBusinessInformation")),
            @Result(property = "businessCenter", column = "username", one = @One(select = "com.example.busniess.dao.BusinessCenterDao.selectOneBusinessCenter")),
            @Result(property = "person", column = "username", one = @One(select = "com.example.busniess.dao.PersonDao.selectPerson"))
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
     * 查看自己的
     * 可以按条件检索
     * @param occupancy
     * @return
     */
    public List<Occupancy> selectMyOccupancy(Occupancy occupancy);

    /**
     * 名字
     *
     * @param userName
     * @return
     */
    @Select("SELECT * FROM occupancy WHERE username=#{userName} AND `statue`=1 AND `kstatue`=1   ORDER BY creattime DESC")
    @Results({
            @Result(property = "id", column = "id" ),
            @Result(property = "imgAddress", column = "id", one = @One(select = "com.example.busniess.dao.ImageAddressDao.selectimgAddress")),
    })
            public List<Occupancy>selectByname(String userName);


    /**
     * 根据id 查询单个
     *
     * @param id
     * @return imgAddress
     */

    @Select("SELECT * FROM occupancy WHERE id=#{id}")
    @Results({
            @Result(property = "imgAddress", column = "id", one = @One(select = "com.example.busniess.dao.ImageAddressDao.selectimgAddress")),
            @Result(property = "businessInformation", column = "username", one = @One(select = "com.example.busniess.dao.BusinessInformationDao.selectBusinessInformation")),
            @Result(property = "businessCenter", column = "username", one = @One(select = "com.example.busniess.dao.BusinessCenterDao.selectOneBusinessCenter"))
    })
    public Occupancy selectOneOccupancy(Integer id);



    /**
     * 修改科技入住信息
     */

    @Update("UPDATE `occupancy` SET `resultTechnolo`=#{resultTechnolo}, `stage`=#{stage}, " +
            "`advantages`=#{advantages}, `industry`=#{industry},`industryDetail`=#{industryDetail},`attribute`=#{attribute}," +
            " `patenNname`=#{patenNname}, `patenNumber`=#{patenNumber}, `price`=#{price}," +
            "`registerNumber`=#{registerNumber}, `describe`=#{describe}, `appliedRange`=#{appliedRange}," +
            "`transferType`=#{transferType},`linkman`=#{linkMan}, `phonenumber`=#{phoneNumber},`province`=#{province}," +
            "`city`=#{city},`district`=#{district},`uptime`=NOW(),`negotiable`=#{negotiable} WHERE (`id`=#{id})")
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
    @Insert("INSERT INTO `occupancy` (`username`, `companyName`,`resultTechnolo`, `stage`, " +
            "`advantages`, `industry`, `industryDetail`,`attribute`, `patenNname`, `patenNumber`," +
            " `price`, `registerNumber`,`describe`, `appliedRange`,`transferType`,`linkman`, `phonenumber`,`province`,`city`,`district`,`stoptime`," +
            "`creattime`,`kstatue`, `statue`,`negotiable`) " +
            "VALUES (#{userName},#{companyName} ,#{resultTechnolo}, #{stage}, #{advantages}, #{industry}, #{industryDetail},#{attribute}, #{patenNname}, #{patenNumber}, " +
            "#{price},#{registerNumber},#{describe}, #{appliedRange},#{transferType},#{linkMan}, #{phoneNumber},#{province},#{city},#{district},#{stopTime}," +
            "NOW(), '1', '0',#{negotiable})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public Boolean insertOccupancy(Occupancy occupancy);

    /**
     * 用户更改发布状态
     *
     * @param kstatue
     * @return
     */
    @Update("UPDATE `occupancy` SET `kstatue`=#{kstatue},close_reason=#{reason},stoptime=NOW() WHERE id=#{id}")
    public Boolean updateKstatue(@Param("kstatue") Integer kstatue, @Param("id") Integer id);

    /**
     * 结束
     *
     * @param kstatue
     * @return
     */
    @Update("UPDATE `occupancy` SET `kstatue`=#{kstatue},close_reason=#{closeReason},stoptime=NOW() WHERE id=#{id}")
    public Boolean closeById(@Param("id") Integer id, @Param("kstatue") Integer kstatue, @Param("closeReason") String closeReason);

    /**
     * 更改审核状态
     *
     * @param statue
     * @return
     */
    @Update("UPDATE `occupancy` SET `statue`=#{statue},`reject`=#{reject},`audittime`=NOW() WHERE id=#{id}")
    public Boolean updateStatue(@Param("statue") Integer statue, @Param("id") Integer id, @Param("reject") String reject);

    /**
     * 根据条件查询
     *按条件搜索搜索可以展示的
     * @param occupancy
     * @return
     */
    public List<Occupancy> selectOccupancyByIndustry(Occupancy occupancy);

    /**
     * 后台查询
     * @param occupancy
     * @return
     */

   public List<Occupancy> selectOccupancyAdminByIndustry(Occupancy occupancy);

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
     *
     * @param
     * @return
     */
    @Select("SELECT COUNT(industry) FROM occupancy WHERE statue=1")
    public Integer countIndustry();

    @Select("SELECT * FROM `occupancy` where statue=1 and kstatue =1 and hot='热门' ORDER BY `creattime` DESC LIMIT 0,#{size}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userName", column = "userName"),

            @Result(property = "imgAddress", column = "id", one = @One(select = "com.example.busniess.dao.ImageAddressDao.selectimgAddress")),
            @Result(property = "businessInformation", column = "username", one = @One(select = "com.example.busniess.dao.BusinessInformationDao.selectBusinessInformation")),
            @Result(property = "businessCenter", column = "username", one = @One(select = "com.example.busniess.dao.BusinessCenterDao.selectOneBusinessCenter"))
    })
    List<Occupancy> getHotIndustry(@Param("size") Integer size);

    /**
     * 根据条件搜索
     * @param occupancy
     * @return
     */
    List<Occupancy> showByPageForCenter(Occupancy occupancy);

    @Select("SELECT companyName,resultTechnolo,stage,advantages,industry,industryDetail,attribute,transferType,price,province,city,district,negotiable FROM occupancy WHERE `statue`=1 AND `kstatue`=1 and username=#{username} ORDER BY creattime DESC limit #{size}")
    List<Occupancy> getOccupanyForProfessional(@Param("username") String username, @Param("size") Integer size);

    /**
     * 行业占比饼图
     * @param size
     * @return
     */
    @Select("SELECT COUNT(0) statue, industry FROM occupancy WHERE  statue=1 GROUP BY industry order by statue desc limit #{size}")
    List<Occupancy> demandsIndustryProp(@Param("size") Integer size);

    @Select("SELECT count(0) FROM occupancy WHERE  statue=1")
    int getCounts();

    @Select("SELECT count(0) as id,DATE_FORMAT(creattime,#{format}) companyName FROM `occupancy` where kstatue =1 and statue =1 group by DATE_FORMAT(creattime,#{format}) limit #{size}")
    List<Occupancy> occupancyRiseTrend(String format, Integer size);


    /**
     * 设置为热门行业
     */
    @Update("UPDATE occupancy SET hot=#{hot} WHERE id=#{id}")
    public  boolean setOccupancyHot(@Param("hot") String hot,@Param("id") Integer id);

}
