package com.example.busniess.controller;

import com.example.busniess.entity.AdviceBoxEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.AdviceBoxService;
import com.example.busniess.utiles.ShiroUtils;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


/**
 * 意见信箱表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2020-01-06 09:01:24
 */
@Validated
@RestController
@RequestMapping("/adviceBox")
public class AdviceBoxController {
    @Autowired
    private AdviceBoxService adviceBoxService;

    /**
     * 新增
     *
     * @param adviceBoxEntity
     * @return
     */
    @PostMapping("/submitAdvice")
    public ReturnResult submitAdvice(@Validated({UserValidator.InSet.class}) AdviceBoxEntity adviceBoxEntity) {
        if (adviceBoxService.addAdviceBox(adviceBoxEntity)) {
            return ReturnResult.success("提交成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 查询数量
     * @return
     */
    @GetMapping("getCounts")
    public ReturnResult getCounts(){
        return ReturnResult.success(adviceBoxService.getCount());
    }
    /**
     * 分页展示,可根据条件筛选
     *
     * @param adviceBoxEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/showByPage", method = RequestMethod.GET)
    public ReturnResult showByPage(AdviceBoxEntity adviceBoxEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        adviceBoxEntity.setIsShow(1);
        PageInfo pageInfo = adviceBoxService.showByPage(adviceBoxEntity, pageNum, pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 分页展示,可根据条件筛选
     *
     * @param adviceBoxEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/showByPageForCenter", method = RequestMethod.GET)
    public ReturnResult showByPageForCenter(AdviceBoxEntity adviceBoxEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        String userName = ShiroUtils.getUserName();
        if (userName == null) {
            return ReturnResult.erro(CodeMsg.LOGIN_TIME_OUT);
        }else{
            adviceBoxEntity.setUsername(userName);
        }
        PageInfo pageInfo = adviceBoxService.showByPage(adviceBoxEntity, pageNum, pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id搜索
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ReturnResult getById(@NotNull(message = "id不能为空") Integer id) {
        AdviceBoxEntity obj = adviceBoxService.selectByID(id);
        if (obj != null) {
            return ReturnResult.success(obj);
        } else {
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }
}
