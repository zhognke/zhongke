package com.example.busniess.dao;

import com.example.busniess.entity.Financing;

import org.apache.ibatis.annotations.*;

import java.util.Date;
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
            "`lunit`, `statue`, `insertTime`) VALUES " +
            "(#{uName},#{projectType}, #{projectStatic}, #{goal}, #{type}, " +
            "#{projectName}, #{projectOutline}, #{advantage}, #{industry}," +
            " #{province}, #{city}, #{discribe}, #{projecrPhase}, " +
            "#{period}, #{linkMan}, #{phoneNumber}, #{extensive}, " +
            "#{cost}, #{hascost}, #{financing}, #{interest}, " +
            "#{time}, #{unit}, #{projectFinancing}, #{income}, " +
            "#{profit}, #{proportion}, #{ageLimit},#{lunit},0, NOW())")
    public Boolean insertFinancing(Financing financing);

    /**
     * 删除
     */
    @Delete("ELETE  FROM financing WHERE id=#{id}")
    public boolean delectFinancing(Integer id);

    /**
     * 修改
     */
    @Update("UPDATE `financing` SET `projecttype`=#{projectType}, `projectstatic`=#{projectStatic}, " +
            "`goal`= #{goal}, `type`=#{type}, `projectname`=#{projectName}," +
            " `projectoutline`=#{projectOutline}, `advantage`=#{advantage}, `industry`=#{industry}, " +
            "`province`=#{city}, `city`='32', `discribe`='32'," +
            " `projecrphase`=#{province}, `period`=#{city}, `linkman`=#{linkMan}, " +
            "`phonenumber`=#{phoneNumber}, `extensive`=#{extensive}, `cost`=#{cost}, " +
            "`hascost`=#{hascost}, `financing`=#{financing}, `interest`=#{interest}," +
            " `time`=#{time}, `unit`=#{unit}, `projectfinancing`=#{projectFinancing}, " +
            "`income`=#{income}, `profit`=#{profit}, `proportion`=#{proportion}, " +
            "`agelimit`=#{ageLimit}, `lunit`=#{lunit}, " +
            "`updateTime`=NOW() WHERE (`id`=#{id})")
    public Boolean updateFinancing(Financing financing);

    /**
     * 查看具体
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM financing WHERE id=#{id}")
    public Financing seleOneFinancing(Integer id);

    /**
     * 查看自己的
     * @param uName
     * @return
     */
    @Select("SELECT id,industry,financing,income,projecrphase,period FROM financing WHERE  uname=#{uName}  ORDER BY insertTime DESC")
    public List<Financing> selectMyFinancing(String uName);

    /**
     * 查看所有的
     * @return
     */
//    @Select("SELECT id,industry,financing,income,projecrphase,period FROM financing")
    public List<Financing> selectAllFinancing(Financing financing);

    /**
     *修改审核状态
     */
    @Update("UPDATE `financing` SET `statue`=#{statue} WHERE (`id`=#{id})")
    public Boolean upFinacingStatue(@Param("statue") Integer statue,@Param("id") Integer id);

    /**
     *查看所有 的
     * @return
     */
    @Select(" SELECT id,industry,financing,income,projecrphase,period FROM financing")
    public List<Financing> selectAllFinacing();
}
