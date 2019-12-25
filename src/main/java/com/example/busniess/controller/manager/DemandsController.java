package com.example.busniess.controller.manager;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.entity.DemandsEntity;
import com.example.busniess.entity.InformEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.DemandsService;
import com.example.busniess.utiles.RabbitUtil;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * 企业需求表--管理员
 * 后台和管理员操作接口
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-24 10:05:26
 */
@RestController("demands")
@RequestMapping("/manager/demands")
public class DemandsController {
    @Autowired
    private DemandsService demandsService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 新增需求
     *
     * @param demandsEntity    实体类
     * @return ReturnResult
     */
    @SysLog(value="新增企业需求",type="企业需求")
    @PostMapping("/addDemands")
    public ReturnResult addDemands(@Validated({UserValidator.InSet.class}) DemandsEntity demandsEntity) {
        demandsEntity.setApprovalStatus(1);
        if (demandsService.insert(demandsEntity)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 后台管理-分页展示,可根据条件筛选(管理端)
     *
     * @param demandsEntity    实体类
     * @param pageNum    页码
     * @param pageSize    页面尺寸
     * @return ReturnResult
     */
    @RequestMapping(value="/showByPageForManager",method = RequestMethod.GET)
    public ReturnResult showByPageForManager(DemandsEntity demandsEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageInfo pageInfo = demandsService.showByPage(demandsEntity, pageNum, pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id获取需求详情
     * @param id    主键id 需求id
     * @return ReturnResult
     */
    @RequestMapping(value = "/getDemandsByID", method = {RequestMethod.GET})
    public ReturnResult getDemandsByID(@NotNull(message = "参数不能为空") Integer id) {
        DemandsEntity demandsEntity = demandsService.getByID(id);
        if (demandsEntity != null) {
            return ReturnResult.success(demandsEntity);
        } else {
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }

    /**
     * 根据id删除需求
     *
     * @param id    主键id
     * @return ReturnResult
     */
    @SysLog(value="删除企业需求",type="企业需求")
    @RequestMapping(value = "/delDemands", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ReturnResult delDemands(@NotNull(message = "参数不能为空") Integer id) {
        if (demandsService.deleteDemandsByID(id)) {
            //通知
            DemandsEntity entity = demandsService.getByID(id);
            InformEntity informEntity = RabbitUtil.sendRabbic(entity.getUserName(), "提交的" + entity.getDemandOutline() + "的企业需求已被管理员删除", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 批量删除
     * @param ids   主键ids(多个id用英文逗号分隔)
     * @return ReturnResult
     */
    @SysLog(value="批量删除需求",type="企业需求")
    @RequestMapping(value="/deleteByBatch",method = {RequestMethod.DELETE,RequestMethod.POST})
    public ReturnResult deleteByBatch(@NotNull(message = "参数不能为空")String ids){
        if(demandsService.deleteBatch(ids)){
            return ReturnResult.success("删除成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id删除需求
     *
     * @param id    主键id
     * @return ReturnResult
     */
    //@SysLog(value="彻底删除企业需求",type="企业需求")
    //@RequestMapping(value = "/realDelDemands", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ReturnResult realDelDemands(@NotNull(message = "参数不能为空") Integer id) {
        if (demandsService.realDeleteDemandsByID(id)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求
     *
     * @param demandsEntity    实体类
     * @return ReturnResult
     */
    @SysLog(value="修改企业需求",type="企业需求")
    @RequestMapping(value = "/updateDemands", method = {RequestMethod.POST})
    public ReturnResult updateDemands(@Validated({UserValidator.UpDate.class}) DemandsEntity demandsEntity) {
        demandsEntity.setApprovalStatus(0);
        if (demandsService.updateDemands(demandsEntity)) {
            //通知
            InformEntity informEntity= RabbitUtil.sendRabbic(demandsEntity.getUserName(),"修改了" + demandsEntity.getDemandOutline() + "的企业需求",new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 关闭需求--管理端
     *
     * @param id    主键id
     * @return ReturnResult
     */
    @SysLog(value="关闭企业需求",type="企业需求")
    @PostMapping("/closeDemandsByIdForManager")
    public ReturnResult closeDemandsByIdForManager(@NotNull(message = "参数不能为空") Integer id,String reason) {
        Integer status = 2;
        if (demandsService.updateDemandsStatus(id,status,reason)) {
            //通知
            DemandsEntity entity = demandsService.getByID(id);
            InformEntity informEntity = RabbitUtil.sendRabbic(entity.getUserName(), "提交的" + entity.getDemandOutline() + "的企业需求已被管理员关闭", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求审批状态-通过
     *
     * @param id    主键id
     * @return ReturnResult
     */
    @SysLog(value="需求审批通过",type="企业需求")
    @PostMapping("/updateApprovalStatusPass")
    public ReturnResult updateApprovalStatusPass(@NotNull(message = "参数不能为空") Integer id) {
        int approvalStatus = 1;
        if (demandsService.updateDemandsApprovalStatus(approvalStatus, "", id)) {
            //通知
            DemandsEntity entity = demandsService.getByID(id);
            InformEntity informEntity = RabbitUtil.sendRabbic(entity.getUserName(), "提交的" + entity.getDemandOutline() + "的企业需求已经审核通过", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求审批状态-驳回
     *
     * @param approvalOpinion 审批意见
     * @param id    主键id
     * @return ReturnResult
     */
    @SysLog(value="需求审批驳回",type="企业需求")
    @PostMapping("/updateApprovalStatusRejected")
    public ReturnResult updateApprovalStatusRejected(@NotNull(message = "参数不能为空") Integer id, String approvalOpinion) {
        int approvalStatus = 2;
        if (demandsService.updateDemandsApprovalStatus(approvalStatus, approvalOpinion, id)) {
            //通知
            DemandsEntity entity = demandsService.getByID(id);
            InformEntity informEntity = RabbitUtil.sendRabbic(entity.getUserName(), "提交的" + entity.getDemandOutline() + "的企业需求被驳回了", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

}
