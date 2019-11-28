package com.example.busniess.controller;

import com.example.busniess.entity.NewsInformation;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.NewsInformationService;
import com.example.busniess.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/news")
@Validated
public class NewsInformationController {
    @Autowired
    NewsInformationService newsInformationServiceImpl;

    /**
     * 添加新闻资讯
     *
     * @param newsInformation
     * @return
     */
    @RequestMapping("/addNewsInformation")
    public ReturnResult addNewsInformation(@Validated({UserValidator.InSet.class})NewsInformation newsInformation) {
        if (newsInformationServiceImpl.insertNewsInformation(newsInformation)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUBMIT_ERROR);
    }

    /**
     * 删除新闻资讯
     *
     * @param id
     * @return
     */
    @RequestMapping("/removeNewsInformation")
    public ReturnResult removeNewsInformation(@NotNull(message = "id号不能为空") Integer id) {
        if (newsInformationServiceImpl.delectNewsInformation(id)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELETE_ERROR);
    }

    /**
     * 修改新闻资讯
     *
     * @param newsInformation
     * @return
     */
    @RequestMapping("/modifierNewsInformation")
    public ReturnResult modifierNewsInformation(@Validated({UserValidator.UpDate.class})NewsInformation newsInformation) {
        if (newsInformationServiceImpl.updateNewsInformation(newsInformation)) {
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
    public ReturnResult searchAllNewsInformation(@NotNull(message = "第几页不能为空") Integer pageNum,@NotNull(message = "每页显示多少数据不能为空") Integer pagesize) {
        return ReturnResult.success(newsInformationServiceImpl.selectAllNewsInformation(pageNum,pagesize));
    }

    /**
     * 根据行业查看新闻资讯
     *
     * @param category
     * @return
     */
    @RequestMapping("/searchNewsByCategory")
    public ReturnResult searchNewsByCategory(@NotBlank(message = "行业不能为空") String category,@NotNull(message = "第几页不能为空")Integer pageNum,@NotNull(message = "每页显示多少数据不能为空")Integer pagesize) {
        return ReturnResult.success(newsInformationServiceImpl.selectNewsInformationByCategory(category,pageNum,pagesize));
    }

    /**
     * 查看单个新闻资讯
     * @param id
     * @return
     */
    @RequestMapping("/serarchOneNewsInformation")
    public ReturnResult serarchOneNewsInformation(@NotNull(message = "id号不能为空") Integer id){
        return ReturnResult.success(newsInformationServiceImpl.selectOneNewsInformation(id));
    }

}
