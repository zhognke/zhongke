package com.example.busniess.controller;

import com.example.busniess.entity.InnovationActivitiesRegistrationEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.InnovationActivitiesRegistrationService;
import com.example.busniess.service.InnovationActivitiesService;
import com.example.busniess.utiles.ShiroUtils;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


/**
 * 创新活动报名表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-15 14:59:00
 */
@Validated
@RestController
@RequestMapping("/innovationActivitiesRegistration")
public class InnovationActivitiesRegistrationController {
    @Autowired
    private InnovationActivitiesService innovationActivitiesService;

    @Autowired
    private InnovationActivitiesRegistrationService innovationActivitiesRegistrationService;

    /**
     * 新增
     * @param innovationActivitiesRegistrationEntity
     * @return
     */
    @PostMapping("/addInnovationActivitiesRegistration")
    public ReturnResult addInnovationActivitiesRegistration(@Validated({UserValidator.InSet.class}) InnovationActivitiesRegistrationEntity innovationActivitiesRegistrationEntity){
        String userName = ShiroUtils.getUserName();
        if (userName == null) {
            return ReturnResult.erro(CodeMsg.NOT_HAVE_LIMITS);  //判断当前用户是否登录
        }
        Integer innovationId = innovationActivitiesRegistrationEntity.getInnovationId();
        if(innovationActivitiesRegistrationService.isRegistration(userName,innovationId)){//判断是否已经报名
            return ReturnResult.erro(CodeMsg.INNOVATION_REGISTRATION);
        }
        if(!innovationActivitiesService.enbaleRegistration(innovationId)){//判断活动能否报名
            return ReturnResult.erro(CodeMsg.INNOVATION_DISABLED);
        }
        if(innovationActivitiesRegistrationService.addInnovationActivitiesRegistration(innovationActivitiesRegistrationEntity)){
            return ReturnResult.success("报名成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @RequestMapping(value="/deleteById",method = {RequestMethod.DELETE,RequestMethod.POST})
    public ReturnResult deleteById(Integer id){
        if(innovationActivitiesRegistrationService.deleteByID(id)){
            return ReturnResult.success("删除成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
      * 批量删除
      * @param ids
      * @return
      */
    @RequestMapping(value="/deleteByBatch",method = {RequestMethod.DELETE,RequestMethod.POST})
    public ReturnResult deleteByBatch(@NotNull(message = "参数不能为空")String ids){
        if(innovationActivitiesRegistrationService.deleteBatch(ids)){
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
    @RequestMapping(value="/realDeleteById",method = {RequestMethod.DELETE,RequestMethod.POST})
    public ReturnResult realDeleteById(Integer id){
        if(innovationActivitiesRegistrationService.realDeleteByID(id)){
            return ReturnResult.success("删除成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改
     * @param innovationActivitiesRegistrationEntity
     * @return
     */
    @PostMapping("/updateById")
    public ReturnResult updateById(@Validated({UserValidator.UpDate.class}) InnovationActivitiesRegistrationEntity innovationActivitiesRegistrationEntity){
        if(innovationActivitiesRegistrationService.updateByID(innovationActivitiesRegistrationEntity)){
            return ReturnResult.success("修改成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 分页展示,可根据条件筛选
     * @param innovationActivitiesRegistrationEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/showByPage",method = RequestMethod.GET)
    public ReturnResult showByPage(InnovationActivitiesRegistrationEntity innovationActivitiesRegistrationEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10")Integer pageSize){
        PageInfo pageInfo = innovationActivitiesRegistrationService.showByPage(innovationActivitiesRegistrationEntity,pageNum,pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id搜索
     * @param id
     * @return
     */
    @RequestMapping(value="/getById",method = RequestMethod.GET)
    public ReturnResult getById(@NotNull(message = "参数不能为空")Integer id){
        InnovationActivitiesRegistrationEntity obj = innovationActivitiesRegistrationService.selectByID(id);
        if(obj!=null){
            return ReturnResult.success(obj);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }

    /**
     * 检验是否已经报名
     * @param username
     * @param innovationId
     * @return
     */
    @GetMapping("/isRegistration")
    public ReturnResult isRegistration(@NotNull(message = "用户名不能为空")String username,@NotNull(message = "活动id不能为空")Integer innovationId){
        return ReturnResult.success(innovationActivitiesRegistrationService.isRegistration(username,innovationId));
    }

}
