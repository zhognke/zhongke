package com.example.busniess.controller;

import com.example.busniess.entity.IntentionEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.IntentionService;
import com.example.busniess.utiles.ShiroUtils;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


/**
 * 意向表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-14 16:19:00
 */
@Validated
@RestController
@RequestMapping("/intention")
public class IntentionController {

    @Autowired
    private IntentionService intentionService;

    /**
     * 新增
     * @param intentionEntity
     * @return
     */
    @PostMapping("/addIntention")
    public ReturnResult addDeclaration(@Validated({UserValidator.InSet.class}) IntentionEntity intentionEntity){
        /*String userName = ShiroUtils.getUserName();
        if (userName == null) {
            return ReturnResult.erro(CodeMsg.NOT_HAVE_LIMITS);  //判断当前用户是否登录
        }else{
            intentionEntity.setUserName(userName);
        }*/
        if(intentionService.addIntention(intentionEntity)){
            return ReturnResult.success("添加成功");
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
    public ReturnResult deleteById(@NotNull(message = "参数不能为空")Integer id){
        if(intentionService.deleteByID(id)){
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
        if(intentionService.deleteBatch(ids)){
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
    public ReturnResult realDeleteById(@NotNull(message = "参数不能为空")Integer id){
        if(intentionService.realDeleteByID(id)){
            return ReturnResult.success("删除成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改
     * @param intentionEntity
     * @return
     */
    @PostMapping("/updateById")
    public ReturnResult updateDeclaration(@Validated({UserValidator.UpDate.class}) IntentionEntity intentionEntity){
        if(intentionService.updateByID(intentionEntity)){
            return ReturnResult.success("修改成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 分页展示,可根据条件筛选
     * @param intentionEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/showByPage",method = RequestMethod.GET)
    public ReturnResult showByPage(IntentionEntity intentionEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10")Integer pageSize){
        PageInfo pageInfo = intentionService.showByPage(intentionEntity,pageNum,pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id搜索
     * @param id
     * @return
     */
    @RequestMapping(value="/getById",method = RequestMethod.GET)
    public ReturnResult getById(@NotNull(message = "参数不能为空")Integer id){
        IntentionEntity obj = intentionService.selectByID(id);
        if(obj!=null){
            return ReturnResult.success(obj);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }
}
