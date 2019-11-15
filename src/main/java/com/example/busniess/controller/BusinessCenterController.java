package com.example.busniess.controller;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.dao.BusinessCenterDao;
import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.Reject;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessCenterService;
import com.example.busniess.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/bussinessCenter")
@Validated
public class BusinessCenterController {

    @Resource
    BusinessCenterService businessCenterServiceImpl;

    @Autowired
    BusinessCenterDao businessCenterDao;

    /**
     * 根据关键字返回企业名
     *
     * @param firmName
     * @return
     */
    @RequestMapping("/returnfirmName")
    public ReturnResult returnfirmName(@NotNull(message = "名字不能为空") String firmName) {
        return ReturnResult.success(businessCenterDao.selectFirmName(firmName));
    }


    /**
     * 提交认证
     */
    @SysLog(value="提交企业认证",type="企业认证")
    @RequestMapping("/addAuthentication")
    public ReturnResult addAuthentication(@Validated({UserValidator.InSet.class}) BusinessCenter businessCenter) {
        if(businessCenterServiceImpl.selectMyBusinessCenter(businessCenter.getUName())!=null){
            return ReturnResult.erro(CodeMsg.DATA_DUPLICATION);
        }
        if (businessCenterServiceImpl.addBusinessCenter(businessCenter)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUMIT_ERROR);
    }

    /**
     * 驳回认证
     */
    @SysLog(value="驳回企业认证",type="企业认证")
    @RequestMapping("/dismissTheCertification")
    public ReturnResult dismissTheCertification(@Validated({UserValidator.InSet.class}) Reject reject) {
        if (businessCenterServiceImpl.rejectAudit(reject)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUMIT_ERROR);
    }

    /**
     * 审核通过
     *
     * @param id       企业信息id
     * @param rid      角色
     * @param userName 用户名
     * @param statue   审核状态
     * @param reId     驳回原因id
     * @return
     */
    @SysLog(value="审核企业认证-通过",type="企业认证")
    @RequestMapping("/passTheAudit")
    public ReturnResult passTheAudit(@NotNull(message = "企业id不能为空") Integer id, @NotNull(message = "角色名不能为空") Integer rid, @NotNull(message = "用户名不能为空") String userName, @NotNull(message = "状态不能为空") Integer statue, @NotNull(message = "驳回原因id不能为空") Integer reId) {
        if (businessCenterServiceImpl.updateAuditStatue(id, rid, userName, statue, reId)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUMIT_ERROR);
    }

    /**
     * 查询所有企业认证信息
     * 按条件查询
     * 行业 industry
     * 人数 scale
     * 名称 firmName
     * 审核状态 statue
     * @param businessCenter
     * @return
     */
    @RequestMapping("/findAllBusinessCenter")
    public ReturnResult findAllBusinessCenter(BusinessCenter businessCenter,Integer pageNumber,Integer pageSize) {
        //System.out.println(businessCenterServiceImpl.selectAllBusinessCenter(businessCenter));
        return ReturnResult.success(businessCenterServiceImpl.selectAllBusinessCenter(businessCenter, pageNumber,pageSize));
    }

    /**
     * 分页展示
     * @param businessCenter
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/showByPage")
    public ReturnResult showByPage(BusinessCenter businessCenter, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        return ReturnResult.success(businessCenterServiceImpl.showByPage(businessCenter,pageNum,pageSize));
    }

    /**
     * 查询自己的企业认证状态
     * @param userName
     * @return
     */
    @RequestMapping("/findMyBusinessCenter")
    public ReturnResult findMyBusinessCenter(String userName) {
        return ReturnResult.success(businessCenterServiceImpl.selectMyBusinessCenter(userName));
    }

    /**
     * 查看具体认证信息hello
     */

    @RequestMapping("/findBussinessCenter")
    public ReturnResult findBussinessCenter(Integer id) {
        return ReturnResult.success(businessCenterServiceImpl.selectBusinessCenterById(id));
    }

    /**
     * 修改认证
     */
    @SysLog(value="修改企业认证",type="企业认证")
    @RequestMapping("/updateBusinessCenter")
    public ReturnResult updateBusinessCenter(BusinessCenter businessCenter) {
        if(businessCenterServiceImpl.updateBusinessCenter(businessCenter)){
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.ISSUE_ERROR);
    }
}
