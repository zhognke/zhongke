package com.example.busniess.controller;

import com.example.busniess.entity.Person;
import com.example.busniess.entity.Reject;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personServiceImpl;

    /**
     * 添加
     */
    @RequestMapping("/addPerson")
    public ReturnResult addPerson(Person person) {
        if (personServiceImpl.addPerson(person)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUMIT_ERROR);
    }

    /**
     * 删除
     */
    @RequestMapping("/delectPerson")
    public ReturnResult delectPerson(Integer id) {
        if (personServiceImpl.delectPerson(id)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELECT_ERROR);
    }

    /**
     * 修改
     */
    @RequestMapping("/updatePerson")
    public ReturnResult updatePerson(Person person) {
        if (personServiceImpl.updatePerson(person)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);
    }

    /**
     * 查看所有的
     */
    @RequestMapping("/selectAllPerson")
    public ReturnResult selectAllPerson(Integer pageNumber, Integer pageSize) {
        return ReturnResult.success(personServiceImpl.selectAllPerson(pageNumber,pageSize));
    }

    /**
     * 查看自己的
     */
    @RequestMapping("/selectMyPerson")
    public ReturnResult selectMyPerson(String uName) {
        return ReturnResult.success(personServiceImpl.selectMyPerson(uName));
    }

    /**
     * 审核通过
     */
    @RequestMapping("/updateStatue")
    public ReturnResult updateStatue(Integer id, Integer rid, String userName, Integer statue, Integer reId) {
        if (personServiceImpl.upStatue(id, rid, userName, statue, reId)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUMIT_ERROR);
    }

    /**
     * 审核驳回
     */
    @RequestMapping("/rejectPerson")
    public ReturnResult rejectPerson(Reject reject) {
        if (personServiceImpl.rejectPerson(reject)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.AUDITOR_ERROR);
    }

    /**
     * 查看具体的
     */
    @RequestMapping("/selectOnePerson")
    public ReturnResult selectOnePerson(String uName,Integer statue){
        return  ReturnResult.success(personServiceImpl.selectOnePerson(uName, statue));
    }

}




