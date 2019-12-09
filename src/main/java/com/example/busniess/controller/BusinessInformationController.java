package com.example.busniess.controller;

import com.example.busniess.entity.BusinessInformation;
import com.example.busniess.entity.InformEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessInformationService;
import com.example.busniess.utiles.RabbitUtil;
import com.example.busniess.validator.UserValidator;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@RestController
@RequestMapping("/BusinessInformation")
@Validated
public class BusinessInformationController {

    @Autowired
    BusinessInformationService businessInformationServiceImpl;
    @Autowired
    RabbitTemplate rabbitTemplate;

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

            //通知
            String userName = businessInformation.getUName();
            String content = "提交的企业补充信息";
            InformEntity informEntity = RabbitUtil.sendRabbic(userName, content, new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);
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
            BusinessInformation businessInformation = businessInformationServiceImpl.selectBusinessInformationById(id);
            //通知

            String userName = businessInformation.getUName();
            String content = "提交的企业补充信息";
            InformEntity informEntity = RabbitUtil.sendRabbic(userName, content, new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);

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

            //通知
            String userName = businessInformation.getUName();
            String content = "提交的企业补充信息";
            InformEntity informEntity = RabbitUtil.sendRabbic(userName, content, new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);

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
            @RequestParam(required = false, defaultValue = "1")
                    Integer pageNum,
            @RequestParam(required = false, defaultValue = "5")
                    Integer pagesize) {
        return ReturnResult.success(businessInformationServiceImpl.selectBusinessInformation(pageNum, pagesize));
    }
}
