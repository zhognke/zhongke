package com.example.busniess.controller.manager;

import com.example.busniess.entity.Roler;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.RolerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager/roler")
public class RolerController {
    @Autowired
    RolerService rolerServiceImpl;

    /**
     * 查看所有的角色
     */
    @RequestMapping("/selectAllRoler")
    public ReturnResult selectAllRoler() {
        return ReturnResult.success(rolerServiceImpl.selectRoler());
    }

    /**
     * 增加角色
     */
    @RequestMapping("/addRoler")
    public ReturnResult addRoler(Roler roler) {
        if (rolerServiceImpl.insertRoler(roler)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.ADD_LERROR);
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping("/delectRoler")
    public  ReturnResult delectRoler(Integer id){
        if(rolerServiceImpl.delectRoler(id)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELETE_ERROR);
    }

    /**
     * 修改角色
     */
    @RequestMapping("/updateRoler")
    public  ReturnResult updateRoler(Roler roler){
        if (rolerServiceImpl.updateRoler(roler)) {
            return ReturnResult.success();
        }
        return  ReturnResult.erro(CodeMsg.UPDATE_ERROR);
    }



}
