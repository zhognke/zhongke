package com.example.busniess.controller;

import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.Reject;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;

import com.example.busniess.service.BusinessCenterService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@RestController
@RequestMapping("/bussinessCenter")
@Validated
public class BusinessCenterController {

    @Resource
    BusinessCenterService businessCenterServiceImplent;


    /**
     * 提交认证
     */
    @RequestMapping("/addAuthentication")
    public ReturnResult addAuthentication(@Validated() BusinessCenter businessCenter){
     if( businessCenterServiceImplent.addBusinessCenter(businessCenter) ) {
         return  ReturnResult.success();
     }
     return  ReturnResult.erro(CodeMsg.SUMIT_ERROR);
    }

/**
 * 驳回认证
 */
@RequestMapping("/dismissTheCertification")
public ReturnResult dismissTheCertification(Reject reject){
   if(businessCenterServiceImplent.rejectAudit(reject)){
       return ReturnResult.success();
   }
   return ReturnResult.erro(CodeMsg.SUMIT_ERROR);
}

    /**
     * 审核通过
     * @param id 企业信息id
     * @param rid 角色
     * @param userName 用户名
     * @param statue  审核状态
     * @param reId  驳回原因id
     * @return
     */
    @RequestMapping("/passTheAudit")
    public ReturnResult passTheAudit(Integer id, Integer rid, String userName,Integer statue,Integer reId){
  if(businessCenterServiceImplent.updateAuditStatue(id,rid,userName,statue,reId )) {
      return ReturnResult.success();
  }
  return ReturnResult.erro(CodeMsg.SUMIT_ERROR);
}

}
