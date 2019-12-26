package com.example.busniess.controller.manager;

import com.example.busniess.entity.Manager;
import com.example.busniess.exception.MyException;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.ManagerService;
import com.example.busniess.shiroutil.UsernamePasswordByUserTypeToken;
import com.example.busniess.validator.UserValidator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/manager/Manager")
//@Validated
public class ManagerController {

    @Autowired
    ManagerService managerServiceImpl;

    /**
     * 登录
     *
     * @param manage
     * @param password
     * @return
     */
    @RequestMapping("/adminLogin")
    public ReturnResult adminLogin(@NotNull(message = "用户名不能为空") String manage,
                                   @NotNull(message = "密码不能为空") String password) {

//    Manager manager=    managerServiceImpl.selectManager(manage);
//    if(manage==null){
//        throw  new UnknownAccountException();
//    }
        Subject subject = SecurityUtils.getSubject();//获取subject对象
        UsernamePasswordByUserTypeToken up = new UsernamePasswordByUserTypeToken(manage, password, "admin");

        subject.login(up);
        return ReturnResult.success(manage);

    }

    /**
     * 删除
     */
    @RequestMapping("/delectAdminManager")
    public ReturnResult delectAdminManager(@NotNull(message = "id号不能为空") Integer id) {
        if (managerServiceImpl.delectManager(id)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELETE_ERROR);
    }

    /**
     * 增加
     */
    @RequestMapping("/addManager")
    public ReturnResult addManager(@Validated({UserValidator.InSet.class}) Manager manager) throws MyException {
        boolean b = managerServiceImpl.insertManager(manager);
        if (b) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUBMIT_ERROR);
    }

    /**
     * 更新
     *
     * @param manager
     * @return
     */
    @RequestMapping("/upManager")
    public ReturnResult upManager(@Validated({UserValidator.UpDate.class}) Manager manager) {
        if (managerServiceImpl.updateManager(manager)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);
    }

    /**
     * 按照条件检索管理员
     * 检索管理员
     */
    @RequestMapping("/selectManagerByAdmin")
    public ReturnResult selectManagerByAdmin(Manager manager) {

        return ReturnResult.success(managerServiceImpl.selectManagerBycondition(manager));

    }
}
