package com.example.busniess.controller.manager;

import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.BusinessInformation;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessCenterService;
import com.example.busniess.service.BusinessInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class BusinessInformationAdminController {
    @Autowired
    BusinessCenterService businessCenterServiceImpl;
    @Autowired
    BusinessInformationService businessInformationServiceImpl;

    /**
     * 按条件检索企业补充信息
     */

    @RequestMapping("/searchAdminBusinessInformation")
    public ReturnResult searchAdminBusinessInformation(BusinessCenter businessCenter,
                                                       @RequestParam(defaultValue = "1", required = false)
                                                               Integer pageNumber,
                                                       @RequestParam(defaultValue = "5", required = false)
                                                               Integer pageSize) {
        return ReturnResult.success(businessCenterServiceImpl.showByPage(businessCenter, pageNumber, pageSize));


    }


    /**
     * 添加企业补充信息
     */
    @RequestMapping("/addAdminBusinessInformation")
    public ReturnResult addAdminBusinessInformation(BusinessInformation businessInformation) {


        if (businessInformationServiceImpl.addBusinessInformation(businessInformation)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.ADD_LERROR);
    }

    /**
     * 修改
     */
    @RequestMapping("/upAdminInformation")
    public ReturnResult upAdminInformation(BusinessInformation businessInformation) {

        if (businessInformationServiceImpl.upDateBusinessInformation(businessInformation)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);
    }

    /**
     * 删除
     */
    @RequestMapping("/delectAdminInformation")
    public ReturnResult delectAdminInformation(Integer id) {
        if (businessInformationServiceImpl.delectInformationByStatue(id,3)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELETE_ERROR);
    }

    /**
     * 查看没有填写补充信息的用户入住信息
     */
    @RequestMapping("/selectBusinessNoInformation")
    public ReturnResult selectBusinessNoInformation() {


        return ReturnResult.success(businessCenterServiceImpl.selectBusinessCenterNoinformation(1));
    }


    /**
     * 查看具体的企业认证补充信息
     */

   @RequestMapping("/selectBusinessInformationById")
    public  ReturnResult selectBusinessInformationById(Integer id){
        return  ReturnResult.success(businessInformationServiceImpl.selectBusinessInformationById(id));
    }

}
