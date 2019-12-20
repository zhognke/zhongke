package com.example.busniess.controller;

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
 * 意向表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-11-14 16:19:00
 */
@RestController
@RequestMapping("/intention")
public class IntentionController {

    @Autowired
    private IntentionService intentionService;

    /**
     * 新增
     *
     * @param intentionEntity 实体类
     * @return ReturnResult
     */
    @PostMapping("/addIntention")
    public ReturnResult addDeclaration(@Validated({UserValidator.InSet.class}) IntentionEntity intentionEntity) {
        if (intentionService.addIntention(intentionEntity)) {
            return ReturnResult.success("添加成功");
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

}
