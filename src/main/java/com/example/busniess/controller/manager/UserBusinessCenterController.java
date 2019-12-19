package com.example.busniess.controller.manager;

import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.User;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.AdminUserService;
import com.example.busniess.service.UserService;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserBusinessCenterController {

    @Autowired
    AdminUserService adminUserServiceImpl;
    @Autowired
    UserService userServiceImpl;

    /**
     * 添加企业用户
     *
     * @param user
     * @param user
     * @return
     */
    @RequestMapping("/addAdminBusinessCenter")
    public ReturnResult addAdminBusinessCenter(User user) {
        if (adminUserServiceImpl.adminAddUser(user, user.getBusinessCenter())) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.ADD_LERROR);
    }

    /**
     * 删除用户
     * 企业和私人
     *
     * @param user 参数 id
     * @return
     */
    @RequestMapping("/delectAdminUser")
    public ReturnResult delectAdminUser(User user) {
        if (adminUserServiceImpl.adminDelletUser(user)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELETE_ERROR);
    }

    /**
     * 修改企业用户信息
     */

    @RequestMapping("/upAdminBusinessUser")
    public ReturnResult upAdminBusinessUser(User user) {
        if (adminUserServiceImpl.adminUpUser(user,user.getBusinessCenter())) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);
    }

    /**
     * 按条件检索企业用户
     */
    @RequestMapping("/selectAdminBusinessUser")
    public ReturnResult selectAdminBusinessUser(BusinessCenter businessCenter, Integer pageNumber, Integer pageSize) {
        PageInfo p = userServiceImpl.searchBusinessUser(businessCenter, pageNumber, pageSize);
        return ReturnResult.success(p);
    }


}
