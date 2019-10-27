package com.example.busniess.controller;

import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.TalentDemandEntity;
import com.example.busniess.entity.User;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.TalentDemandService;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


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

    /**
     * 新增
     *
     * @param talentDemandEntity
     * @return
     */
    @PostMapping("/add")
    public ReturnResult addDeclaration(@Validated({UserValidator.InSet.class}) TalentDemandEntity talentDemandEntity) {
        talentDemandEntity.setCreateTime(new Date());
        if (talentDemandService.add(talentDemandEntity)) {
            return ReturnResult.success("添加成功");
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
    @PostMapping("/update")
    public ReturnResult updateDeclaration(@Validated({UserValidator.UpDate.class}) TalentDemandEntity talentDemandEntity) {
        if (talentDemandService.update(talentDemandEntity)) {
            return ReturnResult.success("修改成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id修改状态
     *
     * @param id
     * @param status
     * @return
     */
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
        if (pageNum == null || pageSize == null) {
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        }
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
        String userName = talentDemandEntity.getUserName();
        if(userName!=null){

        }else{
            Object obj = SecurityUtils.getSubject().getPrincipal();
            if(obj!=null){
                userName = ((User)obj).getUserName();
                talentDemandEntity.setUserName(userName);
            }else{
                return ReturnResult.erro(CodeMsg.BIND_ERROR);
            }
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
