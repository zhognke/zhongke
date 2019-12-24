package com.example.busniess.search.controller;

import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.search.model.EsInnovationActivitiesModel;
import com.example.busniess.search.service.EsInnovationActivitiesService;
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
 * 搜索人才需求管理Controller
 * Created by macro on 2018/6/19.
 */
@Controller
@Api(tags = "EsInnovationActivitiesController")
@RequestMapping("/esInnovationActivities")
public class EsInnovationActivitiesController {
    @Autowired
    private EsInnovationActivitiesService esInnovationActivitiesService;

    @ApiOperation(value = "导入所有数据库中需求到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult<Integer> importAllList() {
        int count = esInnovationActivitiesService.importAll();
        return ReturnResult.success(count);
    }

    @ApiOperation(value = "根据id删除需求")
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    @ResponseBody
    public ReturnResult<Object> delete(Integer id) {
        esInnovationActivitiesService.delete(id);
        return ReturnResult.success("操作成功");
    }

    @ApiOperation(value = "根据id批量删除需求")
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult<Object> delete(List<Integer> ids) {
        esInnovationActivitiesService.delete(ids);
        return ReturnResult.success("操作成功");
    }

    @ApiOperation(value = "根据id批量删除需求")
    @RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult<Object> deleteAll(){
        esInnovationActivitiesService.deleteAll();
        return ReturnResult.success("操作成功");
    }

    @ApiOperation(value = "根据id创建需求")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult<EsInnovationActivitiesModel> create(Integer id) {
        EsInnovationActivitiesModel EsInnovationActivities = esInnovationActivitiesService.create(id);
        if (EsInnovationActivities != null) {
            return ReturnResult.success(EsInnovationActivities);
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    @ApiOperation(value = "创建需求")
    @RequestMapping(value = "/addEsModel", method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult<EsInnovationActivitiesModel> addEsModel(EsInnovationActivitiesModel esInnovationActivitiesModel) {
        if (esInnovationActivitiesService.create(esInnovationActivitiesModel)) {
            return ReturnResult.success("操作成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }


    @ApiOperation(value = "根据关键字搜索")
    @RequestMapping(value = "/searchById", method = RequestMethod.GET)
    @ResponseBody
    public ReturnResult searchById(Integer id){
        EsInnovationActivitiesModel esInnovationActivities = esInnovationActivitiesService.selectById(id);
        return ReturnResult.success(esInnovationActivities);
    }


    @ApiOperation(value = "根据关键字搜索")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public ReturnResult search(EsInnovationActivitiesModel esInnovationActivitiesModel,@RequestParam(defaultValue = "0") Integer pageNum,@RequestParam(defaultValue = "5") Integer pageSize) {
        esInnovationActivitiesModel.setStatus("0");
        esInnovationActivitiesModel.setApprovalStatus(1);
        Page<EsInnovationActivitiesModel> esProductPage = esInnovationActivitiesService.search(esInnovationActivitiesModel, pageNum, pageSize);
        return ReturnResult.success(esProductPage);
    }

    @ApiOperation(value = "根据关键字搜索")
    @RequestMapping(value = "/searchForCenter", method = RequestMethod.GET)
    @ResponseBody
    public ReturnResult searchForCenter(EsInnovationActivitiesModel esInnovationActivitiesModel,@RequestParam(defaultValue = "0") Integer pageNum,@RequestParam(defaultValue = "5") Integer pageSize) {
        Page<EsInnovationActivitiesModel> esProductPage = esInnovationActivitiesService.search(esInnovationActivitiesModel, pageNum, pageSize);
        return ReturnResult.success(esProductPage);
    }
}
