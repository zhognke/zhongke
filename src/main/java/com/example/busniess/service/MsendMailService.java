package com.example.busniess.service;

import com.example.busniess.entity.MsendMail;

import java.util.List;

public interface MsendMailService {
    public Boolean insertUser(MsendMail msendMail);
    public Boolean delectUser(Integer id);
    public List<MsendMail> selectAll();
    public Boolean updateMail(MsendMail msendMail);
}
