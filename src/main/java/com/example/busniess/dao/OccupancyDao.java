package com.example.busniess.dao;

import com.example.busniess.entity.ImageAddress;
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
            @Result(property = "id",column = "id"),
            @Result(property = "userName",column = "userName"),
         @Result(property = "imgAddress", column = "id", many = @Many(select = "com.example.busniess.dao.ImageAddressDao.selectimgAddress")),
            @Result(property = "businessInformation", column = "username", one = @One(select = "com.example.busniess.dao.BusinessInformationDao.selectBusinessInformation"))
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
     * 名字
     * @param userName
     * @return
     */
    @Select("SELECT * FROM occupancy WHERE username=#{userName} AND `statue`=1 AND `kstatue`=1   ORDER BY creattime DESC")
    public List<Occupancy> selectByname(String userName);

    /**
     * 根据id 查询单个
     *
     * @param id
     * @return imgAddress
     */

    @Select("SELECT * FROM occupancy WHERE id=#{id}")
    @Results({
            @Result(property = "imgAddress", column = "id", one = @One(select = "com.example.busniess.dao.ImageAddressDao.selectimgAddress")),
            @Result(property = "businessInformation", column = "username", one = @One(select = "com.example.busniess.dao.BusinessInformationDao.selectBusinessInformation"))
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
            "`advantages`=#{advantages}, `industry`=#{industry},`industryDetail`=#{industryDetail},`attribute`=#{attribute}," +
            " `patenNname`=#{patenNname}, `patenNumber`=#{patenNumber}, `price`=#{price}," +
            "`registerNumber`=#{registerNumber}, `describe`=#{describe}, `appliedRange`=#{appliedRange}," +
            "`transferType`=#{transferType},`linkman`=#{linkMan}, `phonenumber`=#{phoneNumber},`province`=#{province}," +
            "`city`=#{city},`district`=#{district},`uptime`=NOW() WHERE (`id`=#{id})")
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
            "`creattime`,`kstatue`, `statue`) " +
            "VALUES (#{userName},#{companyName} ,#{resultTechnolo}, #{stage}, #{advantages}, #{industry}, #{industryDetail},#{attribute}, #{patenNname}, #{patenNumber}, " +
            "#{price},#{registerNumber},#{describe}, #{appliedRange},#{transferType},#{linkMan}, #{phoneNumber},#{province},#{city},#{district},#{stopTime}," +
            "NOW(), '1', '0')")
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
    public Boolean closeById(@Param("id") Integer id,@Param("kstatue")Integer kstatue,@Param("closeReason")String closeReason);

    /**
     * 更改审核状态
     *
     * @param statue
     * @return
     */
    @Update("UPDATE `occupancy` SET `statue`=#{statue},`reject`=#{reject},`audittime`=NOW() WHERE id=#{id}")
    public Boolean updateStatue(@Param("statue") Integer statue, @Param("id") Integer id, @Param("reject")String reject);

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

    @Select("SELECT id,resultTechnolo,price,industry,industryDetail,attribute,stage,transferType FROM `occupancy` where statue=1 and kstatue =1 and hot='热门' ORDER BY `creattime` DESC LIMIT 0,#{size}")
    List<Occupancy> getHotIndustry(@Param("size")Integer size);

    List<Occupancy> showByPageForCenter(Occupancy occupancy);

    @Select("SELECT companyName,resultTechnolo,stage,advantages,industry,industryDetail,attribute,transferType,price,province,city,district FROM occupancy WHERE `statue`=1 AND `kstatue`=1 and username=#{username} ORDER BY creattime DESC limit #{size}")
    List<Occupancy> getOccupanyForProfessional(@Param("username")String username,@Param("size")Integer size);

    /**
     *
     * @param id
     * @return
     */
//    public List<ImageAddress> selectimgAddress(Integer id);
}
