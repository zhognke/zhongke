package com.example.busniess.dao;

import com.example.busniess.entity.DemandsCommentEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 * 
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-24 17:14:07
 */
public interface DemandsCommentDao {

    /**
     * 新增评论
     * @param demandsCommentEntity
     * @return
     */
    @Insert("insert into `demands_comment`(demands_id,username,comment_content) values (#{demandsId},#{username},#{commentContent})")
    public boolean addComment(DemandsCommentEntity demandsCommentEntity);

    /**
     * 根据需求id获取评论内容
     * @param demandsID
     * @return
     */
    @Select("select id,demands_id as demandsId,username,comment_content as commentContent,create_time as createTime from `demands_comment`  where demands_id = #{demandsID} order by create_time desc")
    public List<DemandsCommentEntity> getCommentByDemandsID(@Param("demandsID")int demandsID);

}
