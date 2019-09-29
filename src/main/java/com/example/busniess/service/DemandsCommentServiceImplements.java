package com.example.busniess.service;

import com.example.busniess.dao.DemandsCommentDao;
import com.example.busniess.entity.DemandsCommentEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service("demandsCommentService")
public class DemandsCommentServiceImplements implements DemandsCommentService {

    @Autowired
    DemandsCommentDao demandsCommentDao;

    @Override
    public boolean addComment(DemandsCommentEntity demandsCommentEntity) {
        return demandsCommentDao.addComment(demandsCommentEntity);
    }

    @Override
    public List<DemandsCommentEntity> getByDemandsID(int demandsID) {
        return demandsCommentDao.getCommentByDemandsID(demandsID);
    }

    @Override
    public PageInfo showByPage(int demandsID, int pageNum, int pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List<DemandsCommentEntity> list = demandsCommentDao.getCommentByDemandsID(demandsID);
        PageInfo info = new PageInfo(list);
        return info;
    }
}
