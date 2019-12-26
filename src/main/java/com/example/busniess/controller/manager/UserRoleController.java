package com.example.busniess.controller.manager;

import com.example.busniess.entity.User;
import com.example.busniess.entity.UserRole;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.UserRoleService;
import com.example.busniess.service.UserService;
import com.example.busniess.utiles.RabbitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/manager/userroler")
@RestController
public class UserRoleController {
    @Autowired
    UserRoleService userRoleServiceImpl;
    @Autowired
    UserService userServiceImpl;

    /**
     * 查看所有关联关系
     */
    @RequestMapping("/selectUserRoler")
    public ReturnResult selectUserRoler(UserRole userRole) {
        return ReturnResult.success(userRoleServiceImpl.selectUserRoler(userRole));
    }

    /**
     * 增加关联关系
     * 包含删除功能
     * 不传和角色相关的就是删除
     * id;//id号
     * rid;//角色id
     * userName;//用户名
     * role;//角色名
     */
    @RequestMapping("/addUserRoler")
    public ReturnResult addUserRoler(List<UserRole>  userRoles) {
        if (userRoleServiceImpl.insertUserRoler(userRoles)) {


            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.ADD_LERROR);
    }

    /**
     * 跟新关联关系
     */
    @RequestMapping("/updateUserRoler")
    public ReturnResult updateUserRoler(List<UserRole> userRoles) {
        if (userRoleServiceImpl.upUserRoler(userRoles)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);
    }


    /**
     * 删除关联关系
     */
    @RequestMapping("/delectUserRoler")
    public ReturnResult delectUserRoler(List<Integer> ids) {
        if (userRoleServiceImpl.delectUserroler(ids)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELETE_ERROR);

    }

    /**
     * 检索用户后台用
     */
    @RequestMapping("/selectAdminUser")
    public  ReturnResult selectAdminUser(User user){
        return ReturnResult.success(userServiceImpl.selectAdminUser(user));
    }





}




