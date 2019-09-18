package com.example.busniess.service;

import com.example.busniess.dao.MsendMailDao;
import com.example.busniess.entity.MsendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsendMailServiceImplements implements MsendMailService {

    @Autowired
    MsendMailDao msendMailDao;

    @Override
    public Boolean insertUser(MsendMail msendMail) {
        return msendMailDao.insertUser(msendMail);
    }

    @Override
    public Boolean delectUser(Integer id) {
        return msendMailDao.delectUser(id);
    }

    @Override
    public List<MsendMail> selectAll() {
        return msendMailDao.selectAll();
    }

    @Override
    public Boolean updateMail(MsendMail msendMail) {
        return msendMailDao.updateMail(msendMail);
    }
}
