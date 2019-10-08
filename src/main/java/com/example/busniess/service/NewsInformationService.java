package com.example.busniess.service;

import com.example.busniess.entity.NewsInformation;

import java.util.List;

public interface NewsInformationService {
    /**
     * 插入新闻资讯
     * @param newsInformation
     * @return
     */
    public  Boolean insertNewsInformation(NewsInformation newsInformation);

    /**
     * 删除新闻资讯
     * @param id
     * @return
     */
    public  Boolean delectNewsInformation(Integer id);

    /**
     * 修改新闻资讯
     * @param newsInformation
     * @return
     */
    public Boolean updateNewsInformation(NewsInformation newsInformation);

    /**
     * 产看所有新闻资讯
     * @return
     */
    public List<NewsInformation> selectAllNewsInformation();

    /**
     * 按照类别查看新闻资讯
     * @param category
     * @return
     */
    public List<NewsInformation> selectNewsInformationByCategory(String category);

}
