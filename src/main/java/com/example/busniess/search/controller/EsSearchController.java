package com.example.busniess.search.controller;

import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.search.service.EsSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "EsSearchController")
@RequestMapping("/esSearch")
public class EsSearchController {
    @Autowired
    EsSearchService esSearchService;


    @ApiOperation(value = "根据关键字搜索")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public ReturnResult search(String keyword, @RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize,String indices){
        return ReturnResult.success(esSearchService.search(keyword,pageNum,pageSize,indices));
    }

    @ApiOperation(value = "根据关键字搜索")
    @RequestMapping(value = "/searchForCenter", method = RequestMethod.GET)
    @ResponseBody
    public ReturnResult searchForCenter(String keyword, @RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize,String indices){
        return ReturnResult.success(esSearchService.search(keyword,pageNum,pageSize,indices));
    }


}
