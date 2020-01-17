package com.example.busniess.controller;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.entity.InformEntity;
import com.example.busniess.entity.TalentDemandEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessCenterService;
import com.example.busniess.service.TalentDemandService;
import com.example.busniess.utiles.EchartsEntity;
import com.example.busniess.utiles.RabbitUtil;
import com.example.busniess.utiles.ShiroUtils;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;


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
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 新增
     *
     * @param talentDemandEntity    实体类
     * @return  ReturnResult
     */
    @PostMapping("/addTalent")
    public ReturnResult addTalent(@Validated({UserValidator.InSet.class}) TalentDemandEntity talentDemandEntity) {
        talentDemandEntity.setApprovalStatus(0);
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
     * 根据id关闭需求-企业用户
     *
     * @param id    主键id
     * @param closeReason   关闭原因
     * @return  ReturnResult
     */
    @PostMapping("/closeDemands")
    public ReturnResult closeDemands(Integer id, String closeReason) {
        if (talentDemandService.closeDemands(id, closeReason)) {
            //通知
            TalentDemandEntity talentDemandEntity = talentDemandService.selectById(id);
            InformEntity informEntity = RabbitUtil.sendRabbic(talentDemandEntity.getUserName(), "关闭了" + talentDemandEntity.getTitle() + "的人才需求", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);
            return ReturnResult.success("操作成功");
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
    public ReturnResult showByPage(TalentDemandEntity talentDemandEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        talentDemandEntity.setStatus(0);    //只能查看正常状态的
        talentDemandEntity.setApprovalStatus(1);    //只能查看审批通过的
        PageInfo pageInfo = talentDemandService.showByPage(talentDemandEntity, pageNum, pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 分页展示,可根据条件筛选
     *
     * @param talentDemandEntity 人才实体类
     * @param pageNum   当前页码
     * @param pageSize  分页尺寸
     * @return  ReturnResult
     */
    @RequestMapping(value = "/showByPageForCenter", method = RequestMethod.GET)
    public ReturnResult showByPageForCenter(TalentDemandEntity talentDemandEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        String userName = ShiroUtils.getUserName();
        if (ShiroUtils.isLogin()) {
            talentDemandEntity.setUserName(userName);   //只能查看自己的信息
        } else {
            return ReturnResult.erro(CodeMsg.NOT_HAVE_LIMITS);
        }
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

    /**
     * 根据id显示详情
     *
     * @param id 主键id
     * @return ReturnResult
     */
    @RequestMapping(value = "showById", method = RequestMethod.GET)
    public ReturnResult showById(Integer id, @RequestParam(defaultValue = "5") Integer size) {
        if (id == null) {
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        } else {
            TalentDemandEntity obj = talentDemandService.selectById(id, size);
            if (obj != null) {
                return ReturnResult.success(obj);
            } else {
                return ReturnResult.erro(CodeMsg.DATA_EMPTY);
            }
        }
    }

    /**
     * 人才需求行业占比统计(饼图)
     *
     * @return ReturnResult data.ldata legend数据,即legend.data需要的数据;data.sdata 对应x轴的数据,即series[0].data需要的数据
     */
    @RequestMapping(value = "/demandsIndustryProp", method = {RequestMethod.POST, RequestMethod.GET})
    public ReturnResult demandsIndustryProp(@RequestParam(defaultValue = "16")Integer size) {
        Map<String,Object> map = talentDemandService.demandsIndustryProp(size);
        if(map!=null){
            return ReturnResult.success(map);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }
}
