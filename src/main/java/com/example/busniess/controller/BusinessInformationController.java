package com.example.busniess.controller;

import com.example.busniess.entity.BusinessInformation;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessInformationService;
import com.example.busniess.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/BusinessInformation")
@Validated
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
    public ReturnResult addBusinessInformation(
            @Validated({UserValidator.InSet.class})
            BusinessInformation businessInformation) {

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
    public ReturnResult delectBusinessInformation(
            @NotNull(message = "id不能为空") Integer id) {
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
    public ReturnResult updateBusinessInformation(
            @Validated({UserValidator.UpDate.class})
                    BusinessInformation businessInformation) {
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
    public ReturnResult selectBusinessInformation(
            @NotBlank(message = "用户名不能为空")
                    String userName) {
        return ReturnResult.success(businessInformationServiceImpl.selectBusinessInformation(userName));
    }

    /**
     * 查看所有
     */
    @RequestMapping("/selectAllBusinessInfoemation")
    public ReturnResult selectAllBusinessInfoemation(
            @RequestParam(required = false,defaultValue ="1")
                    Integer pageNum,
            @RequestParam(required = false,defaultValue ="5")
            Integer pagesize) {
        return ReturnResult.success(businessInformationServiceImpl.selectBusinessInformation(pageNum, pagesize));
    }
}
