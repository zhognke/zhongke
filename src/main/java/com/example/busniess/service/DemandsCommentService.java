package com.example.busniess.service;

import com.example.busniess.entity.DemandsCommentEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-24 17:14:07
 */
public interface DemandsCommentService {
    /**
     * 新增评论
     * @param demandsCommentEntity
     * @return
     */
    public boolean addComment(DemandsCommentEntity demandsCommentEntity);

    /**
     * 根据需求id获取评论内容
     * @param demandsID
     * @return
     */
    public List<DemandsCommentEntity> getByDemandsID(int demandsID);

    public PageInfo showByPage(int demandsID, int pageNum, int pagesize);
}

