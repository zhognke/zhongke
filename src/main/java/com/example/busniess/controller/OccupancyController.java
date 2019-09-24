package com.example.busniess.controller;

import com.example.busniess.entity.Occupancy;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.OccupancyService;
import com.example.busniess.service.OccupancyServiceimplements;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/occupancy")
public class OccupancyController {
    @Resource
    OccupancyService occupancyServiceimplements;

    /**
     * 新建入住成果
     * @param occupancy
     * @return
     */
    @RequestMapping("/addOccupancy")
    public ReturnResult addOccupancy(@Validated({UserValidator.InSet.class}) Occupancy occupancy){

        if(occupancyServiceimplements.addOccupancy(occupancy)) {
            return  ReturnResult.success();
        }else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }

    }
    /**
     * 删除入住成果
     */
    @RequestMapping("/delectOccupancy")
    public  ReturnResult delectOccupancy(Integer id){
        if(occupancyServiceimplements.delectOccupancy(id)) {
            return ReturnResult.success();
        }   else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 查看自己发布的科技成果
     * @param userName//用户姓名
     * @param pageNumber//第几页
     * @param pageSize//显示多少
     * @return
     */
    @RequestMapping("/examineMyOccupancy")
    public ReturnResult examineMyOccupancy(String userName,Integer pageNumber,Integer pageSize){
        PageInfo o=occupancyServiceimplements.selectMyOccupancy(userName,pageNumber,pageSize);
        return ReturnResult.success(o);

    }

    /**
     *显示所有能显示的内容
     * @param pageNum//页数
     * @param pagesize//显示的数量
     * @return
     */
    @RequestMapping("/selectOnShowOccupancy")
    public  ReturnResult selectOnShowOccupancy(Integer pageNum, Integer pagesize ){
        PageInfo pageInfo=occupancyServiceimplements.selectOnShowOccupancy(pageNum,pagesize);
        return ReturnResult.success(pageInfo);
    }

}
