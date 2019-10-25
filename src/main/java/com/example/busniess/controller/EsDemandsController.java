//package com.example.busniess.controller;
//
//import com.example.busniess.resultpackage.CodeMsg;
//import com.example.busniess.resultpackage.ReturnResult;
//import com.example.busniess.search.model.EsDemands;
//import com.example.busniess.service.EsDemandsService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * 搜索需求管理Controller
// * Created by macro on 2018/6/19.
// */
//@Controller
//@Api(tags = "EsDemandsController")
//@RequestMapping("/esDemands")
//public class EsDemandsController {
//    @Autowired
//    private EsDemandsService esDemandsService;
//
//    @ApiOperation(value = "导入所有数据库中需求到ES")
//    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
//    @ResponseBody
//    public ReturnResult<Integer> importAllList() {
//        int count = esDemandsService.importAll();
//        return ReturnResult.success(count);
//    }
//
//    @ApiOperation(value = "根据id删除需求")
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public ReturnResult<Object> delete(@PathVariable Integer id) {
//        esDemandsService.delete(id);
//        return ReturnResult.success(null);
//    }
//
//    @ApiOperation(value = "根据id批量删除需求")
//    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
//    @ResponseBody
//    public ReturnResult<Object> delete(@RequestParam("ids") List<Integer> ids) {
//        esDemandsService.delete(ids);
//        return ReturnResult.success(null);
//    }
//
//    @ApiOperation(value = "根据id创建需求")
//    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public ReturnResult<EsDemands> create(@PathVariable Integer id) {
//        EsDemands EsDemands = esDemandsService.create(id);
//        if (EsDemands != null) {
//            return ReturnResult.success(EsDemands);
//        } else {
//            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
//        }
//    }
//
//    @ApiOperation(value = "根据关键字搜索")
//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    @ResponseBody
//    public ReturnResult search(@RequestParam(required = false) String keyword,
//                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
//                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
//        Page<EsDemands> esProductPage = esDemandsService.search(keyword, pageNum, pageSize);
//        return ReturnResult.success(esProductPage);
//    }
//}
