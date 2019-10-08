package com.example.busniess.controller;

import com.example.busniess.entity.NewsInformation;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.NewsInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsInformationController {
    @Autowired
    NewsInformationService newsInformationServiceImplements;

    /**
     * 添加新闻资讯
     *
     * @param newsInformation
     * @return
     */
    @RequestMapping("/addNewsInformation")
    public ReturnResult addNewsInformation(NewsInformation newsInformation) {
        if (newsInformationServiceImplements.insertNewsInformation(newsInformation)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUMIT_ERROR);
    }

    /**
     * 删除新闻资讯
     *
     * @param id
     * @return
     */
    @RequestMapping("/removeNewsInformation")
    public ReturnResult removeNewsInformation(Integer id) {
        if (newsInformationServiceImplements.delectNewsInformation(id)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELECT_ERROR);
    }

    /**
     * 修改新闻资讯
     *
     * @param newsInformation
     * @return
     */
    @RequestMapping("/modifierNewsInformation")
    public ReturnResult modifierNewsInformation(NewsInformation newsInformation) {
        if (newsInformationServiceImplements.updateNewsInformation(newsInformation)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);
    }

    /**
     * 查看所有新闻资讯
     *
     * @return
     */
    @RequestMapping("/searchAllNewsInformation")
    public ReturnResult searchAllNewsInformation() {
        return ReturnResult.success(newsInformationServiceImplements.selectAllNewsInformation());
    }

    /**
     * 根据行业查看新闻资讯
     *
     * @param category
     * @return
     */
    @RequestMapping("/searchNewsByCategory")
    public ReturnResult searchNewsByCategory(String category) {
        return ReturnResult.success(newsInformationServiceImplements.selectNewsInformationByCategory(category));
    }
}
