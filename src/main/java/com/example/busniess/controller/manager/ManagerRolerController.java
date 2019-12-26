package com.example.busniess.controller.manager;

import com.example.busniess.entity.ManagerRoler;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.ManagerRolerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manager/managerroler")
public class ManagerRolerController {
    @Autowired
    ManagerRolerService managerRolerServiceImpl;

    /**
     * 插入记录关系
     * 管理员用户
     */
    @RequestMapping("/insertManagerRolers")
    public ReturnResult insertManagerRolers(List<ManagerRoler> managerRolers) {
        if (managerRolerServiceImpl.insertManagerRoler(managerRolers)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUBMIT_ERROR);
    }

    /**
     * 删除记录关系
     */
    @RequestMapping("/delectManagerRolers")
    public ReturnResult delectManagerRolers(List<Integer> ids) {
        if (managerRolerServiceImpl.delectManagerRoler(ids)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELETE_ERROR);
    }

    /**
     * 修改
     */
    @RequestMapping("/updateManagerRolers")
    public ReturnResult updateManagerRolers(List<ManagerRoler> managerRolers) {
        if (managerRolerServiceImpl.updateManagerRoler(managerRolers)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);
    }

    /**
     * 查看关联关系
     */
@RequestMapping("/selectManagerRolers")
    public ReturnResult selectManagerRolers(ManagerRoler managerRoler) {
        return ReturnResult.success(managerRolerServiceImpl.selectManagerRolerByCondition(managerRoler));
    }
}
