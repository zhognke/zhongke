package com.example.busniess.controller;

import com.example.busniess.entity.InformEntity;
import com.example.busniess.entity.ProfessionalsEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessCenterService;
import com.example.busniess.service.ProfessionalsService;
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

    /**
     * 新增
     * @param professionalsEntity    实体类   实体类
     * @return  ReturnResult
     */
    @PostMapping("/addProfessionals")
    public ReturnResult addProfessionals(@Validated({UserValidator.InSet.class}) ProfessionalsEntity professionalsEntity){
        professionalsEntity.setApprovalStatus(0);
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
    @RequestMapping(value="/deleteById",method = {RequestMethod.DELETE,RequestMethod.POST})
    public ReturnResult deleteById(@NotNull(message = "参数不能为空")Integer id){
        if(professionalsService.deleteById(id)){
            //通知
            ProfessionalsEntity professionalsEntity = professionalsService.selectById(id);
            InformEntity informEntity= RabbitUtil.sendRabbic(professionalsEntity.getUserName(),"删除了" + professionalsEntity.getRealName() + "的专家入驻信息",new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);
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
    @RequestMapping(value="/deleteByBatch",method = {RequestMethod.DELETE,RequestMethod.POST})
    public ReturnResult deleteByBatch(@NotNull(message = "参数不能为空")String ids){
        if(professionalsService.deleteBatch(ids)){
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
     * 关闭专家信息-用户
     * @param id    主键id
     * @param closeReason   关闭原因
     * @return  ReturnResult
     */
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
     * 分页展示,可根据条件筛选
     * @param professionalsEntity    实体类
     * @param pageNum   页码
     * @param pageSize  页面尺寸
     * @return  ReturnResult
     */
    @RequestMapping(value="/showByPage",method = RequestMethod.GET)
    public ReturnResult showByPage(ProfessionalsEntity professionalsEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize){
        professionalsEntity.setStatus(0);
        professionalsEntity.setApprovalStatus(1);
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
