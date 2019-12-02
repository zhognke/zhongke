package com.example.busniess.controller;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.entity.DemandsEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessCenterService;
import com.example.busniess.service.DemandsService;
import com.example.busniess.utiles.EchartsEntity;
import com.example.busniess.utiles.ShiroUtils;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 企业需求表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-24 10:05:26
 */
@Api(tags="企业需求控制类")
@RestController
@RequestMapping("/demands")
public class DemandsController {
    @Autowired
    private DemandsService demandsService;

    @Resource
    BusinessCenterService businessCenterService;

    /**
     * 分页展示,可根据条件筛选(用户端)
     *
     * @param demandsEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/showByPage",method = RequestMethod.GET)
    public ReturnResult showByPage(DemandsEntity demandsEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "9") Integer pageSize) {
        demandsEntity.setStatus(0);
        demandsEntity.setApprovalStatus(1);
        PageInfo pageInfo = demandsService.showByPage(demandsEntity, pageNum, pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 企业中心-分页展示,可根据条件筛选(企业端)
     *
     * @param demandsEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value="企业中心-分页展示,可根据条件筛选(企业端)",notes="测试阶段需要传入用户名")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userName",value="当前登录的用户名",dataType="String"),
            @ApiImplicitParam(name="keyWord",value="搜索关键字",dataType="String"),
            @ApiImplicitParam(name="pageNum",value="当前页码",dataType="Integer"),
            @ApiImplicitParam(name="pageSize",value="每页展示条数",dataType="Integer"),
            @ApiImplicitParam(name="demandIndustry",value="(搜索条件)需求行业",dataType="Integer"),
            @ApiImplicitParam(name="preInvestmentAmountBegin",value="(搜索条件)预投金额最小值",dataType="BigDecimal"),
            @ApiImplicitParam(name="preInvestmentAmountEnd",value="(搜索条件)预投金额最大值",dataType="BigDecimal")
    })
    @RequestMapping(value="/showByPageForCenter",method = RequestMethod.GET)
    public ReturnResult showByPageForCenter(DemandsEntity demandsEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        String userName = ShiroUtils.getUserName();
        if (userName == null) {
            return ReturnResult.erro(CodeMsg.LOGIN_TIME_OUT);
        }else{
            demandsEntity.setUserName(userName);
        }
        PageInfo pageInfo = demandsService.showByPage(demandsEntity, pageNum, pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 后台管理-分页展示,可根据条件筛选(管理端)
     *
     * @param demandsEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/showByPageForManager",method = RequestMethod.GET)
    public ReturnResult showByPageForManager(DemandsEntity demandsEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageInfo pageInfo = demandsService.showByPage(demandsEntity, pageNum, pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id获取需求详情
     * @param id 需求id
     * @return
     */
    @ApiOperation("根据id查询需求详情")
    @ApiImplicitParam(name="id",value="需求id",dataType = "Integer")
    @RequestMapping(value = "/getDemandsByID", method = {RequestMethod.GET})
    public ReturnResult getDemandsByID(Integer id) {
        if (id == null) {
            return ReturnResult.erro(CodeMsg.DATA_FAIL);
        }
        DemandsEntity demandsEntity = demandsService.getByID(id);
        if (demandsEntity != null) {
            return ReturnResult.success(demandsEntity);
        } else {
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }

    /**
     * 最新需求
     *
     * @return
     */
    @RequestMapping(value = "/lastDemandsShow", method = {RequestMethod.GET})
    public ReturnResult lastDemandsShow(@RequestParam(defaultValue = "14")Integer size) {
        List<DemandsEntity> list = demandsService.lastDemandsShow(size);
        if (list.size() > 0) {
            return ReturnResult.success(list);
        } else {
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }

    /**
     * 热门需求版块
     *
     * @return
     */
    @RequestMapping(value = "/hotDemandsIndustry", method = {RequestMethod.GET})
    public ReturnResult hotDemandsIndustry(@RequestParam(defaultValue = "8")Integer size) {
        List<String> list = demandsService.hotDemandsIndustry(size);
        if (list.size() > 0) {
            return ReturnResult.success(list);
        } else {
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }

    /**
     * 新增需求
     *
     * @param demandsEntity
     * @return
     */
    @SysLog(value="新增企业需求",type="企业需求")
    @PostMapping("/addDemands")
    public ReturnResult addDemands(@Validated({UserValidator.InSet.class}) DemandsEntity demandsEntity) {
        /*String userName = ShiroUtils.getUserName();
        if (userName == null) {
            return ReturnResult.erro(CodeMsg.NOT_HAVE_LIMITS);  //判断当前用户是否登录
        }else{
            demandsEntity.setUserName(userName);
        }*/
        if (demandsService.insert(demandsEntity)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id删除需求
     *
     * @param id
     * @return
     */
    @SysLog(value="删除企业需求",type="企业需求")
    @RequestMapping(value = "/delDemands", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ReturnResult delDemands(Integer id) {
        if (id == null) {
            return ReturnResult.erro(CodeMsg.DATA_FAIL);
        }
        if (demandsService.deleteDemandsByID(id)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value="/deleteByBatch",method = {RequestMethod.DELETE,RequestMethod.POST})
    public ReturnResult deleteByBatch(@NotNull(message = "参数不能为空")String ids){
        if(demandsService.deleteBatch(ids)){
            return ReturnResult.success("删除成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id删除需求
     *
     * @param id
     * @return
     */
    @SysLog(value="彻底删除企业需求",type="企业需求")
    @RequestMapping(value = "/realDelDemands", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ReturnResult realDelDemands(Integer id) {
        if (id == null) {
            return ReturnResult.erro(CodeMsg.DATA_FAIL);
        }
        if (demandsService.realDeleteDemandsByID(id)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求
     *
     * @param demandsEntity
     * @return
     */
    @SysLog(value="修改企业需求",type="企业需求")
    @RequestMapping(value = "/updateDemands", method = {RequestMethod.POST})
    public ReturnResult updateDemands(@Validated({UserValidator.UpDate.class}) DemandsEntity demandsEntity) {
        demandsEntity.setApprovalStatus(0);
        if (demandsService.updateDemands(demandsEntity)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求有效状态
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/updateDemandsStatus")
    public ReturnResult updateDemandsStatus(Integer id,Integer status,String closeReason) {
        if (id == null || status == null) {
            return ReturnResult.erro(CodeMsg.DATA_FAIL);
        }
        if (demandsService.updateDemandsStatus(id,status,closeReason)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 关闭需求
     * @param id
     * @return
     */
    @SysLog(value="关闭企业需求-用户端",type="企业需求")
    @PostMapping("/closeDemandsById")
    public ReturnResult closeDemandsById(Integer id,String closeReason) {
        Integer status = 3;
        if (demandsService.updateDemandsStatus(id,status,closeReason)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 关闭需求
     * @param id
     * @return
     */
    @SysLog(value="关闭企业需求-管理端",type="企业需求")
    @PostMapping("/closeDemandsByIdForManager")
    public ReturnResult closeDemandsByIdForManager(Integer id,String reason) {
        Integer status = 2;
        if (demandsService.updateDemandsStatus(id,status,reason)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求审批状态-通过
     *
     * @param id
     * @return
     */
    @SysLog(value="审批企业需求-通过",type="企业需求")
    @PostMapping("/updateApprovalStatusPass")
    public ReturnResult updateApprovalStatusPass(Integer id) {
        Integer approvalStatus = 1;
        if (demandsService.updateDemandsApprovalStatus(approvalStatus, "", id)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求审批状态-驳回
     *
     * @param approvalOpinion 审批意见
     * @param id
     * @return
     */
    @SysLog(value="审批企业需求-驳回",type="企业需求")
    @PostMapping("/updateApprovalStatusRejected")
    public ReturnResult updateApprovalStatusRejected(Integer id, String approvalOpinion) {
        Integer approvalStatus = 2;
        if (demandsService.updateDemandsApprovalStatus(approvalStatus, approvalOpinion, id)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求审批状态
     *
     * @param approvalStatus
     * @param id
     * @return
     */
    @PostMapping("/updateApprovalStatus")
    public ReturnResult updateApprovalStatus(Integer approvalStatus, String approvalOpinion, Integer id) {
        if (id == null || approvalStatus == null) {
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        }
        if (approvalStatus == 1) {  //审批通过后清空审批意见
            approvalOpinion = "";
        }
        if (demandsService.updateDemandsApprovalStatus(approvalStatus, approvalOpinion, id)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 企业需求行业占比统计(饼图)
     *
     * @return data.ldata legend数据,即legend.data需要的数据;data.sdata 对应x轴的数据,即series[0].data需要的数据
     */
    @RequestMapping(value = "/demandsIndustryProp", method = {RequestMethod.POST, RequestMethod.GET})
    public ReturnResult demandsIndustryProp() {
        List<DemandsEntity> list = demandsService.demandsIndustryProp();
        if (list.isEmpty() || "[]".equals(list.toString())) {
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            List<EchartsEntity> sdata = new ArrayList<>();
            String[] ldata = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                EchartsEntity echartsEntity = new EchartsEntity();
                ldata[i] = list.get(i).getDemandIndustry();
                echartsEntity.setName(list.get(i).getDemandIndustry());
                echartsEntity.setValue(Double.valueOf(String.valueOf(list.get(i).getCounts())));
                sdata.add(echartsEntity);
            }
            map.put("sdata", sdata);
            map.put("ldata", ldata);
            return ReturnResult.success(map);
        }
    }

    /**
     * 企业需求增长趋势
     *
     * @return data.xdata x轴坐标数据,即xAxis.data需要的数据;data.sdata 对应x轴的数据,即series[0].data需要的数据
     */
    @RequestMapping(value = "/demandsRiseTrend", method = {RequestMethod.POST, RequestMethod.GET})
    public ReturnResult demandsRiseTrend() {
        List<DemandsEntity> list = demandsService.demandsRiseTrend();
        if (list.isEmpty() || "[]".equals(list.toString())) {
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            Integer[] sdata = new Integer[list.size()];
            String[] xdata = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                xdata[i] = list.get(i).getCompanyName();
                sdata[i] = list.get(i).getCounts();
            }
            map.put("sdata", sdata);
            map.put("xdata", xdata);
            return ReturnResult.success(map);
        }
    }

}
