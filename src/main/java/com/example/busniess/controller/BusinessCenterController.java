package com.example.busniess.controller;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.dao.BusinessCenterDao;
import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.InformEntity;
import com.example.busniess.entity.Reject;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessCenterService;
import com.example.busniess.utiles.RabbitUtil;
import com.example.busniess.validator.UserValidator;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Date;

@RestController
@RequestMapping("/bussinessCenter")
@Validated
public class BusinessCenterController {

    @Resource
    BusinessCenterService businessCenterServiceImpl;

    @Autowired
    BusinessCenterDao businessCenterDao;
    @Autowired
    RabbitTemplate rabbitTemplate;

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
    @SysLog(value = "提交企业认证", type = "企业认证")
    @RequestMapping("/addAuthentication")
    public ReturnResult addAuthentication(@Validated({UserValidator.InSet.class})BusinessCenter businessCenter) {
        if (businessCenterServiceImpl.selectMyBusinessCenter(businessCenter.getUName()) != null) {
            return ReturnResult.erro(CodeMsg.DATA_DUPLICATION);
        }
        if (businessCenterServiceImpl.addBusinessCenter(businessCenter)) {
            //通知
            InformEntity informEntity=RabbitUtil.sendRabbic(businessCenter.getUName(),"提交了" +
                    businessCenter.getFirmName() + "的企业认证",new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);

            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUBMIT_ERROR);
    }



    /**
     * 查询所有企业认证信息
     * 按条件查询
     * 行业 industry
     * 人数 scale
     * 名称 firmName
     * 审核状态 statue
     *
     * @param businessCenter
     * @return
     */
    @RequestMapping("/findAllBusinessCenter")
    public ReturnResult findAllBusinessCenter(BusinessCenter businessCenter, Integer pageNumber, Integer pageSize) {
        //System.out.println(businessCenterServiceImpl.selectAllBusinessCenter(businessCenter));
        return ReturnResult.success(businessCenterServiceImpl.selectAllBusinessCenter(businessCenter, pageNumber, pageSize));
    }

    /**
     * 分页展示
     *
     * @param businessCenter
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/showByPage")
    public ReturnResult showByPage(BusinessCenter businessCenter, @RequestParam(required = false, defaultValue = "1") Integer pageNum, @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        return ReturnResult.success(businessCenterServiceImpl.showByPage(businessCenter, pageNum, pageSize));
    }

    /**
     * 查询自己的企业认证状态
     *
     * @param userName
     * @return
     */
    @RequestMapping("/findMyBusinessCenter")
    public ReturnResult findMyBusinessCenter(@NotNull(message = "用户账号不能为空") String userName) {
        return ReturnResult.success(businessCenterServiceImpl.selectMyBusinessCenter(userName));
    }

    /**
     * 查看具体认证信息
     */

    @RequestMapping("/findBussinessCenter")
    public ReturnResult findBussinessCenter(@NotNull(message = "id号不能为空") Integer id) {
        return ReturnResult.success(businessCenterServiceImpl.selectBusinessCenterById(id));
    }

    /**
     * 修改认证
     */
    @SysLog(value = "修改企业认证", type = "企业认证")
    @RequestMapping("/updateBusinessCenter")
    public ReturnResult updateBusinessCenter(BusinessCenter businessCenter) {
        if (businessCenterServiceImpl.updateBusinessCenter(businessCenter)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.ISSUE_ERROR);
    }
}
