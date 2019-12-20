package com.example.busniess.controller.manager;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.entity.IntentionEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.IntentionService;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


/**
 * 意向表-管理端
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-14 16:19:00
 */
@RestController("intention")
@RequestMapping("/manager/intention")
public class IntentionController {

    @Autowired
    private IntentionService intentionService;


    /**
     * 修改
     *
     * @param intentionEntity 实体类
     * @return ReturnResult
     */
    @PostMapping("/updateById")
    public ReturnResult updateDeclaration(@Validated({UserValidator.UpDate.class}) IntentionEntity intentionEntity) {
        if (intentionService.updateByID(intentionEntity)) {
            return ReturnResult.success("修改成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 逻辑删除
     *
     * @param id 主键id
     * @return ReturnResult
     */
    @SysLog(value = "删除意向", type = "用户意向")
    @RequestMapping(value = "/deleteById", method = {RequestMethod.DELETE, RequestMethod.POST})
    public ReturnResult deleteById(@NotNull(message = "参数不能为空") Integer id) {
        if (intentionService.deleteByID(id)) {
            return ReturnResult.success("删除成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 批量删除
     *
     * @param ids 主键ids(多个id用英文逗号分隔)
     * @return ReturnResult
     */
    @SysLog(value = "批量删除", type = "用户意向")
    @RequestMapping(value = "/deleteByBatch", method = {RequestMethod.DELETE, RequestMethod.POST})
    public ReturnResult deleteByBatch(@NotNull(message = "参数不能为空") String ids) {
        if (intentionService.deleteBatch(ids)) {
            return ReturnResult.success("删除成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 分页展示,可根据条件筛选
     *
     * @param intentionEntity 实体类
     * @param pageNum         当前页码
     * @param pageSize        页面尺寸
     * @return ReturnResult
     */
    @RequestMapping(value = "/showByPage", method = RequestMethod.GET)
    public ReturnResult showByPage(IntentionEntity intentionEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo = intentionService.showByPage(intentionEntity, pageNum, pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 修改意向状态
     *
     * @param id     主键id
     * @param statue 状态
     * @return ReturnResult
     */
    @SysLog(value = "变更跟进状态", type = "用户意向")
    @PostMapping("/updateStatue")
    public ReturnResult updateStatue(@NotNull(message = "参数不能为空") Integer id, @NotNull(message = "参数不能为空") Integer statue) {
        if (intentionService.updateStatue(id, statue)) {
            return ReturnResult.success("操作成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改有效状态
     *
     * @param id      主键id
     * @param isValid 邮箱状态
     * @return ReturnResult
     */
    @SysLog(value = "变更有效状态", type = "用户意向")
    @PostMapping("/updateValid")
    public ReturnResult updateValid(@NotNull(message = "参数不能为空") Integer id, @NotNull(message = "参数不能为空") Integer isValid) {
        if (intentionService.updateValid(id, isValid)) {
            return ReturnResult.success("操作成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id搜索
     *
     * @param id 主键id
     * @return ReturnResult
     */
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ReturnResult getById(@NotNull(message = "参数不能为空") Integer id) {
        IntentionEntity obj = intentionService.selectByID(id);
        if (obj != null) {
            return ReturnResult.success(obj);
        } else {
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }
}
