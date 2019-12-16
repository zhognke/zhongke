package com.example.busniess.service.imp;

import com.example.busniess.dao.NewsInformationDao;
import com.example.busniess.entity.NewsInformation;
import com.example.busniess.entity.Occupancy;
import com.example.busniess.service.NewsInformationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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
    public PageInfo selectNewsInformationByCategory(NewsInformation newsInformation,Integer pageNum,Integer pagesize) {

        PageHelper.startPage(pageNum, pagesize);
        List<NewsInformation> o = newsInformationDao.selectNewsInformationByCategory(newsInformation);
        PageInfo pageInfo = new PageInfo(o);
        return pageInfo;
//        return newsInformationDao.selectNewsInformationByCategory(category);
    }

    @Override
    public NewsInformation selectOneNewsInformation(Integer id) {
        return newsInformationDao.selectOneNewsInformation(id);
    }

    /**
     * 文件下载
     */
    public Boolean download(HttpServletRequest request, HttpServletResponse response, String path) throws UnsupportedEncodingException {

        // 如果文件名不为空，则进行下载
        File file = new File(path);
        String fileName = file.getName();
        if (fileName != null) {


            // 如果文件名存在，则进行下载
            if (file.exists()) {

                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");

                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//                       response.setHeader("Content-Disposition",
//                        "attachment;filename=" + fileName);

                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = 0;
                    while ((i = bis.read(buffer)) != -1) {
                        os.write(buffer, 0, i);
//                        i = bis.read(buffer);
                    }
                    System.out.println("Download the song successfully!");
                    return true;
                } catch (Exception e) {
                    System.out.println("Download the song failed!");
                    return false;
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return true;
    }




}
