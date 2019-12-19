package com.example.busniess.controller.manager;

import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.Person;
import com.example.busniess.entity.User;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.AdminUserService;
import com.example.busniess.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/admin")
@RestController
public class UserPersionController {
    @Autowired
    AdminUserService adminUserServiceImpl;

    @Autowired
    UserService userServiceImpl;

    /**
     * 增加私人账号
     *
     * @param user
     * @return
     */
    @RequestMapping("/addAdminPerson")
    public ReturnResult addAdminPerson(User user) {
        if (adminUserServiceImpl.addPersonUser(user, user.getPerson())) {
            return ReturnResult.success();
        }


        return ReturnResult.erro(CodeMsg.ADD_LERROR);
    }

    /**
     * 修改私人账号
     *
     * @param user
     * @return
     */
    @RequestMapping("/upAdminPerson")
    public ReturnResult upAdminPerson(User user) {

        if (adminUserServiceImpl.upPerSonUser(user, user.getPerson())) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);
    }

    /**
     * 按条件检索私人用户
     *
     * @param person
     * @return
     */
    @RequestMapping("/electAdminPerson")
    public ReturnResult selectAdminPerson(Person person, @RequestParam(required = false,defaultValue = "1")
            Integer pageNumber,@RequestParam(required = false,defaultValue = "6") Integer pageSize) {
   PageInfo p= userServiceImpl.searchPerson(person,pageNumber,pageSize);

        return ReturnResult.success(p);
    }

}
