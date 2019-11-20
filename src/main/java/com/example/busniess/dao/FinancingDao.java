package com.example.busniess.dao;

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
            "`lunit`, `statue`, `insertTime`) VALUES " +
            "(#{uName},#{projectType}, #{projectStatic}, #{goal}, #{type}, " +
            "#{projectName}, #{projectOutline}, #{advantage}, #{industry}," +
            " #{province}, #{city}, #{discribe}, #{projecrPhase}, " +
            "#{period}, #{linkMan}, #{phoneNumber}, #{extensive}, " +
            "#{cost}, #{hascost}, #{financing}, #{interest}, " +
            "#{time}, #{unit}, #{projectFinancing}, #{income}, " +
            "#{profit}, #{proportion}, #{ageLimit},#{lunit},0, NOW())")
    public Boolean insertFinancing(FinancingEntity financing);

    /**
     * 删除
     */
    @Delete("DELETE  FROM financing WHERE id=#{id}")
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
    public Boolean updateFinancing(FinancingEntity financing);

    /**
     * 查看具体
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM financing WHERE id=#{id}")
    public FinancingEntity seleOneFinancing(Integer id);

    /**
     * 查看自己的
     * @param uName
     * @return
     */
    @Select("SELECT id,industry,financing,income,projecrphase,period FROM financing WHERE  uname=#{uName}  ORDER BY insertTime DESC")
    public List<FinancingEntity> selectMyFinancing(String uName);

    /**
     * 查看所有的
     * @return
     */
//    @Select("SELECT id,industry,financing,income,projecrphase,period FROM financing")
    public List<FinancingEntity> selectAllFinancing(FinancingEntity financing);

    /**
     *修改审核状态
     */
    @Update("UPDATE `financing` SET `statue`=#{statue},`reject`=#{reject} WHERE (`id`=#{id})")
    public Boolean upFinacingStatue(@Param("id") Integer id,@Param("statue") Integer statue,@Param("reject")String reject);

    /**
     *查看所有 的
     * @return
     */
    @Select(" SELECT id,industry,financing,income,projecrphase,period FROM financing")
    public List<FinancingEntity> selectAllFinacing();
}
