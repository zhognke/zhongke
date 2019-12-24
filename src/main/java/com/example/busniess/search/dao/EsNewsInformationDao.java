package com.example.busniess.search.dao;

import com.example.busniess.search.model.EsNewsInformationModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 创新活动表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-12-12 11:42:14
 */
public interface EsNewsInformationDao {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select id,uname,category,category industry,title,cover,detail,detail content,submittime create_time,uptime update_time,resource,publishDate,summary,summary outline from `newsinformation`")
    List<EsNewsInformationModel> selectAll();


    @Select("select id,uname,category,category industry,title,cover,detail,detail content,submittime create_time,uptime update_time,resource,publishDate,summary,summary outline from `newsinformation` where id = #{id}")
    EsNewsInformationModel selectById(Integer id);
}
