package com.example.busniess.controller;

import com.example.busniess.entity.InnovationActivitiesApplicationEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.InnovationActivitiesApplicationService;
import com.example.busniess.utiles.ShiroUtils;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


/**
 * 创新活动申请表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-15 14:59:00
 */
@Validated
@RestController
@RequestMapping("/innovationActivitiesApplication")
public class InnovationActivitiesApplicationController {

    @Autowired
    private InnovationActivitiesApplicationService innovationActivitiesApplicationService;

    /**
     * 新增
     * @param innovationActivitiesApplicationEntity    实体类
     * @return ReturnResult
     */
    @PostMapping("/addInnovationActivitiesApplication")
    public ReturnResult addDeclaration(@Validated({UserValidator.InSet.class}) InnovationActivitiesApplicationEntity innovationActivitiesApplicationEntity){
        if(innovationActivitiesApplicationService.addInnovationActivitiesApplication(innovationActivitiesApplicationEntity)){
            return ReturnResult.success("添加成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 逻辑删除
     * @param id    主键id
     * @return ReturnResult
     */
    @RequestMapping(value="/deleteById",method = {RequestMethod.DELETE,RequestMethod.POST})
    public ReturnResult deleteById(@NotNull(message = "参数不能为空")Integer id){
        if(innovationActivitiesApplicationService.deleteByID(id)){
            return ReturnResult.success("操作成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
      * 批量删除
      * @param ids    主键ids(多个id用英文逗号分隔)
      * @return ReturnResult
      */
    @RequestMapping(value="/deleteByBatch",method = {RequestMethod.DELETE,RequestMethod.POST})
    public ReturnResult deleteByBatch(@NotNull(message = "参数不能为空")String ids){
        if(innovationActivitiesApplicationService.deleteBatch(ids)){
            return ReturnResult.success("操作成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改
     * @param innovationActivitiesApplicationEntity    实体类
     * @return ReturnResult
     */
    @PostMapping("/updateById")
    public ReturnResult updateById(@Validated({UserValidator.UpDate.class}) InnovationActivitiesApplicationEntity innovationActivitiesApplicationEntity){
        if(innovationActivitiesApplicationService.updateByID(innovationActivitiesApplicationEntity)){
            return ReturnResult.success("修改成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 分页展示,可根据条件筛选
     * @param innovationActivitiesApplicationEntity    实体类
     * @param pageNum    页码
     * @param pageSize    页面尺寸
     * @return ReturnResult
     */
    @RequestMapping(value="/showByPage",method = RequestMethod.GET)
    public ReturnResult showByPage(InnovationActivitiesApplicationEntity innovationActivitiesApplicationEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10")Integer pageSize){
        innovationActivitiesApplicationEntity.setApprovalStatus(1);
        PageInfo pageInfo = innovationActivitiesApplicationService.showByPage(innovationActivitiesApplicationEntity,pageNum,pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 会员中心-我的活动
     * @param innovationActivitiesApplicationEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/showByPageForCenter",method = RequestMethod.GET)
    public ReturnResult showByPageForCenter(InnovationActivitiesApplicationEntity innovationActivitiesApplicationEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10")Integer pageSize){
        String userName = ShiroUtils.getUserName();
        if (ShiroUtils.isLogin()) {
            innovationActivitiesApplicationEntity.setUsername(userName);   //只能查看自己的信息
        } else {
            return ReturnResult.erro(CodeMsg.NOT_HAVE_LIMITS);
        }
        PageInfo pageInfo = innovationActivitiesApplicationService.showByPage(innovationActivitiesApplicationEntity,pageNum,pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id搜索
     * @param id    主键id
     * @return ReturnResult
     */
    @RequestMapping(value="/getById",method = RequestMethod.GET)
    public ReturnResult getById(@NotNull(message = "参数不能为空")Integer id){
        InnovationActivitiesApplicationEntity obj = innovationActivitiesApplicationService.selectByID(id);
        if(obj!=null){
            return ReturnResult.success(obj);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }

}
