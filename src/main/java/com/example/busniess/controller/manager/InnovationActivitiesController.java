package com.example.busniess.controller.manager;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.entity.InnovationActivitiesEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.InnovationActivitiesService;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


/**
 * 创新活动表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-15 14:59:00
 */
@Validated
@RestController("innovationActivities")
@RequestMapping("/manager/innovationActivities")
public class InnovationActivitiesController {

    @Autowired
    private InnovationActivitiesService innovationActivitiesService;

    /**
     * 新增
     * @param innovationActivitiesEntity
     * @return ReturnResult
     */
    @SysLog(value="新增活动",type="创新活动")
    @PostMapping("/addInnovationActivities")
    public ReturnResult addInnovationActivities(@Validated({UserValidator.InSet.class}) InnovationActivitiesEntity innovationActivitiesEntity){
        if(innovationActivitiesService.addInnovationActivities(innovationActivitiesEntity)){
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
    @SysLog(value="逻辑删除",type="创新活动")
    @RequestMapping(value="/deleteById",method = {RequestMethod.DELETE,RequestMethod.POST})
    public ReturnResult deleteById(@NotNull(message = "参数不能为空")Integer id){
        if(innovationActivitiesService.deleteByID(id)){
            return ReturnResult.success("删除成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
      * 批量删除
      * @param ids    主键ids(多个id用英文逗号分隔)
      * @return ReturnResult
      */
    @SysLog(value="批量删除",type="创新活动")
    @RequestMapping(value="/deleteByBatch",method = {RequestMethod.DELETE,RequestMethod.POST})
    public ReturnResult deleteByBatch(@NotNull(message = "参数不能为空")String ids){
        if(innovationActivitiesService.deleteBatch(ids)){
            return ReturnResult.success("删除成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 彻底删除
     * @param id    主键id
     * @return ReturnResult
     */
    @SysLog(value="彻底删除",type="创新活动")
    @RequestMapping(value="/realDeleteById",method = {RequestMethod.DELETE,RequestMethod.POST})
    public ReturnResult realDeleteById(@NotNull(message = "参数不能为空")Integer id){
        if(innovationActivitiesService.realDeleteByID(id)){
            return ReturnResult.success("删除成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改
     * @param innovationActivitiesEntity    实体类
     * @return ReturnResult
     */
    @SysLog(value="修改信息",type="创新活动")
    @PostMapping("/updateById")
    public ReturnResult updateById(@Validated({UserValidator.UpDate.class}) InnovationActivitiesEntity innovationActivitiesEntity){
        if(innovationActivitiesService.updateByID(innovationActivitiesEntity)){
            return ReturnResult.success("修改成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改状态
     */
    @SysLog(value="修改状态",type="创新活动")
    @PostMapping("updateStatus")
    public ReturnResult updateStatus(@NotNull(message = "参数不能为空")Integer id,@NotNull(message = "状态不能为空")Integer status){
        if(innovationActivitiesService.updateStatus(id,status)){
            return ReturnResult.success("操作成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 分页展示,可根据条件筛选
     * @param innovationActivitiesEntity    实体类
     * @param pageNum   页码
     * @param pageSize  页面尺寸
     * @return ReturnResult
     */
    @RequestMapping(value="/showByPage",method = RequestMethod.GET)
    public ReturnResult showByPage(InnovationActivitiesEntity innovationActivitiesEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10")Integer pageSize){
        PageInfo pageInfo = innovationActivitiesService.showByPage(innovationActivitiesEntity,pageNum,pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id搜索
     * @param id    主键id
     * @return ReturnResult
     */
    @RequestMapping(value="/getById",method = RequestMethod.GET)
    public ReturnResult getById(@NotNull(message = "参数不能为空")Integer id){
        InnovationActivitiesEntity obj = innovationActivitiesService.selectByID(id);
        if(obj!=null){
            return ReturnResult.success(obj);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }

    /**
     * 根据id显示详情
     * @param id    主键id
     * @return ReturnResult
     */
    @RequestMapping(value="/showById",method = RequestMethod.GET)
    public ReturnResult showById(@NotNull(message = "参数不能为空")Integer id){
        InnovationActivitiesEntity obj = innovationActivitiesService.selectByID(id);
        if(obj!=null){
            return ReturnResult.success(obj);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }
}
