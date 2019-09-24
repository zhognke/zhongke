package com.example.busniess.controller;

import com.example.busniess.entity.DemandsEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.DemandsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * 企业需求表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-24 10:05:26
 */
@Validated
@RestController
@RequestMapping("/demands")
public class DemandsController {
    @Autowired
    private DemandsService demandsService;

    /**
     * 分页展示,可根据条件筛选(企业端)
     * @param demandsEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/showByPage")
    public ReturnResult showByPage(DemandsEntity demandsEntity,int pageNum,int pageSize){
        PageInfo pageInfo = demandsService.showByPage(demandsEntity,pageNum,pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id获取需求详情
     * @param id    需求id
     * @return
     */
    @RequestMapping("/getDemandsByID")
    public ReturnResult getDemandsByID(int id){
        DemandsEntity demandsEntity = demandsService.getByID(id);
        return ReturnResult.success(demandsEntity);
    }
    /**
     * 分页展示,可根据条件筛选(管理端端)
     * @param demandsEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/showByPageForManager")
    public ReturnResult showByPageForManager(DemandsEntity demandsEntity,int pageNum,int pageSize){
        PageInfo pageInfo = demandsService.showByPage(demandsEntity,pageNum,pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 新增需求
     * @param demandsEntity
     * @return
     */
    @PostMapping("/addDemands")
    public ReturnResult addDemands(DemandsEntity demandsEntity){
        if(demandsEntity.getCreateTime()==null){
            demandsEntity.setCreateTime(new Date());
        }
        if(demandsEntity.getUpdateTime()==null){
            demandsEntity.setUpdateTime(new Date());
        }
        if(demandsEntity.getStatus()==null){
            demandsEntity.setStatus(0);
        }
        if(demandsEntity.getApprovalStatus()==null){
            demandsEntity.setApprovalStatus(0);
        }
        if(demandsService.insert(demandsEntity)){
            return ReturnResult.success();
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id删除需求
     * @param id
     * @return
     */
    @RequestMapping(value="delDemands",method = {RequestMethod.DELETE,RequestMethod.GET})
    public ReturnResult delDemands(int id){
        if(demandsService.deleteDemandsByID(id)){
            return ReturnResult.success();
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求
     * @param demandsEntity
     * @return
     */
    @RequestMapping(value="updateDemands",method = {RequestMethod.POST})
    public ReturnResult updateDemands(DemandsEntity demandsEntity){
        if(demandsService.updateDemands(demandsEntity)){
            return ReturnResult.success();
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求有效状态
     * @param status
     * @param id
     * @return
     */
    @PostMapping("updateDemandsStatus")
    public ReturnResult updateDemandsStatus(int status,int id){
        if(demandsService.updateDemandsStatus(status,id)){
            return ReturnResult.success();
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求审批状态
     * @param approvalStatus
     * @param id
     * @return
     */
    @PostMapping("updateDemandsApprovalStatus")
    public ReturnResult updateDemandsApprovalStatus(int approvalStatus,int id){
        if(demandsService.updateDemandsApprovalStatus(approvalStatus,id)){
            return ReturnResult.success();
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }
}
