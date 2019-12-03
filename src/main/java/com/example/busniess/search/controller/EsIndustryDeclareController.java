package com.example.busniess.search.controller;

import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.search.model.EsDemandsModel;
import com.example.busniess.search.model.EsIndustryDeclareModel;
import com.example.busniess.search.service.EsIndustryDeclareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 搜索申报管理Controller
 * Created by macro on 2018/6/19.
 */
@Controller
@Api(tags = "EsIndustryDeclareController")
@RequestMapping("/esIndustryDeclare")
public class EsIndustryDeclareController {
    @Autowired
    private EsIndustryDeclareService esIndustryDeclareService;

    @ApiOperation(value = "导入所有数据库中需求到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult<Integer> importAllList() {
        int count = esIndustryDeclareService.importAll();
        return ReturnResult.success(count);
    }

    @ApiOperation(value = "根据id删除需求")
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    @ResponseBody
    public ReturnResult<Object> delete(Integer id) {
        esIndustryDeclareService.delete(id);
        return ReturnResult.success(null);
    }

    @ApiOperation(value = "根据id批量删除需求")
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult<Object> delete(List<Integer> ids) {
        esIndustryDeclareService.delete(ids);
        return ReturnResult.success(null);
    }

    @ApiOperation(value = "根据id创建需求")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult<EsDemandsModel> create(Integer id) {
        EsIndustryDeclareModel EsDemands = esIndustryDeclareService.create(id);
        if (EsDemands != null) {
            return ReturnResult.success(EsDemands);
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    @ApiOperation(value = "根据关键字搜索")
    @RequestMapping(value = "/searchById", method = RequestMethod.GET)
    @ResponseBody
    public ReturnResult searchById(Integer id){
        EsIndustryDeclareModel esDemands = esIndustryDeclareService.selectById(id);
        return ReturnResult.success(esDemands);
    }

    @ApiOperation(value = "根据关键字搜索")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public ReturnResult search(@RequestParam(required = false) String keyword,
                               @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsIndustryDeclareModel> esProductPage = esIndustryDeclareService.search(keyword, pageNum, pageSize);
        return ReturnResult.success(esProductPage);
    }
}
