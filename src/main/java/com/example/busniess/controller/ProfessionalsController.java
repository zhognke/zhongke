package com.example.busniess.controller;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.entity.InformEntity;
import com.example.busniess.entity.ProfessionalsEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessCenterService;
import com.example.busniess.service.ProfessionalsService;
import com.example.busniess.utiles.RabbitUtil;
import com.example.busniess.utiles.RedisUtil;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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
    ProfessionalsService professionalsService;
    @Autowired
    BusinessCenterService businessCenterService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RedisUtil redisUtil;

    /**
     * 新增
     * @param professionalsEntity    实体类   实体类
     * @return  ReturnResult
     */
    @SysLog(value="新增专家入驻",type="专家入驻")
    @PostMapping("/addProfessionals")
    public ReturnResult addProfessionals(@Validated({UserValidator.InSet.class}) ProfessionalsEntity professionalsEntity){
        //String userName = professionalsEntity.getUserName();
        //String userName = ShiroUtils.getUserName();
        /*if (userName == null) {
            return ReturnResult.erro(CodeMsg.NOT_HAVE_LIMITS);  //判断当前用户是否登录
        }else{
            BusinessCenter businessCenter = businessCenterService.selectMyBusinessCenter(userName);
            if(businessCenter!=null&&businessCenter.getStatue()==1){    //判断当前用户是否通过企业认证
                professionalsEntity.setUserName(userName);
            }else{
                return ReturnResult.erro(CodeMsg.ACCESS_DENIED);
            }
        }*/
        professionalsEntity.setCreateTime(new Date());
        if(professionalsService.add(professionalsEntity)){
            //通知
            InformEntity informEntity= RabbitUtil.sendRabbic(professionalsEntity.getUserName(),"提交了" + professionalsEntity.getRealName() + "的专家入驻信息",new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);
            return ReturnResult.success("添加成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 逻辑删除
     * @param id    主键id
     * @return  ReturnResult
     */
    @SysLog(value="逻辑删除",type="专家入驻")
    @RequestMapping(value="/deleteById",method = {RequestMethod.DELETE,RequestMethod.POST})
    public ReturnResult deleteById(@NotNull(message = "参数不能为空")Integer id){
        if(professionalsService.deleteById(id)){
            return ReturnResult.success("删除成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }
    /**
     * 批量删除
     * @param ids    主键ids
     * @return  ReturnResult
     */
    @SysLog(value="批量删除",type="专家入驻")
    @RequestMapping(value="/deleteByBatch",method = {RequestMethod.DELETE,RequestMethod.POST})
    public ReturnResult deleteByBatch(@NotNull(message = "参数不能为空")String ids){
        if(professionalsService.deleteBatch(ids)){
            return ReturnResult.success("删除成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 彻底删除
     * @param id    主键id
     * @return  ReturnResult
     */
    @SysLog(value="彻底删除",type="专家入驻")
    @RequestMapping(value="/realDeleteById",method = {RequestMethod.DELETE,RequestMethod.GET})
    public ReturnResult realDeleteById(@NotNull(message = "参数不能为空")Integer id){
        if(professionalsService.realDeleteById(id)){
            return ReturnResult.success("删除成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改
     * @param professionalsEntity    实体类
     * @return  ReturnResult
     */
    @SysLog(value="修改信息",type="专家入驻")
    @PostMapping("/updateById")
    public ReturnResult updateDeclaration(@Validated({UserValidator.UpDate.class}) ProfessionalsEntity professionalsEntity){
        if(professionalsService.update(professionalsEntity)){
            //通知
            InformEntity informEntity= RabbitUtil.sendRabbic(professionalsEntity.getUserName(),"修改了" + professionalsEntity.getRealName() + "的专家入驻信息",new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);
            return ReturnResult.success("修改成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id修改状态
     * @param id    主键id
     * @param status    状态
     * @return  ReturnResult
     */
    @SysLog(value="修改状态",type="专家入驻")
    @PostMapping("/updateStatus")
    public ReturnResult updateStatus(@NotNull(message = "参数不能为空")Integer id,@NotNull(message = "状态不能为空")Integer status){
        if(professionalsService.updateStatus(id,status)){
            return ReturnResult.success("修改成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 关闭专家信息-用户
     * @param id    主键id
     * @param closeReason   关闭原因
     * @return  ReturnResult
     */
    @SysLog(value="关闭信息-用户",type="专家入驻")
    @PostMapping("/closeById")
    public ReturnResult closeById(@NotNull(message = "参数不能为空")Integer id,@NotNull(message = "关闭原因不能为空")String closeReason){
        if(professionalsService.closeById(id,closeReason)){
            //通知
            ProfessionalsEntity professionalsEntity = professionalsService.selectById(id);
            InformEntity informEntity= RabbitUtil.sendRabbic(professionalsEntity.getUserName(),"关闭了" + professionalsEntity.getRealName() + "的专家入驻信息",new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);
            return ReturnResult.success("修改成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 关闭专家入驻状态
     * @param id    主键id
     * @param closeReason   关闭原因
     * @return  ReturnResult
     */
    @SysLog(value="关闭信息-管理员",type="专家入驻")
    @PostMapping("/closeByIdForManager")
    public ReturnResult closeByIdForManager(@NotNull(message = "参数不能为空")Integer id,@NotNull(message = "关闭原因不能为空")String closeReason){
        if(professionalsService.closeByIdForManager(id,closeReason)){
            //通知
            ProfessionalsEntity entity = professionalsService.selectById(id);
            InformEntity informEntity = RabbitUtil.sendRabbic(entity.getUserName(), "提交的" + entity.getRealName() + "的专家入驻信息已被管理员关闭", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);
            return ReturnResult.success("修改成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id修改审批状态
     * @param id    主键id
     * @param approvalStatus    审批状态
     * @param approvalOpinion   审批意见
     * @return  ReturnResult
     */
    @SysLog(value="审批信息",type="专家入驻")
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
     * @param id    主键id
     * @return  ReturnResult
     */
    @SysLog(value="审批通过",type="专家入驻")
    @PostMapping("/updateApprovalStatusPass")
    public ReturnResult updateApprovalStatusPass(@NotNull(message = "参数不能为空")Integer id) {
        Integer approvalStatus = 1;
        if (professionalsService.updateApprovalStatus(id,approvalStatus, "")) {
            //通知
            ProfessionalsEntity entity = professionalsService.selectById(id);
            InformEntity informEntity = RabbitUtil.sendRabbic(entity.getUserName(), "提交的" + entity.getRealName() + "的专家入驻信息已经审核通过", new Date());
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
    @SysLog(value="审批驳回",type="专家入驻")
    @PostMapping("/updateApprovalStatusRejected")
    public ReturnResult updateApprovalStatusRejected(@NotNull(message = "参数不能为空")Integer id, @NotNull(message = "驳回原因不能为空")String approvalOpinion) {
        Integer approvalStatus = 2;
        if (professionalsService.updateApprovalStatus(id,approvalStatus, approvalOpinion)) {
            //通知
            ProfessionalsEntity entity = professionalsService.selectById(id);
            InformEntity informEntity = RabbitUtil.sendRabbic(entity.getUserName(), "提交的" + entity.getRealName() + "的专家入驻信息被驳回", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 分页展示,可根据条件筛选
     * @param professionalsEntity    实体类
     * @param pageNum   页码
     * @param pageSize  页面尺寸
     * @return  ReturnResult
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
     * @param id    主键id
     * @return  ReturnResult
     */
    @RequestMapping(value="getById",method = RequestMethod.GET)
    public ReturnResult getById(Integer id){
        if(id==null){
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        }else{
            ProfessionalsEntity obj = professionalsService.selectById(id);
            if(obj!=null){
                return ReturnResult.success(obj);
            }else{
                return ReturnResult.erro(CodeMsg.DATA_EMPTY);
            }
        }
    }

    /**
     * 根据id搜索
     * @param id    主键id
     * @return  ReturnResult
     */
    @RequestMapping(value="showById",method = RequestMethod.GET)
    public ReturnResult showById(Integer id,@RequestParam(defaultValue = "5") Integer size){
        if(id==null){
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        }else{
            ProfessionalsEntity obj = professionalsService.selectById(id,size);
            if(null != obj){
                return ReturnResult.success(obj);
            }else{
                return ReturnResult.erro(CodeMsg.DATA_EMPTY);
            }
        }
    }

    /**
     * 热门数据
     * @param pageNum   当期页码
     * @param pageSize  页面尺寸
     * @return  ReturnResult
     */
    @RequestMapping(value="showHot",method = RequestMethod.GET)
    public ReturnResult showHot(@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "5") Integer pageSize){
        PageInfo pageInfo = professionalsService.showHot(pageNum,pageSize);
        return ReturnResult.success(pageInfo);
    }
}
