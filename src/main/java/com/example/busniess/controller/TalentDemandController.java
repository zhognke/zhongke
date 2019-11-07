package com.example.busniess.controller;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.TalentDemandEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessCenterService;
import com.example.busniess.service.TalentDemandService;
import com.example.busniess.utiles.ShiroUtils;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 人才需求表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-23 09:29:27
 */
@RestController
@RequestMapping("/talentDemand")
public class TalentDemandController {
    @Autowired
    private TalentDemandService talentDemandService;
    @Autowired
    BusinessCenterService businessCenterService;

    /**
     * 新增
     *
     * @param talentDemandEntity
     * @return
     */
    @SysLog(value="新增人才需求",type="人才需求")
    @PostMapping("/addTalent")
    public ReturnResult addTalent(@Validated({UserValidator.InSet.class}) TalentDemandEntity talentDemandEntity) {
        String userName = ShiroUtils.getUserName();
        if (userName == null) {
            return ReturnResult.erro(CodeMsg.NOT_HAVE_LIMITS);  //判断当前用户是否登录
        }else{
            BusinessCenter businessCenter = businessCenterService.selectMyBusinessCenter(userName);
            if(businessCenter!=null&&businessCenter.getStatue()==1){    //判断当前用户是否通过企业认证
                talentDemandEntity.setUserName(userName);
            }else{
                return ReturnResult.erro(CodeMsg.ACCESS_DENIED);
            }
        }
        if (talentDemandService.add(talentDemandEntity)) {
            return ReturnResult.success("操作成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @SysLog(value="逻辑删除人才需求",type="人才需求")
    @RequestMapping(value = "/deleteById", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ReturnResult deleteById(Integer id) {
        if (talentDemandService.delectById(id)) {
            return ReturnResult.success("删除成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 彻底删除
     *
     * @param id
     * @return
     */
    @SysLog(value="彻底删除人才需求",type="人才需求")
    @RequestMapping(value = "/realDeleteById", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ReturnResult realDeleteById(Integer id) {
        if (talentDemandService.realDeleteById(id)) {
            return ReturnResult.success("删除成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改
     *
     * @param talentDemandEntity
     * @return
     */
    @SysLog(value="修改人才需求",type="人才需求")
    @PostMapping("/update")
    public ReturnResult updateDeclaration(@Validated({UserValidator.UpDate.class}) TalentDemandEntity talentDemandEntity) {
        if (talentDemandService.update(talentDemandEntity)) {
            return ReturnResult.success("修改成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id关闭需求-企业用户
     * @param id
     * @param closeReason
     * @return
     */
    @SysLog(value="会员关闭人才需求",type="人才需求")
    @PostMapping("/closeDemands")
    public ReturnResult closeDemands(Integer id,String closeReason) {
        if (talentDemandService.closeDemands(id,closeReason)) {
            return ReturnResult.success("操作成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id关闭需求-管理员
     * @param id
     * @param closeReason
     * @return
     */
    @SysLog(value="管理员关闭人才需求",type="人才需求")
    @PostMapping("/closeDemandsForManager")
    public ReturnResult closeDemandsForManager(Integer id,String closeReason) {
        if (talentDemandService.closeDemandsForManager(id,closeReason)) {
            return ReturnResult.success("操作成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id修改状态
     * @param id
     * @param status
     * @return
     */
    @SysLog(value="修改人才需求状态",type="人才需求")
    @PostMapping("/updateStatus")
    public ReturnResult updateStatus(Integer id, Integer status,String userName) {
        if (talentDemandService.updateStatus(id, status,userName)) {
            return ReturnResult.success("修改成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id修改审批状态
     *
     * @param id
     * @param approvalStatus
     * @param approvalOpinion
     * @return
     */
    @PostMapping("/updateApprovalStatus")
    public ReturnResult updateApprovalStatus(Integer id, Integer approvalStatus, String approvalOpinion) {
        if (talentDemandService.updateApprovalStatus(id, approvalStatus, approvalOpinion)) {
            return ReturnResult.success("修改成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改审批状态-通过
     *
     * @param id
     * @return
     */
    @SysLog(value="审批人才需求-通过",type="人才需求")
    @PostMapping("/updateApprovalStatusPass")
    public ReturnResult updateApprovalStatusPass(Integer id) {
        Integer approvalStatus = 1;
        if (talentDemandService.updateApprovalStatus(id,approvalStatus, "")) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改审批状态-驳回
     *
     * @param approvalOpinion 审批意见
     * @param id
     * @return
     */
    @SysLog(value="审批人才需求-驳回",type="人才需求")
    @PostMapping("/updateApprovalStatusRejected")
    public ReturnResult updateApprovalStatusRejected(Integer id, String approvalOpinion) {
        Integer approvalStatus = 2;
        if (talentDemandService.updateApprovalStatus(id,approvalStatus, approvalOpinion)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 分页展示,可根据条件筛选
     *
     * @param talentDemandEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/showByPage", method = RequestMethod.GET)
    public ReturnResult showByPage(TalentDemandEntity talentDemandEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        talentDemandEntity.setStatus(0);
        talentDemandEntity.setApprovalStatus(1);
        PageInfo pageInfo = talentDemandService.showByPage(talentDemandEntity, pageNum, pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 分页展示,可根据条件筛选
     *
     * @param talentDemandEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/showByPageForCenter", method = RequestMethod.GET)
    public ReturnResult showByPageForCenter(TalentDemandEntity talentDemandEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        String userName = ShiroUtils.getUserName();
        if (ShiroUtils.isLogin()) {
            talentDemandEntity.setUserName(userName);
        }else{
            return ReturnResult.erro(CodeMsg.NOT_HAVE_LIMITS);
        }
        PageInfo pageInfo = talentDemandService.showByPage(talentDemandEntity, pageNum, pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id搜索
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public ReturnResult getById(Integer id) {
        if (id == null) {
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        } else {
            TalentDemandEntity obj = talentDemandService.selectById(id);
            if (obj != null) {
                return ReturnResult.success(obj);
            } else {
                return ReturnResult.erro(CodeMsg.DATA_EMPTY);
            }
        }
    }
}
