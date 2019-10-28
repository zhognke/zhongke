package com.example.busniess.controller;

import com.example.busniess.entity.FinancingEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.FinancingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Financing")
public class FinancingController {
    @Resource
    FinancingService financingServiceImpl;

    /**
     * 添加融资
     *
     * @param financing
     * @return
     */
    @RequestMapping("/addFinancing")
    public ReturnResult addFinancing(FinancingEntity financing) {
        if (financingServiceImpl.insertFinacing(financing)) {
            return ReturnResult.success();
        }
        return ReturnResult.success(CodeMsg.SUMIT_ERROR);
    }

    /**
     * 删除融资
     */
    @RequestMapping("/delectFinancing")
    public ReturnResult delectFinancing(Integer id) {
        if (financingServiceImpl.delectFinacing(id)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELECT_ERROR);
    }

    /**
     * 修改融资
     */
    @RequestMapping("/updateFinancing")
    public ReturnResult updateFinancing(FinancingEntity financing) {
        if (financingServiceImpl.updateFinacing(financing)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);

    }

    /**
     * 查询自己的
     */
    @RequestMapping("/findMyFinancing")
    public ReturnResult findMyFinancing(String uName, Integer pagenum, Integer pageSize) {
        return ReturnResult.success(financingServiceImpl.selectMyFiancing(uName, pagenum, pageSize));

    }

    /**
     * 根据条件查询
     */
    @RequestMapping("/findFinancingByCondition")
    public ReturnResult findFinancingByCondition(FinancingEntity financing, Integer pagenum, Integer pageSize) {
       return ReturnResult.success(financingServiceImpl.SelectAllFinacing(financing, pagenum, pageSize));
    }
    /**
     * 修改状态
     */
    @RequestMapping("/upFinancingStatue")
    public  ReturnResult upFinancingStatue(Integer id,Integer statue){
     if(financingServiceImpl.updateFinacingStatue(id,statue)){
         return ReturnResult.success();
     }
     return ReturnResult.erro(CodeMsg.ISSUE_ERROR);
    }

    /**
     * 查看具体
     * @param id
     * @return
     */
    @RequestMapping("/selectOneById")
    public  ReturnResult selectOneById(Integer id){
        return  ReturnResult.success(financingServiceImpl.selectFinancingById(id));

    }

}
