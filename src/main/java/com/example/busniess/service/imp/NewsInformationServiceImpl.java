package com.example.busniess.service.imp;

import com.example.busniess.dao.NewsInformationDao;
import com.example.busniess.entity.NewsInformation;
import com.example.busniess.entity.Occupancy;
import com.example.busniess.service.NewsInformationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NewsInformationServiceImpl implements NewsInformationService {
    @Autowired
    NewsInformationDao newsInformationDao;

    @Override
    public Boolean insertNewsInformation(NewsInformation newsInformation) {

        return  newsInformationDao.insertNewsInformation(newsInformation);
    }

    @Override
    public Boolean delectNewsInformation(Integer id) {

        return  newsInformationDao.delectNewsInformation(id);
    }

    @Override
    public Boolean updateNewsInformation(NewsInformation newsInformation) {
        return newsInformationDao.udateNewsInformation(newsInformation);
    }

    @Override
    public PageInfo selectAllNewsInformation(Integer pageNum,Integer pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List<NewsInformation> o = newsInformationDao.selectNewsInformation();
        PageInfo pageInfo = new PageInfo(o);
        return pageInfo;
//        return newsInformationDao.selectNewsInformation();
    }

    @Override
    public PageInfo selectNewsInformationByCategory(String category,Integer pageNum,Integer pagesize) {

        PageHelper.startPage(pageNum, pagesize);
        List<NewsInformation> o = newsInformationDao.selectNewsInformationByCategory(category);
        PageInfo pageInfo = new PageInfo(o);
        return pageInfo;
//        return newsInformationDao.selectNewsInformationByCategory(category);
    }

    @Override
    public NewsInformation selectOneNewsInformation(Integer id) {
        return newsInformationDao.selectOneNewsInformation(id);
    }
}
