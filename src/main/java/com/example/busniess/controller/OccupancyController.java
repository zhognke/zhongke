package com.example.busniess.controller;

import com.example.busniess.dao.OccupancyDao;
import com.example.busniess.entity.Echarts;
import com.example.busniess.entity.Occupancy;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.OccupancyService;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/occupancy")
@Validated
public class OccupancyController {

    @Resource
    OccupancyService occupancyServiceimpl;
    @Autowired
    OccupancyDao occupancyDao;

    /**
     * 查询具体的科技成果
     *
     * @param id
     * @return
     */
    @RequestMapping("/selectOneOccupancy")
    public ReturnResult selectOneOccupancy(@NotNull(message = "id不能为空") Integer id) {
        return ReturnResult.success(occupancyDao.selectOneOccupancy(id));
    }


    /**
     * 新建入住成果
     *
     * @param occupancy
     * @return
     */
    @RequestMapping("/addOccupancy")
    public ReturnResult addOccupancy(@Validated({UserValidator.InSet.class}) Occupancy occupancy) {
        if (occupancyServiceimpl.addOccupancy(occupancy)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }

    }

    /**
     * 删除入住成果
     */
    @RequestMapping("/delectOccupancy")
    public ReturnResult delectOccupancy(Integer id) {
        if (occupancyServiceimpl.delectOccupancy(id)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 查看自己发布的科技成果
     *
     * @param userName//用户姓名
     * @param pageNumber//第几页
     * @param pageSize//显示多少
     * @return
     */
    @RequestMapping("/examineMyOccupancy")
    public ReturnResult examineMyOccupancy(@NotBlank(message = "传入值不能为空") String userName, @Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pageNumber, @Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pageSize) {
        PageInfo o = occupancyServiceimpl.selectMyOccupancy(userName, pageNumber, pageSize);
        return ReturnResult.success(o);

    }

    /**
     * 显示所有能显示的内容
     *
     * @param pageNum//页数
     * @param pagesize//显示的数量
     * @return
     */
    @RequestMapping("/selectOnShowOccupancy")
    public ReturnResult selectOnShowOccupancy(@NotNull(message = "参数不能为空") @Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pageNum, @NotNull(message = "参数不能为空") @Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pagesize) {
        PageInfo pageInfo = occupancyServiceimpl.selectOnShowOccupancy(pageNum, pagesize);
        return ReturnResult.success(pageInfo);
    }


    /**
     * 更新发布状态
     *
     * @param kStatue
     * @param id
     * @return
     */
    @RequestMapping("/upKstatue")
    public ReturnResult upKstatue(@NotNull(message = "跟新状态不能为空") Integer kStatue, @NotNull(message = "发布成果的id不能为空") Integer id) {
        if (occupancyServiceimpl.upDateKstatue(kStatue, id)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.ISSUE_ERROR);
        }
    }

    /**
     * 根据行业显示科技成果
     */
    @RequestMapping("/selectByIndustry")
    public ReturnResult selectByIndustry(Occupancy occupancy, Integer pageNum, @NotNull(message = "参数不能为空") @Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pagesize) {

        return ReturnResult.success(occupancyServiceimpl.selectOccupancyByIndustry(occupancy, pageNum, pagesize));
    }

    /**
     * 折线图
     *
     * @return
     */
    @RequestMapping("/selectBrokenImg")
    public ReturnResult selectBrokenImg() {
        Echarts echarts = occupancyServiceimpl.returnBrokenImg();

        return ReturnResult.success(echarts);
    }

    /**
     * 饼状
     *
     * @return
     */
    @RequestMapping("/selectPieImg")
    public ReturnResult selectPieImg() {
        Echarts echarts = occupancyServiceimpl.returnPieImg();

        return ReturnResult.success(echarts);
    }

}
