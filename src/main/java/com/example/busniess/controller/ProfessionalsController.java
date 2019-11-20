package com.example.busniess.controller;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.ProfessionalsEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessCenterService;
import com.example.busniess.service.ProfessionalsService;
import com.example.busniess.utiles.ShiroUtils;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * 专家信息表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-24 11:42:14
 */
@Validated
@RestController
@RequestMapping("/professionals")
public class ProfessionalsController {
    @Autowired
    private ProfessionalsService professionalsService;
    @Autowired
    BusinessCenterService businessCenterService;

    /**
     * 新增
     * @param professionalsEntity
     * @return
     */
    @SysLog(value="新增专家入驻",type="专家入驻")
    @PostMapping("/addProfessionals")
    public ReturnResult addProfessionals(@Validated({UserValidator.InSet.class}) ProfessionalsEntity professionalsEntity){
        String userName = ShiroUtils.getUserName();
        if (userName == null) {
            return ReturnResult.erro(CodeMsg.NOT_HAVE_LIMITS);  //判断当前用户是否登录
        }else{
            BusinessCenter businessCenter = businessCenterService.selectMyBusinessCenter(userName);
            if(businessCenter!=null&&businessCenter.getStatue()==1){    //判断当前用户是否通过企业认证
                professionalsEntity.setUserName(userName);
            }else{
                return ReturnResult.erro(CodeMsg.ACCESS_DENIED);
            }
        }
        professionalsEntity.setCreateTime(new Date());
        if(professionalsService.add(professionalsEntity)){
            return ReturnResult.success("添加成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @SysLog(value="逻辑删除专家入驻信息",type="专家入驻")
    @RequestMapping(value="/deleteById",method = {RequestMethod.DELETE,RequestMethod.GET})
    public ReturnResult deleteById(Integer id){
        if(professionalsService.deleteById(id)){
            return ReturnResult.success("删除成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 彻底删除
     * @param id
     * @return
     */
    @SysLog(value="彻底删除专家入驻信息",type="专家入驻")
    @RequestMapping(value="/realDeleteById",method = {RequestMethod.DELETE,RequestMethod.GET})
    public ReturnResult realDeleteById(Integer id){
        if(professionalsService.realDeleteById(id)){
            return ReturnResult.success("删除成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改
     * @param professionalsEntity
     * @return
     */
    @SysLog(value="修改专家入驻信息",type="专家入驻")
    @PostMapping("/updateById")
    public ReturnResult updateDeclaration(@Validated({UserValidator.UpDate.class}) ProfessionalsEntity professionalsEntity){
        if(professionalsService.update(professionalsEntity)){
            return ReturnResult.success("修改成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id修改状态
     * @param id
     * @param status
     * @return
     */
    @SysLog(value="修改专家入驻状态",type="专家入驻")
    @PostMapping("/updateStatus")
    public ReturnResult updateStatus(Integer id,Integer status){
        if(professionalsService.updateStatus(id,status)){
            return ReturnResult.success("修改成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 关闭专家入驻状态
     * @param id
     * @param closeReason
     * @return
     */
    @SysLog(value="关闭专家入驻状态",type="专家入驻")
    @PostMapping("/closeById")
    public ReturnResult closeById(Integer id,String closeReason){
        if(professionalsService.closeById(id,closeReason)){
            return ReturnResult.success("修改成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id修改审批状态
     * @param id
     * @param approvalStatus
     * @param approvalOpinion
     * @return
     */
    @PostMapping("/updateApprovalStatus")
    public ReturnResult updateApprovalStatus(Integer id,Integer approvalStatus,String approvalOpinion){
        if(professionalsService.updateApprovalStatus(id,approvalStatus,approvalOpinion)){
            return ReturnResult.success("修改成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }
    /**
     * 修改审批状态-通过
     *
     * @param id
     * @return
     */
    @SysLog(value="修改专家入驻信息",type="专家入驻")
    @PostMapping("/updateApprovalStatusPass")
    public ReturnResult updateApprovalStatusPass(Integer id) {
        Integer approvalStatus = 1;
        if (professionalsService.updateApprovalStatus(id,approvalStatus, "")) {
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
        if (professionalsService.updateApprovalStatus(id,approvalStatus, approvalOpinion)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 分页展示,可根据条件筛选
     * @param professionalsEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/showByPage",method = RequestMethod.GET)
    public ReturnResult showByPage(ProfessionalsEntity professionalsEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize){
        if(pageNum==null||pageSize==null){
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        }
        PageInfo pageInfo = professionalsService.showByPage(professionalsEntity,pageNum,pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id搜索
     * @param id
     * @return
     */
    @RequestMapping(value="getById",method = RequestMethod.GET)
    public ReturnResult getById(Integer id,@RequestParam(defaultValue = "5") Integer size){
        if(id==null){
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        }else{
            ProfessionalsEntity obj = professionalsService.selectById(id,size);
            if(obj!=null){
                return ReturnResult.success(obj);
            }else{
                return ReturnResult.erro(CodeMsg.DATA_EMPTY);
            }
        }
    }
}
