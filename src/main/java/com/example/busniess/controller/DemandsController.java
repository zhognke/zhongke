package com.example.busniess.controller;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.entity.DemandsEntity;
import com.example.busniess.entity.InformEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.DemandsService;
import com.example.busniess.utiles.EchartsEntity;
import com.example.busniess.utiles.RabbitUtil;
import com.example.busniess.utiles.ShiroUtils;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;


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

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 分页展示,可根据条件筛选(用户端)
     *
     * @param demandsEntity    实体类
     * @param pageNum    页码
     * @param pageSize    页面尺寸
     * @return ReturnResult
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
     * @param demandsEntity    实体类
     * @param pageNum    页码
     * @param pageSize    页面尺寸
     * @return ReturnResult
     */
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
     * @param demandsEntity    实体类
     * @param pageNum    页码
     * @param pageSize    页面尺寸
     * @return ReturnResult
     */
    @RequestMapping(value="/showByPageForManager",method = RequestMethod.GET)
    public ReturnResult showByPageForManager(DemandsEntity demandsEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageInfo pageInfo = demandsService.showByPage(demandsEntity, pageNum, pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id获取需求详情
     * @param id    主键id 需求id
     * @return ReturnResult
     */
    @RequestMapping(value = "/getDemandsByID", method = {RequestMethod.GET})
    public ReturnResult getDemandsByID(@NotNull(message = "参数不能为空") Integer id) {
        DemandsEntity demandsEntity = demandsService.getByID(id);
        if (demandsEntity != null) {
            return ReturnResult.success(demandsEntity);
        } else {
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }

    /**
     * 根据id显示需求详情(大厅)
     * @param id    主键id 需求id
     * @return ReturnResult
     */
    @RequestMapping(value = "/showDemandsByID", method = {RequestMethod.GET})
    public ReturnResult showDemandsByID(@NotNull(message = "参数不能为空") Integer id) {
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
     * @return ReturnResult
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
     * @return ReturnResult
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
     * @param demandsEntity    实体类
     * @return ReturnResult
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
            //通知
            InformEntity informEntity= RabbitUtil.sendRabbic(demandsEntity.getUserName(),"提交了" + demandsEntity.getDemandOutline() + "的企业需求",new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id删除需求
     *
     * @param id    主键id
     * @return ReturnResult
     */
    @SysLog(value="删除企业需求",type="企业需求")
    @RequestMapping(value = "/delDemands", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ReturnResult delDemands(@NotNull(message = "参数不能为空") Integer id) {
        if (demandsService.deleteDemandsByID(id)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 批量删除
     * @param ids   主键ids(多个id用英文逗号分隔)
     * @return ReturnResult
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
     * @param id    主键id
     * @return ReturnResult
     */
    @SysLog(value="彻底删除企业需求",type="企业需求")
    @RequestMapping(value = "/realDelDemands", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ReturnResult realDelDemands(@NotNull(message = "参数不能为空") Integer id) {
        if (demandsService.realDeleteDemandsByID(id)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求
     *
     * @param demandsEntity    实体类
     * @return ReturnResult
     */
    @SysLog(value="修改企业需求",type="企业需求")
    @RequestMapping(value = "/updateDemands", method = {RequestMethod.POST})
    public ReturnResult updateDemands(@Validated({UserValidator.UpDate.class}) DemandsEntity demandsEntity) {
        demandsEntity.setApprovalStatus(0);
        if (demandsService.updateDemands(demandsEntity)) {
            //通知
            InformEntity informEntity= RabbitUtil.sendRabbic(demandsEntity.getUserName(),"修改了" + demandsEntity.getDemandOutline() + "的企业需求",new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求有效状态
     *
     * @param id    主键id
     * @param status    状态
     * @return ReturnResult
     */
    @PostMapping("/updateDemandsStatus")
    public ReturnResult updateDemandsStatus(@NotNull(message = "参数不能为空") Integer id,@NotNull(message = "参数不能为空") Integer status,String closeReason) {
        if (demandsService.updateDemandsStatus(id,status,closeReason)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 关闭需求--用户
     *
     * @param id    主键id
     * @return ReturnResult
     */
    @SysLog(value="关闭企业需求-用户端",type="企业需求")
    @PostMapping("/closeDemandsById")
    public ReturnResult closeDemandsById(@NotNull(message = "参数不能为空") Integer id,String closeReason) {
        Integer status = 3;
        if (demandsService.updateDemandsStatus(id,status,closeReason)) {
            //通知
            DemandsEntity demandsEntity = demandsService.getByID(id);
            InformEntity informEntity= RabbitUtil.sendRabbic(demandsEntity.getUserName(),"关闭了" + demandsEntity.getDemandOutline() + "的企业需求",new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 关闭需求--管理端
     *
     * @param id    主键id
     * @return ReturnResult
     */
    @SysLog(value="关闭企业需求-管理端",type="企业需求")
    @PostMapping("/closeDemandsByIdForManager")
    public ReturnResult closeDemandsByIdForManager(@NotNull(message = "参数不能为空") Integer id,String reason) {
        Integer status = 2;
        if (demandsService.updateDemandsStatus(id,status,reason)) {
            //通知
            DemandsEntity entity = demandsService.getByID(id);
            InformEntity informEntity = RabbitUtil.sendRabbic(entity.getUserName(), "提交的" + entity.getDemandOutline() + "的企业需求已被管理员关闭", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求审批状态-通过
     *
     * @param id    主键id
     * @return ReturnResult
     */
    @SysLog(value="审批通过",type="企业需求")
    @PostMapping("/updateApprovalStatusPass")
    public ReturnResult updateApprovalStatusPass(@NotNull(message = "参数不能为空") Integer id) {
        int approvalStatus = 1;
        if (demandsService.updateDemandsApprovalStatus(approvalStatus, "", id)) {
            //通知
            DemandsEntity entity = demandsService.getByID(id);
            InformEntity informEntity = RabbitUtil.sendRabbic(entity.getUserName(), "提交的" + entity.getDemandOutline() + "的企业需求已经审核通过", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求审批状态-驳回
     *
     * @param approvalOpinion 审批意见
     * @param id    主键id
     * @return ReturnResult
     */
    @SysLog(value="审批驳回",type="企业需求")
    @PostMapping("/updateApprovalStatusRejected")
    public ReturnResult updateApprovalStatusRejected(@NotNull(message = "参数不能为空") Integer id, String approvalOpinion) {
        int approvalStatus = 2;
        if (demandsService.updateDemandsApprovalStatus(approvalStatus, approvalOpinion, id)) {
            //通知
            DemandsEntity entity = demandsService.getByID(id);
            InformEntity informEntity = RabbitUtil.sendRabbic(entity.getUserName(), "提交的" + entity.getDemandOutline() + "的企业需求被驳回了", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求审批状态
     *
     * @param approvalStatus    审批状态
     * @param id    主键id
     * @return ReturnResult
     */
    @PostMapping("/updateApprovalStatus")
    public ReturnResult updateApprovalStatus(@NotNull(message = "参数不能为空") Integer approvalStatus, String approvalOpinion, @NotNull(message = "参数不能为空") Integer id) {
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
     * @return ReturnResult data.ldata legend数据,即legend.data需要的数据;data.sdata 对应x轴的数据,即series[0].data需要的数据
     */
    @RequestMapping(value = "/demandsIndustryProp", method = {RequestMethod.POST, RequestMethod.GET})
    public ReturnResult demandsIndustryProp() {
        List<DemandsEntity> list = demandsService.demandsIndustryProp();
        if (list.isEmpty() || "[]".equals(list.toString())) {
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        } else {
            Map<String, Object> map = new HashMap<>();
            List<EchartsEntity> sdata = new ArrayList<>();
            String[] ldata = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                EchartsEntity echartsEntity = new EchartsEntity();
                ldata[i] = list.get(i).getDemandIndustry();
                echartsEntity.setName(list.get(i).getDemandIndustry());
                echartsEntity.setValue(Double.parseDouble(String.valueOf(list.get(i).getCounts())));
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
     * @return ReturnResult data.xdata x轴坐标数据,即xAxis.data需要的数据;data.sdata 对应x轴的数据,即series[0].data需要的数据
     */
    @RequestMapping(value = "/demandsRiseTrend", method = {RequestMethod.POST, RequestMethod.GET})
    public ReturnResult demandsRiseTrend() {
        List<DemandsEntity> list = demandsService.demandsRiseTrend();
        if (list.isEmpty() || "[]".equals(list.toString())) {
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        } else {
            Map<String, Object> map = new HashMap<>();
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
