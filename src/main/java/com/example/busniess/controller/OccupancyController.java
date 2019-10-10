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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/occupancy")
@Validated
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
    public ReturnResult examineMyOccupancy(@NotBlank(message = "传入值不能为空") String userName,@Min(value = 1,message = "传入值必须是数字且不能小于1") Integer pageNumber,@Min(value = 1,message = "传入值必须是数字且不能小于1") Integer pageSize){
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
    public  ReturnResult selectOnShowOccupancy(@NotNull(message = "参数不能为空")@Min(value = 1,message = "传入值必须是数字且不能小于1") Integer pageNum, @NotNull(message = "参数不能为空")@Min(value = 1,message = "传入值必须是数字且不能小于1")Integer pagesize ){
        PageInfo pageInfo=occupancyServiceimplements.selectOnShowOccupancy(pageNum,pagesize);
        return ReturnResult.success(pageInfo);
    }

//    /**
//     *审核
//     * @param id    //企业的id号
//     * @param userName //用户名
//     * @param roleId //角色id
//     * @return
//     */
//    @RequestMapping("/auditor")
//    public  ReturnResult auditor(@NotNull(message = "企业认证id不能为空") Integer id, @NotBlank(message = "用户名不能为空") String userName,@NotNull(message = "角色id不能为空") Integer roleId){
//
//
//       if(occupancyServiceimplements.upDateStatue(1,id,userName,roleId)) {
//           return ReturnResult.success();
//       }else {
//           return ReturnResult.erro(CodeMsg.AUDITOR_ERROR);
//       }
//
//
//    }

    /**
     * 更新发布状态
     * @param kStatue
     * @param id
     * @return
     */
@RequestMapping("/upKstatue")
    public ReturnResult upKstatue(@NotNull(message = "跟新状态不能为空") Integer kStatue,@NotNull(message = "发布成果的id不能为空") Integer id){
       if(occupancyServiceimplements.upDateKstatue(kStatue,id)){
         return ReturnResult.success();
       } else {
           return ReturnResult.erro(CodeMsg.ISSUE_ERROR);
       }
    }
/**
 * 根据行业显示科技成果
 */
@RequestMapping("/selectByIndustry")
public ReturnResult selectByIndustry(Occupancy occupancy,Integer pageNum, @NotNull(message = "参数不能为空")@Min(value = 1,message = "传入值必须是数字且不能小于1")Integer pagesize){

    return ReturnResult.success(occupancyServiceimplements.selectOccupancyByIndustry(occupancy,pageNum,pagesize));
}


}
