package com.example.busniess.controller;


import com.example.busniess.entity.DemandsCommentEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.DemandsCommentService;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-24 17:14:07
 */
@RestController
@RequestMapping("/demandsComment")
public class DemandsCommentController {
    @Autowired
    private DemandsCommentService demandsCommentService;

    /**
     * 新增评论
     * @param demandsCommentEntity  实体类
     * @return  ReturnResult
     */
    @PostMapping("/addDemandsComment")
    public ReturnResult addDemandsComment(@Validated({UserValidator.InSet.class}) DemandsCommentEntity demandsCommentEntity){
        if(demandsCommentService.addComment(demandsCommentEntity)){
            return ReturnResult.success();
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 查看当前需求的评论
     * @param demandsID 需求id
     * @param pageNum   页码
     * @param pageSize  页面尺寸
     * @return  ReturnResult
     */
    @RequestMapping(value="/showCommentByPage",method = RequestMethod.GET)
    public ReturnResult showCommentByPage(int demandsID, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize){
        PageInfo info =  demandsCommentService.showByPage(demandsID,pageNum,pageSize);
        return ReturnResult.success(info);
    }
}
