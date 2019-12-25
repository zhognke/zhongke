package com.example.busniess.controller.manager;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.entity.InformEntity;
import com.example.busniess.entity.TalentDemandEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessCenterService;
import com.example.busniess.service.TalentDemandService;
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
 * 人才需求表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-23 09:29:27
 */
@RestController("talentDemand")
@RequestMapping("/manager/talentDemand")
public class TalentDemandController {

    @Autowired
    private TalentDemandService talentDemandService;
    @Autowired
    BusinessCenterService businessCenterService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 新增
     *
     * @param talentDemandEntity    实体类
     * @return  ReturnResult
     */
    @SysLog(value = "新增人才需求", type = "人才需求")
    @PostMapping("/addTalent")
    public ReturnResult addTalent(@Validated({UserValidator.InSet.class}) TalentDemandEntity talentDemandEntity) {
        talentDemandEntity.setApprovalStatus(1);
        if (talentDemandService.add(talentDemandEntity)) {
            //通知
            InformEntity informEntity = RabbitUtil.sendRabbic(talentDemandEntity.getUserName(), "提交了" + talentDemandEntity.getTitle() + "的人才需求", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);
            return ReturnResult.success("操作成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 逻辑删除
     *
     * @param id    主键id
     * @return  ReturnResult
     */
    @SysLog(value = "逻辑删除人才需求", type = "人才需求")
    @RequestMapping(value = "/deleteById", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ReturnResult deleteById(Integer id) {
        if (talentDemandService.delectById(id)) {
            return ReturnResult.success("删除成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 批量删除
     *
     * @param ids   id(英文逗号分隔)
     * @return  ReturnResult
     */
    @SysLog(value = "批量删除人才需求", type = "人才需求")
    @RequestMapping(value = "/deleteByBatch", method = {RequestMethod.DELETE, RequestMethod.POST})
    public ReturnResult deleteByBatch(@NotNull(message = "参数不能为空") String ids) {
        if (talentDemandService.deleteBatch(ids)) {
            return ReturnResult.success("删除成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改
     *
     * @param talentDemandEntity    实体类
     * @return  ReturnResult
     */
    @SysLog(value = "修改人才需求", type = "人才需求")
    @PostMapping("/update")
    public ReturnResult updateById(@Validated({UserValidator.UpDate.class}) TalentDemandEntity talentDemandEntity) {
        if (talentDemandService.update(talentDemandEntity)) {
            //通知
            InformEntity informEntity = RabbitUtil.sendRabbic(talentDemandEntity.getUserName(), "修改了" + talentDemandEntity.getTitle() + "的人才需求", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);
            return ReturnResult.success("修改成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id关闭需求-管理员
     *
     * @param id    主键id
     * @param closeReason   关闭原因
     * @return  ReturnResult
     */
    @SysLog(value = "关闭人才需求", type = "人才需求")
    @PostMapping("/closeDemands")
    public ReturnResult closeDemands(Integer id, String closeReason) {
        if (talentDemandService.closeDemandsForManager(id, closeReason)) {
            //通知
            TalentDemandEntity entity = talentDemandService.selectById(id);
            InformEntity informEntity = RabbitUtil.sendRabbic(entity.getUserName(), "提交的" + entity.getTitle() + "的人才需求已被管理员关闭", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);
            return ReturnResult.success("操作成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改审批状态-通过
     *
     * @param id    主键id
     * @return  ReturnResult
     */
    @SysLog(value = "审批通过", type = "人才需求")
    @PostMapping("/updateApprovalStatusPass")
    public ReturnResult updateApprovalStatusPass(Integer id) {
        Integer approvalStatus = 1;
        if (talentDemandService.updateApprovalStatus(id, approvalStatus, "")) {
            //通知
            TalentDemandEntity entity = talentDemandService.selectById(id);
            InformEntity informEntity = RabbitUtil.sendRabbic(entity.getUserName(), "提交的" + entity.getTitle() + "的人才需求已经审核通过", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改审批状态-驳回
     *
     * @param approvalOpinion 审批意见
     * @param id    主键id
     * @return  ReturnResult
     */
    @SysLog(value = "审批驳回", type = "人才需求")
    @PostMapping("/updateApprovalStatusRejected")
    public ReturnResult updateApprovalStatusRejected(Integer id, String approvalOpinion) {
        Integer approvalStatus = 2;
        if (talentDemandService.updateApprovalStatus(id, approvalStatus, approvalOpinion)) {
            //通知
            TalentDemandEntity entity = talentDemandService.selectById(id);
            InformEntity informEntity = RabbitUtil.sendRabbic(entity.getUserName(), "提交的" + entity.getTitle() + "的人才需求被驳回了", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改推荐状态
     *
     * @param isHot 推荐状态
     * @param id    主键id
     * @return  ReturnResult
     */
    @SysLog(value = "修改推荐状态", type = "人才需求")
    @PostMapping("/updateHot")
    public ReturnResult updateHot(Integer id, Integer isHot) {
        if (talentDemandService.updateHot(id, isHot)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 分页展示,可根据条件筛选
     *
     * @param talentDemandEntity 人才实体类
     * @param pageNum   当前页码
     * @param pageSize  分页尺寸
     * @return  ReturnResult
     */
    @RequestMapping(value = "/showByPage", method = RequestMethod.GET)
    public ReturnResult showByPage(TalentDemandEntity talentDemandEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageInfo pageInfo = talentDemandService.showByPage(talentDemandEntity, pageNum, pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id搜索
     *
     * @param id 主键id
     * @return ReturnResult
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
