package com.example.busniess.search.controller;

import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.search.model.EsDemandsModel;
import com.example.busniess.search.service.EsDemandsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 搜索需求管理Controller
 * Created by macro on 2018/6/19.
 */
@Controller
@Api(tags = "EsDemandsController")
@RequestMapping("/esDemands")
public class EsDemandsController {
    @Autowired
    private EsDemandsService esDemandsService;

    @ApiOperation(value = "导入所有数据库中需求到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult<Integer> importAllList() {
        int count = esDemandsService.importAll();
        return ReturnResult.success(count);
    }

    @ApiOperation(value = "根据id删除需求")
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    @ResponseBody
    public ReturnResult<Object> delete(Integer id) {
        esDemandsService.delete(id);
        return ReturnResult.success("操作成功");
    }

    @ApiOperation(value = "根据id批量删除需求")
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult<Object> delete(List<Integer> ids) {
        esDemandsService.delete(ids);
        return ReturnResult.success("操作成功");
    }

    @ApiOperation(value = "根据id批量删除需求")
    @RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult<Object> deleteAll(){
        esDemandsService.deleteAll();
        return ReturnResult.success("操作成功");
    }

    @ApiOperation(value = "根据id创建需求")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult<EsDemandsModel> create(Integer id) {
        EsDemandsModel EsDemands = esDemandsService.create(id);
        if (EsDemands != null) {
            return ReturnResult.success(EsDemands);
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    @ApiOperation(value = "创建需求")
    @RequestMapping(value = "/addEsModel", method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult<EsDemandsModel> addEsModel(EsDemandsModel esDemandsModel) {
        if (esDemandsService.create(esDemandsModel)) {
            return ReturnResult.success("操作成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }


    @ApiOperation(value = "根据关键字搜索")
    @RequestMapping(value = "/searchById", method = RequestMethod.GET)
    @ResponseBody
    public ReturnResult searchById(Integer id){
        EsDemandsModel esDemands = esDemandsService.selectById(id);
        return ReturnResult.success(esDemands);
    }


    @ApiOperation(value = "根据关键字搜索")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public ReturnResult search(EsDemandsModel esDemandsModel,@RequestParam(defaultValue = "0") Integer pageNum,@RequestParam(defaultValue = "5") Integer pageSize) {
        esDemandsModel.setStatus(0);
        esDemandsModel.setApprovalStatus(1);
        Page<EsDemandsModel> esProductPage = esDemandsService.search(esDemandsModel, pageNum, pageSize);
        return ReturnResult.success(esProductPage);
    }

    @ApiOperation(value = "根据关键字搜索")
    @RequestMapping(value = "/searchForCenter", method = RequestMethod.GET)
    @ResponseBody
    public ReturnResult searchForCenter(EsDemandsModel esDemandsModel,@RequestParam(defaultValue = "0") Integer pageNum,@RequestParam(defaultValue = "5") Integer pageSize) {
        Page<EsDemandsModel> esProductPage = esDemandsService.search(esDemandsModel, pageNum, pageSize);
        return ReturnResult.success(esProductPage);
    }
}
