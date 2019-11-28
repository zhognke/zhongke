package com.example.busniess.controller;

import com.example.busniess.entity.BusinessInformation;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BusinessInformation")
public class BusinessInformationController {

    @Autowired
    BusinessInformationService businessInformationServiceImpl;

    /**
     * 增加
     *
     * @param businessInformation
     * @return
     */
    @RequestMapping("/addBusinessInformation")
    public ReturnResult addBusinessInformation(BusinessInformation businessInformation) {

        if (businessInformationServiceImpl.addBusinessInformation(businessInformation)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUBMIT_ERROR);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/delectBusinessInformation")
    public ReturnResult delectBusinessInformation(Integer id) {
        if (businessInformationServiceImpl.delectBusinessInformation(id)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELETE_ERROR);
    }

    /**
     * 修改
     *
     * @param businessInformation
     * @return
     */
    @RequestMapping("/updateBusinessInformation")
    public ReturnResult updateBusinessInformation(BusinessInformation businessInformation) {
        if (businessInformationServiceImpl.upDateBusinessInformation(businessInformation)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);
    }

    /**
     * 查看自己的
     *
     * @param userName
     * @return
     */
    @RequestMapping("/selectBusinessInformation")
    public ReturnResult selectBusinessInformation(String userName) {
        return ReturnResult.success(businessInformationServiceImpl.selectBusinessInformation(userName));
    }

    /**
     * 查看所有
     */
    @RequestMapping("/selectAllBusinessInfoemation")
    public ReturnResult selectAllBusinessInfoemation(Integer pageNum, Integer pagesize) {
        return ReturnResult.success(businessInformationServiceImpl.selectBusinessInformation(pageNum, pagesize));
    }
}
