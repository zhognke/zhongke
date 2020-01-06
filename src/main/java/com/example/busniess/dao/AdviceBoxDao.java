package com.example.busniess.dao;

import com.example.busniess.entity.AdviceBoxEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 意见信箱表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2020-01-06 09:01:24
 */
public interface AdviceBoxDao {
    /**
     * 新增
     *
     * @param adviceBoxEntity
     * @return
     */
    @Insert("insert into advice_box   " +
            "(username,type,real_name,phone,title,content,province,city,district,address) "
            + "values (#{username},#{type},#{realName},#{phone},#{title},#{content},#{province},#{city},#{district},#{address})")
    boolean insert(AdviceBoxEntity adviceBoxEntity);

    /**
     * 逻辑删除
     *
     * @return
     */
    @Update("update advice_box set del_flag = 1 where id= #{id};")
    boolean deleteByID(@Param("id") Integer id);

    /**
     * 批量逻辑删除
     *
     * @return
     */
    @Update("update advice_box set del_flag = 1 where id in ('${ids}');")
    boolean deleteBatch(@Param("ids") String ids);

    /**
     * 指派
     *
     * @param adviceBoxEntity
     * @return
     */
    @Update("update advice_box set assignment_username=#{assignmentUsername} where id = #{id}")
    boolean assignment(AdviceBoxEntity adviceBoxEntity);

    /**
     * 回信
     *
     * @param adviceBoxEntity
     * @return
     */
    @Update("update advice_box set reply=#{reply},reply_time=now(),statue=1 where id = #{id}")
    boolean reply(AdviceBoxEntity adviceBoxEntity);

    /**
     * 是否显示
     *
     * @param adviceBoxEntity
     * @return
     */
    @Update("update advice_box set is_show=#{isShow} where id = #{id}")
    boolean isShow(AdviceBoxEntity adviceBoxEntity);

    /**
     * 根据id查找
     *
     * @return
     */
    @Select("select ab.id,username,d.value type,real_name,phone,title,content,assignment_username,reply,province,city,district,address,is_show,statue,create_time,update_time,reply_time,ab.del_flag from advice_box ab,dict d where ab.type = d.code and ab.id = #{id}")
    AdviceBoxEntity selectByID(Integer id);

    /**
     * 根据条件查找(sql在mapper文件)
     *
     * @param adviceBoxEntity
     * @return
     */
    List<AdviceBoxEntity> search(AdviceBoxEntity adviceBoxEntity);

    @Select("SELECT count(0) FROM `advice_box` where TO_DAYS(create_time) > TO_DAYS(now())-365 and del_flag = 0")
    Integer counts();

    @Select("SELECT count(0) FROM `advice_box` where TO_DAYS(create_time) > TO_DAYS(now())-365 and del_flag = 0 and is_show=1")
    Integer countsIsShow();

    @Select("SELECT count(0) FROM `advice_box` where TO_DAYS(create_time) = TO_DAYS(now()) and del_flag = 0")
    Integer countsToday();

    @Select("SELECT count(0) FROM `advice_box` where TO_DAYS(reply_time) = TO_DAYS(now()) and del_flag = 0")
    Integer countsTodayReply();
}
