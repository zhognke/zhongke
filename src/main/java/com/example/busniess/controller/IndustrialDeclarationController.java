package com.example.busniess.controller;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.entity.BusinessCenterInformationEntity;
import com.example.busniess.entity.IndustrialDeclarationEntity;
import com.example.busniess.entity.InformEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessCenterInformationService;
import com.example.busniess.service.IndustrialDeclarationService;
import com.example.busniess.utiles.EchartsEntity;
import com.example.busniess.utiles.RabbitUtil;
import com.example.busniess.utiles.ShiroUtils;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;


/**
 * 工业项目投资导向计划申报
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-28 10:31:37
 */
@RestController
@RequestMapping("/industrialdeclaration")
public class IndustrialDeclarationController {

    @Autowired
    private IndustrialDeclarationService industrialDeclarationService;

    @Autowired
    BusinessCenterInformationService businessCenterInformationService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 新增申报
     * @param industrialDeclarationEntity
     * @return
     */
    @SysLog(value="新增工业申报",type="工业申报")
    @PostMapping("/addDeclaration")
    public ReturnResult addDeclaration(@Validated({UserValidator.InSet.class}) IndustrialDeclarationEntity industrialDeclarationEntity){
        String userName = ShiroUtils.getUserName();
        if (userName == null) {
            return ReturnResult.erro(CodeMsg.NOT_HAVE_LIMITS);  //判断当前用户是否登录
        }else{
            BusinessCenterInformationEntity businessCenter = businessCenterInformationService.selectOnByUname(userName);
            if(businessCenter==null||businessCenter.getStatue()!=1){    //判断当前用户是否通过企业认证
                return ReturnResult.erro(CodeMsg.ACCESS_DENIED);  //判断当前用户填写的企业名称是否与数据库一致
            }else{
            }
        }
        if(industrialDeclarationService.add(industrialDeclarationEntity)){

            BusinessCenterInformationEntity businessCenter = businessCenterInformationService.selectOneByCompanyName(industrialDeclarationEntity.getCompanyName());
            InformEntity informEntity = RabbitUtil.sendRabbic(businessCenter.getUname(), "提交了" + industrialDeclarationEntity.getProjectName() + "的工业申报", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);


            return ReturnResult.success("操作成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @SysLog(value="删除工业申报",type="工业申报")
    @RequestMapping(value="/deleteById",method = {RequestMethod.DELETE,RequestMethod.POST})
    public ReturnResult deleteById(Integer id){
        if(industrialDeclarationService.delectById(id)){
            return ReturnResult.success("操作成功");
        }else{
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
        if(industrialDeclarationService.deleteBatch(ids)){
            return ReturnResult.success("删除成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 彻底删除
     * @param id
     * @return
     */
    @SysLog(value="彻底删除工业申报",type="工业申报")
    @RequestMapping(value="/realDeleteById",method = {RequestMethod.DELETE,RequestMethod.POST})
    public ReturnResult realDeleteById(Integer id){
        if(industrialDeclarationService.realDeleteById(id)){
            return ReturnResult.success("操作成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改申报信息
     * @param industrialDeclarationEntity
     * @return
     */
    @SysLog(value="修改工业申报",type="工业申报")
    @PostMapping("/updateDeclaration")
    public ReturnResult updateDeclaration(@Validated({UserValidator.UpDate.class}) IndustrialDeclarationEntity industrialDeclarationEntity){
        if(industrialDeclarationService.update(industrialDeclarationEntity)){
            return ReturnResult.success("操作成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id修改申报有效状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/updateStatus")
    public ReturnResult updateStatus(Integer id,Integer status,String closeReason){
        if(industrialDeclarationService.updateStatus(id,status,closeReason)){
            return ReturnResult.success("操作成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }
    /**
     * 根据id关闭申报信息
     * @param id
     * @return
     */
    @SysLog(value="关闭工业申报",type="工业申报")
    @PostMapping("/closeById")
    public ReturnResult closeById(Integer id,String closeReason){
        Integer status =3;
        if(industrialDeclarationService.updateStatus(id,status,closeReason)){
            return ReturnResult.success("操作成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }
    /**
     * 根据id关闭申报信息-管理端
     * @param id
     * @return
     */
    @SysLog(value="关闭工业申报-管理端",type="工业申报")
    @PostMapping("/closeByIdForManager")
    public ReturnResult closeByIdForManager(Integer id,String closeReason){
        Integer status = 2;
        if(industrialDeclarationService.updateStatus(id,status,closeReason)){
            //通知
            IndustrialDeclarationEntity entity = industrialDeclarationService.selectById(id);
            BusinessCenterInformationEntity businessCenter = businessCenterInformationService.selectOneByCompanyName(entity.getCompanyName());
            InformEntity informEntity = RabbitUtil.sendRabbic(businessCenter.getUname(), "提交的" + entity.getProjectName() + "的工业申报已被管理员关闭", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);
            return ReturnResult.success("操作成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改申报审批状态
     * @param id
     * @param approvalStatus
     * @param approvalOpinion
     * @return
     */
    @PostMapping("/updateApprovalStatus")
    public ReturnResult updateApprovalStatus(Integer id,Integer approvalStatus,String approvalOpinion){
        if(industrialDeclarationService.updateApprovalStatus(id,approvalStatus,approvalOpinion)){
            return ReturnResult.success("操作成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改申报审批状态-通过
     *
     * @param id
     * @return
     */
    @SysLog(value="审批工业申报-通过",type="工业申报")
    @PostMapping("/updateApprovalStatusPass")
    public ReturnResult updateApprovalStatusPass(Integer id) {
        Integer approvalStatus = 1;
        if (industrialDeclarationService.updateApprovalStatus(id,approvalStatus, "")) {
            //通知
            IndustrialDeclarationEntity entity = industrialDeclarationService.selectById(id);
            BusinessCenterInformationEntity businessCenter = businessCenterInformationService.selectOneByCompanyName(entity.getCompanyName());
            InformEntity informEntity = RabbitUtil.sendRabbic(businessCenter.getUname(), "提交的" + entity.getProjectName() + "的工业申报已经审核通过", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改申报审批状态-驳回
     *
     * @param approvalOpinion 审批意见
     * @param id
     * @return
     */
    @SysLog(value="审批工业申报-驳回",type="工业申报")
    @PostMapping("/updateApprovalStatusRejected")
    public ReturnResult updateApprovalStatusRejected(Integer id, String approvalOpinion) {
        Integer approvalStatus = 2;
        if (industrialDeclarationService.updateApprovalStatus(id,approvalStatus, approvalOpinion)) {
            //通知
            IndustrialDeclarationEntity entity = industrialDeclarationService.selectById(id);
            BusinessCenterInformationEntity businessCenter = businessCenterInformationService.selectOneByCompanyName(entity.getCompanyName());
            InformEntity informEntity = RabbitUtil.sendRabbic(businessCenter.getUname(), "提交的" + entity.getProjectName() + "的工业申报被驳回了", new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }
    /**
     * 分页展示,可根据条件筛选
     * @param industrialDeclarationEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/showByPage",method = RequestMethod.GET)
    public ReturnResult showByPage(IndustrialDeclarationEntity industrialDeclarationEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "9")Integer pageSize){
        industrialDeclarationEntity.setStatus(0);
        industrialDeclarationEntity.setApprovalStatus(1);
        PageInfo pageInfo = industrialDeclarationService.showByPage(industrialDeclarationEntity,pageNum,pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 个人中心分页展示,可根据条件筛选
     * @param industrialDeclarationEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/showByPageForCenter",method = RequestMethod.GET)
    public ReturnResult showByPageForCenter(IndustrialDeclarationEntity industrialDeclarationEntity, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10")Integer pageSize){
        String userName = ShiroUtils.getUserName();
        if (ShiroUtils.isLogin()) {
            BusinessCenterInformationEntity businessCenterInformation = businessCenterInformationService.selectOnByUname(userName);
            if(businessCenterInformation==null){
                return ReturnResult.erro(CodeMsg.ACCESS_DENIED);
            }
            String companyName = businessCenterInformation.getCompanyName();
            industrialDeclarationEntity.setCompanyName(companyName);
        }else{
            return ReturnResult.erro(CodeMsg.NOT_HAVE_LIMITS);
        }
        PageInfo pageInfo = industrialDeclarationService.showByPage(industrialDeclarationEntity,pageNum,pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 显示最近的工业申报(16条)
     * @return
     */
    @RequestMapping(value="/lastDeclarations",method = RequestMethod.GET)
    public ReturnResult lastDeclarations( @RequestParam(defaultValue = "16")Integer size){
        List<IndustrialDeclarationEntity> list = industrialDeclarationService.lastDeclarations(size);
        return ReturnResult.success(list);
    }

    /**
     * 根据id搜索
     * @param id
     * @return
     */
    @RequestMapping(value="getById",method = RequestMethod.GET)
    public ReturnResult getById(Integer id){
        if(id==null){
            return ReturnResult.erro(CodeMsg.DATA_FAIL);
        }else{
            IndustrialDeclarationEntity obj = industrialDeclarationService.selectById(id);
            if(obj!=null){
                return ReturnResult.success(obj);
            }else{
                return ReturnResult.erro(CodeMsg.DATA_EMPTY);
            }
        }
    }


    /**
     * 工业申报行业占比统计(饼图)
     * @return data.ldata legend数据,即legend.data需要的数据;data.sdata 对应x轴的数据,即series[0].data需要的数据
     */
    @RequestMapping(value="/declartionsIndustryProp",method = {RequestMethod.POST,RequestMethod.GET})
    public ReturnResult declartionsIndustryProp(){
        List<IndustrialDeclarationEntity> list = industrialDeclarationService.declartionsIndustryProp();
        if(list.isEmpty()||"[]".equals(list.toString())){
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }else{
            Map<String, Object> map=new HashMap<String, Object>();
            List<EchartsEntity> sdata = new ArrayList<>();
            String[] ldata = new String[list.size()];
            for(int i =0;i<list.size();i++){
                EchartsEntity echartsEntity = new EchartsEntity();
                ldata[i]=list.get(i).getDeclarationType();
                echartsEntity.setName(list.get(i).getDeclarationType());
                echartsEntity.setValue(Double.valueOf(String.valueOf(list.get(i).getCounts())));
                sdata.add(echartsEntity);
            }
            map.put("sdata",sdata);
            map.put("ldata",ldata);
            return ReturnResult.success(map);
        }
    }

    /**
     * 工业申报增长趋势(折线图)
     * @return  data.xdata x轴坐标数据,即xAxis.data需要的数据;data.sdata 对应x轴的数据,即series[0].data需要的数据
     */
    @RequestMapping(value="/declartionsRiseTrend",method = {RequestMethod.POST,RequestMethod.GET})
    public ReturnResult declartionsRiseTrend(){
        List<IndustrialDeclarationEntity> list = industrialDeclarationService.declartionsRiseTrend();
        if(list.isEmpty()||"[]".equals(list.toString())){
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }else{
            Map<String, Object> map=new HashMap<String, Object>();
            Integer[] sdata = new Integer[list.size()];
            String[] xdata = new String[list.size()];
            for(int i =0;i<list.size();i++){
                xdata[i]=list.get(i).getCompanyName();
                sdata[i]=list.get(i).getCounts();
            }
            map.put("sdata",sdata);
            map.put("xdata",xdata);
            return ReturnResult.success(map);
        }
    }

    /**
     * 获取公司名称列表
     * @return
     */
    @RequestMapping(value="/getCompanyList",method = {RequestMethod.GET})
    public ReturnResult getCompanyList(){
        List<IndustrialDeclarationEntity> list = industrialDeclarationService.getCompanyList();
        if(list.isEmpty()||"[]".equals(list.toString())){
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }else{
            return ReturnResult.success(list);
        }
    }
}
