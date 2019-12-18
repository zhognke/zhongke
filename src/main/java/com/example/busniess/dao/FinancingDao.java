package com.example.busniess.dao;

import com.example.busniess.entity.DemandsEntity;
import com.example.busniess.entity.FinancingEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FinancingDao {
    /**
     * 新增项目融资
     *
     * @param financing
     * @return
     */
    @Insert("INSERT INTO `financing` (`uname`,`projecttype`, `projectstatic`," +
            " `goal`, `type`, `projectname`, " +
            "`projectoutline`,`advantage`, `industry`, " +
            "`province`, `city`, `discribe`, " +
            "`projecrphase`, `period`, `linkman`, " +
            "`phonenumber`, `extensive`, `cost`, " +
            "`hascost`, `financing`, `interest`, `time`," +
            "`unit`, `projectfinancing`, `income`, " +
            "`profit`, `proportion`, `agelimit`, " +
            "`lunit`, `statue`, `insertTime`,`kstatue`) VALUES " +
            "(#{uName},#{projectType}, #{projectStatic}, #{goal}, #{type}, " +
            "#{projectName}, #{projectOutline}, #{advantage}, #{industry}," +
            " #{province}, #{city}, #{discribe}, #{projecrPhase}, " +
            "#{period}, #{linkMan}, #{phoneNumber}, #{extensive}, " +
            "#{cost}, #{hascost}, #{financing}, #{interest}, " +
            "#{time}, #{unit}, #{projectFinancing}, #{income}, " +
            "#{profit}, #{proportion}, #{ageLimit},#{lunit},0, NOW(),1)")
    public Boolean insertFinancing(FinancingEntity financing);

    /**
     * 删除
     */
    @Delete("DELETE  FROM financing WHERE id=#{id}")
    public boolean delectFinancing(Integer id);

    /**
     * 修改
     */
    @Update("UPDATE `financing` SET " +
            "`projectName`=#{projectName}, `projectOutline`=#{projectOutline}, " +
            "`advantage`= #{advantage}, `industry`=#{industry}, " +
            "`province`=#{province}, `city`=#{city}, `discribe`=#{discribe}," +
            " `projecrphase`=#{projecrPhase}, " +
            " `projectType`=#{projectType}, `projectStatic`=#{projectStatic}, " +
            "`goal`=#{goal}, `type`=#{type}, `period`=#{period}," +
            " `linkMan`=#{linkMan}, `phoneNumber`=#{phoneNumber}, `extensive`=#{extensive}, " +
            "`cost`=#{cost}, `hascost`=#{hascost}, `financing`=#{financing}, " +
            "`interest`=#{interest}, `time`=#{time}, " +
            "`unit`=#{unit},`lunit`=#{lunit},`projectFinancing`=#{projectFinancing}," +
            "`income`=#{income},`profit`=#{profit},`proportion`=#{proportion},`statue`=0," +
            "`updateTime`=NOW() WHERE (`id`=#{id})")
    public Boolean updateFinancing(FinancingEntity financing);

    /**
     * 查看具体
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM financing WHERE id=#{id}")
    @Results({
            @Result(property = "uName", column = "uname"),
            @Result(property = "businessInformation", column = "uname", one = @One(select = "com.example.busniess.dao.BusinessInformationDao.selectBusinessInformation")),
            @Result(property = "businessCenter", column = "uname", one = @One(select = "com.example.busniess.dao.BusinessCenterDao.selectOneBusinessCenter"))
    })
    public FinancingEntity seleOneFinancing(Integer id);

    /**
     * 查看自己的
     *
     * @param uName
     * @return
     */
    @Select("SELECT id,projectname,industry,financing,income,projecrphase,period,projectFinancing,statue,lunit,insertTime FROM financing WHERE  uname=#{uName}  ORDER BY insertTime DESC")
    @Results({
            @Result(property = "uName", column = "uname"),
            @Result(property = "businessInformation", column = "uname", one = @One(select = "com.example.busniess.dao.BusinessInformationDao.selectBusinessInformation")),
    })
    public List<FinancingEntity> selectMyFinancing(String uName);

    /**
     * 查看所有的
     *
     * @return
     */
//    @Select("SELECT id,industry,financing,income,projecrphase,period FROM financing")
    public List<FinancingEntity> selectAllFinancing(FinancingEntity financing);

    /**
     * 修改审核状态
     */
    @Update("UPDATE `financing` SET `statue`=#{statue},`reject`=#{reject} WHERE (`id`=#{id})")
    public Boolean upFinacingStatue(@Param("id") Integer id, @Param("statue") Integer statue, @Param("reject") String reject);

    /**
     *用户修改
     * @param id
     * @param statue
     * @param reject
     * @return
     */
    @Update("UPDATE `financing` SET `kstatue`=#{kStatue},`reject`=#{reject} WHERE (`id`=#{id})")
    public Boolean upFinacingKstatue(@Param("id") Integer id, @Param("statue") Integer statue, @Param("reject") String reject);
    /**
     * 查看所有 的
     *
     * @return
     */
    @Select("SELECT id,uname,projectname,industry,financing,income,projecrphase,period,projectFinancing,statue,lunit,insertTime FROM financing ORDER BY insertTime DESC")
    @Results({
            @Result(property = "uName", column = "uname"),
            @Result(property = "businessInformation", column = "uname", one = @One(select = "com.example.busniess.dao.BusinessInformationDao.selectBusinessInformation")),
    })

    public List<FinancingEntity> selectAllFinacing0();

    @Select("SELECT industry FROM financing  where statue=1 AND kstatue=1 GROUP BY industry ORDER BY COUNT(industry) DESC LIMIT 5")
    public List<String>  selectIndustry();

    @Select("SELECT count(0) period, SUBSTRING_INDEX(industry,',',1) industry from `financing` where kstatue =1 and statue=1 group by SUBSTRING_INDEX(industry,',',1)  order by period desc limit #{size}")
    List<FinancingEntity> getIndustryProp(@Param("size") Integer size);

    @Select("select count(0) from financing where kstatue =1 and statue =1")
    int getCounts();

    @Select("SELECT count(0) as ageLimit,DATE_FORMAT(insertTime,#{format}) projectName FROM `financing` where kstatue =1 and statue =1 group by DATE_FORMAT(insertTime,#{format}) desc limit #{size}")
    List<FinancingEntity> financingRiseTrend(@Param("format")String format, @Param("size")Integer size);
}
