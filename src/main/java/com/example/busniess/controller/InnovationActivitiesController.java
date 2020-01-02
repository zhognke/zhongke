package com.example.busniess.controller;

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
@RestController
@RequestMapping("/innovationActivities")
public class InnovationActivitiesController {

    @Autowired
    private InnovationActivitiesService innovationActivitiesService;

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
