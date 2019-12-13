package com.example.busniess.service;

import com.example.busniess.entity.NewsInformation;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
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
    public PageInfo selectAllNewsInformation(Integer pageNum,Integer pagesize);

    /**
     * 按照类别查看新闻资讯
     * @param category
     * @return
     */
    public PageInfo selectNewsInformationByCategory(String category, Integer pageNum, Integer pagesize);

    /**
     * 查看单个新闻资讯
     * @param id
     * @return
     */
    public NewsInformation selectOneNewsInformation(Integer id);

    /**
     * 下载
     */
    public Boolean download(HttpServletRequest request, HttpServletResponse response, String path) throws UnsupportedEncodingException;

}
