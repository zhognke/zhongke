package com.example.busniess.controller.manager;

import com.example.busniess.entity.IntentionRecordEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.IntentionRecordService;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


/**
 * 意向跟进表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-12-18 19:00:04
 */
@Validated
@RestController
@RequestMapping("/manager/intentionrecord")
public class IntentionRecordController {
    @Autowired
    private IntentionRecordService intentionRecordService;

    /**
     * 新增
     *
     * @param intentionRecordEntity
     * @return
     */
    @PostMapping("/addIntentionRecord")
    public ReturnResult addIntentionRecord(@Validated({UserValidator.InSet.class}) IntentionRecordEntity intentionRecordEntity) {
        if (intentionRecordService.addIntentionRecord(intentionRecordEntity)) {
            return ReturnResult.success("添加成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById", method = {RequestMethod.DELETE, RequestMethod.POST})
    public ReturnResult deleteById(Integer id) {
        if (intentionRecordService.deleteByID(id)) {
            return ReturnResult.success("删除成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改
     *
     * @param intentionRecordEntity
     * @return
     */
    @PostMapping("/updateById")
    public ReturnResult updateDeclaration(@Validated({UserValidator.UpDate.class}) IntentionRecordEntity intentionRecordEntity) {
        if (intentionRecordService.updateByID(intentionRecordEntity)) {
            return ReturnResult.success("修改成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 分页展示
     *
     * @param intentionId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/showByPage", method = RequestMethod.GET)
    public ReturnResult showByPage(@NotNull(message = "参数不能为空")Integer intentionId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo = intentionRecordService.showByPage(intentionId, pageNum, pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id搜索
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ReturnResult getById(@NotNull(message = "参数不能为空") Integer id) {
        IntentionRecordEntity obj = intentionRecordService.selectByID(id);
        if (obj != null) {
            return ReturnResult.success(obj);
        } else {
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }
}
