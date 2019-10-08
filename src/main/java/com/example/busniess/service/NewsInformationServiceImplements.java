package com.example.busniess.service;

import com.example.busniess.dao.NewsInformationDao;
import com.example.busniess.entity.NewsInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NewsInformationServiceImplements implements NewsInformationService {
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
    public List<NewsInformation> selectAllNewsInformation() {
        return newsInformationDao.selectNewsInformation();
    }

    @Override
    public List<NewsInformation> selectNewsInformationByCategory(String category) {
        return newsInformationDao.selectNewsInformationByCategory(category);
    }
}
