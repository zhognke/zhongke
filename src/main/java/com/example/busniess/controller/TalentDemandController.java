package com.example.busniess.controller;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.entity.InformEntity;
import com.example.busniess.entity.TalentDemandEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessCenterService;
import com.example.busniess.service.TalentDemandService;
import com.example.busniess.utiles.RabbitUtil;
import com.example.busniess.utiles.RedisKey;
import com.example.busniess.utiles.RedisUtil;
import com.example.busniess.utiles.ShiroUtils;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;


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
    @Autowired
    RedisUtil redisUtil;

    /**
     * 新增
     *
     * @param talentDemandEntity    实体类
     * @return  ReturnResult
     */
    @SysLog(value = "新增人才需求", type = "人才需求")
    @PostMapping("/addTalent")
    public ReturnResult addTalent(@Validated({UserValidator.InSet.class}) TalentDemandEntity talentDemandEntity) {
        //String userName = talentDemandEntity.getUserName();
        //String userName = ShiroUtils.getUserName();
        /*if (userName == null) {
            return ReturnResult.erro(CodeMsg.NOT_HAVE_LIMITS);  //判断当前用户是否登录
        }else{
            BusinessCenter businessCenter = businessCenterService.selectMyBusinessCenter(userName);
            if(businessCenter!=null&&businessCenter.getStatue()==1){    //判断当前用户是否通过企业认证
                talentDemandEntity.setUserName(userName);
            }else{
                return ReturnResult.erro(CodeMsg.ACCESS_DENIED);
            }
        }*/
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
     * 彻底删除
     *
     * @param id    主键id
     * @return  ReturnResult
     */
    @SysLog(value = "彻底删除人才需求", type = "人才需求")
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
     * 根据id关闭需求-企业用户
     *
     * @param id    主键id
     * @param closeReason   关闭原因
     * @return  ReturnResult
     */
    @SysLog(value = "会员关闭人才需求", type = "人才需求")
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
     * 根据id关闭需求-管理员
     *
     * @param id    主键id
     * @param closeReason   关闭原因
     * @return  ReturnResult
     */
    @SysLog(value = "管理员关闭人才需求", type = "人才需求")
    @PostMapping("/closeDemandsForManager")
    public ReturnResult closeDemandsForManager(Integer id, String closeReason) {
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
     * 根据id修改状态
     *
     * @param id    主键id
     * @param status    状态
     * @return  ReturnResult
     */
    @SysLog(value = "修改人才需求状态", type = "人才需求")
    @PostMapping("/updateStatus")
    public ReturnResult updateStatus(Integer id, Integer status, String userName) {
        if (talentDemandService.updateStatus(id, status, userName)) {
            return ReturnResult.success("修改成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id修改审批状态
     *
     * @param id    主键id
     * @param approvalStatus    审批状态
     * @param approvalOpinion   审批意见
     * @return  ReturnResult
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
     * 分页展示,可根据条件筛选
     *
     * @param talentDemandEntity 人才实体类
     * @param pageNum   当前页码
     * @param pageSize  分页尺寸
     * @return  ReturnResult
     */
    @RequestMapping(value = "/showByPage", method = RequestMethod.GET)
    public ReturnResult showByPage(TalentDemandEntity talentDemandEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
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
    @Cacheable(key="'talent'+#id",value="talent")
    @RequestMapping(value = "showById", method = RequestMethod.GET)
    public ReturnResult showById(Integer id, @RequestParam(defaultValue = "5") Integer size) {
        if (id == null) {
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        } else {
            TalentDemandEntity obj = talentDemandService.selectById(id, size);
            if (obj != null) {
                //记录浏览量到redis,然后定时更新到数据库
                String key = RedisKey.TALENT_VIEW_COUNT_CODE + obj.getId();
                //找到redis中该篇文章的点赞数，如果不存在则向redis中添加一条
                Map<Object, Object> viewCountItem = redisUtil.hmget(RedisKey.TALENT_VIEW_COUNT_KEY);
                Integer viewCount = obj.getViewCount();
                if (!viewCountItem.isEmpty()) {
                    if (viewCountItem.containsKey(key)) {
                        viewCount = (Integer) viewCountItem.get(key);
                        obj.setViewCount(viewCount);
                    }
                }
                redisUtil.hset(RedisKey.TALENT_VIEW_COUNT_KEY, key, ++viewCount);
                return ReturnResult.success(obj);
            } else {
                return ReturnResult.erro(CodeMsg.DATA_EMPTY);
            }
        }
    }
}
