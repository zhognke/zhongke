package com.example.busniess.controller;

import com.example.busniess.entity.Roler;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.RolerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roler")
public class RolerController {
    @Autowired
    RolerService rolerServiceImpl;

    /**
     * 增加角色
     *
     * @param roler
     * @return
     */
    @RequestMapping("/addRoler")
    public ReturnResult addRoler(Roler roler) {

        if (rolerServiceImpl.insertRoler(roler)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUBMIT_ERROR);

    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @RequestMapping("/delectRoler")
    public ReturnResult delectRoler(Integer id) {
        if (rolerServiceImpl.delectRoler(id)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELETE_ERROR);
    }

    /**
     * 修改角色
     */
    @RequestMapping("/updateRoler")
    public ReturnResult updateRoler(Roler roler) {
        if (rolerServiceImpl.updateRoler(roler)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);
    }

    /**
     * 查看角色
     */

    @RequestMapping("/selectRoler")
    public ReturnResult selectRoler() {
        return ReturnResult.success(rolerServiceImpl.selectRoler());
    }

    /**
     * 设置角色
     */
    @RequestMapping("/setRoler")
    public ReturnResult setRoler(Integer rid, String userName) {

        if (rolerServiceImpl.insertRoler(rid, userName)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.AUDITOR_ERROR);

    }

}
