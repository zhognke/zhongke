package com.example.busniess.controller;

import com.example.busniess.entity.Person;
import com.example.busniess.entity.Reject;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.PersonService;
import com.example.busniess.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/person")
@Validated
public class PersonController {
    @Autowired
    PersonService personServiceImpl;

    /**
     * 添加
     */
    @RequestMapping("/addPerson")
    public ReturnResult addPerson(@Validated(UserValidator.InSet.class) Person person) {
        if (personServiceImpl.addPerson(person)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUBMIT_ERROR);
    }

    /**
     * 删除
     */
    @RequestMapping("/delectPerson")
    public ReturnResult delectPerson(@NotNull(message = "id号不能为空") Integer id) {
        if (personServiceImpl.delectPerson(id)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELETE_ERROR);
    }

    /**
     * 修改
     */
    @RequestMapping("/updatePerson")
    public ReturnResult updatePerson(@Validated(UserValidator.UpDate.class) Person person) {
        if (personServiceImpl.updatePerson(person)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);
    }

    /**
     * 查看所有的
     */
    @RequestMapping("/selectAllPerson")
    public ReturnResult selectAllPerson(Person person,@RequestParam(required = false,defaultValue = "1")
                                                    Integer pageNumber,
                                        @RequestParam(required = false,defaultValue = "5")
                                        Integer pageSize) {
        return ReturnResult.success(personServiceImpl.selectAllPerson(person,pageNumber,pageSize));
    }

    /**
     * 查看自己的
     */
    @RequestMapping("/selectMyPerson")
    public ReturnResult selectMyPerson(@NotNull(message = "名字不能为空") String uName) {
        return ReturnResult.success(personServiceImpl.selectMyPerson(uName));
    }

    /**
     * 审核通过
     */
    @RequestMapping("/updateStatue")
    public ReturnResult updateStatue(@NotNull(message = "id号不能为空")
                                                 Integer id,
                                     @NotNull(message = "角色id不能为空")
                                     Integer rid,
                                     @NotBlank(message = "关联用户名不能为空")
                                     String userName,
                                   @RequestParam(required = false,defaultValue = "1")
                                     Integer statue,
                                     @RequestParam(required = false,defaultValue = "0")
                                     Integer reId) {
        if (personServiceImpl.upStatue(id, rid, userName, statue, reId)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUBMIT_ERROR);
    }

    /**
     * 审核驳回
     */
    @RequestMapping("/rejectPerson")
    public ReturnResult rejectPerson(@Validated(UserValidator.InSet.class) Reject reject) {
        if (personServiceImpl.rejectPerson(reject)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.AUDITOR_ERROR);
    }

    /**
     * 根据状态查看自己的
     */
    @RequestMapping("/selectOnePerson")
    public ReturnResult selectOnePerson(@NotBlank(message = "名字不能为空") String uName,
                                        @NotNull(message = "状态不能为空") Integer statue){
        return  ReturnResult.success(personServiceImpl.selectOnePerson(uName, statue));
    }
/**
 * 查看具体的
 */
public  ReturnResult selectOne(@NotNull(message = "id不能为空") Integer id){
    return ReturnResult.success(personServiceImpl.selectOnePersonByid(id));
}


}




