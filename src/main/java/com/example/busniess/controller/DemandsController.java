package com.example.busniess.controller;

import com.example.busniess.entity.DemandsEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.DemandsService;
import com.example.busniess.utiles.EchartsEntity;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
 * 企业需求表
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-24 10:05:26
 */
@Validated
@RestController
@RequestMapping("/demands")
public class DemandsController {
    @Autowired
    private DemandsService demandsService;

    /**
     * 分页展示,可根据条件筛选(企业端)
     * @param demandsEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/showByPage")
    public ReturnResult showByPage(DemandsEntity demandsEntity,Integer pageNum,Integer pageSize){
        if(pageNum==null||pageSize==null){
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        }
        PageInfo pageInfo = demandsService.showByPage(demandsEntity,pageNum,pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 分页展示,可根据条件筛选(管理端)
     * @param demandsEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/showByPageForManager")
    public ReturnResult showByPageForManager(DemandsEntity demandsEntity,Integer pageNum,Integer pageSize){
        if(pageNum==null||pageSize==null){
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        }
        PageInfo pageInfo = demandsService.showByPageForManager(demandsEntity,pageNum,pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id获取需求详情
     * @param id    需求id
     * @return
     */
    @RequestMapping("/getDemandsByID")
    public ReturnResult getDemandsByID(Integer id){
        if(id==null){
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        }
        DemandsEntity demandsEntity = demandsService.getByID(id);
        if(demandsEntity!=null){
            return ReturnResult.success(demandsEntity);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }

    /**
     * 新增需求
     * @param demandsEntity
     * @return
     */
    @PostMapping("/addDemands")
    public ReturnResult addDemands(@Validated({UserValidator.InSet.class}) DemandsEntity demandsEntity){
        if(demandsEntity.getCreateTime()==null){
            demandsEntity.setCreateTime(new Date());
        }
        if(demandsEntity.getUpdateTime()==null){
            demandsEntity.setUpdateTime(new Date());
        }
        if(demandsEntity.getStatus()==null){
            demandsEntity.setStatus(0);
        }
        if(demandsEntity.getApprovalStatus()==null){
            demandsEntity.setApprovalStatus(0);
        }
        if(demandsService.insert(demandsEntity)){
            return ReturnResult.success();
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id删除需求
     * @param id
     * @return
     */
    @RequestMapping(value="/delDemands",method = {RequestMethod.DELETE,RequestMethod.GET})
    public ReturnResult delDemands(Integer id){
        if(id==null){
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        }
        if(demandsService.deleteDemandsByID(id)){
            return ReturnResult.success();
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求
     * @param demandsEntity
     * @return
     */
    @RequestMapping(value="/updateDemands",method = {RequestMethod.POST})
    public ReturnResult updateDemands(DemandsEntity demandsEntity){
        if(demandsService.updateDemands(demandsEntity)){
            return ReturnResult.success();
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求有效状态
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/updateDemandsStatus")
    public ReturnResult updateDemandsStatus(Integer status,Integer id){
        if(id==null||status==null){
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        }
        if(demandsService.updateDemandsStatus(status,id)){
            return ReturnResult.success();
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改需求审批状态
     * @param approvalStatus
     * @param id
     * @return
     */
    @PostMapping("/updateDemandsApprovalStatus")
    public ReturnResult updateDemandsApprovalStatus(Integer approvalStatus,String approvalOpinion,Integer id){
        if(id==null||approvalStatus==null){
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        }
        if(approvalStatus==1){  //审批通过后清空审批意见
            approvalOpinion="";
        }
        if(demandsService.updateDemandsApprovalStatus(approvalStatus,approvalOpinion,id)){
            return ReturnResult.success();
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 企业需求行业占比统计(饼图)
     * @return data.ldata legend数据,即legend.data需要的数据;data.sdata 对应x轴的数据,即series[0].data需要的数据
     */
    @RequestMapping(value="/demandsIndustryProp",method = {RequestMethod.POST,RequestMethod.GET})
    public ReturnResult demandsIndustryProp(){
        List<DemandsEntity> list = demandsService.demandsIndustryProp();
        if(list.isEmpty()||"[]".equals(list.toString())){
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }else{
            Map<String, Object> map=new HashMap<String, Object>();
            List<EchartsEntity> sdata = new ArrayList<>();
            String[] ldata = new String[list.size()];
            for(int i =0;i<list.size();i++){
                EchartsEntity echartsEntity = new EchartsEntity();
                ldata[i]=list.get(i).getDemandIndustry();
                echartsEntity.setName(list.get(i).getDemandIndustry());
                echartsEntity.setValue(Double.valueOf(String.valueOf(list.get(i).getCounts())));
                sdata.add(echartsEntity);
            }
            map.put("sdata",sdata);
            map.put("ldata",ldata);
            return ReturnResult.success(map);
        }
    }

    /**
     * 企业需求增长趋势
     * @return  data.xdata x轴坐标数据,即xAxis.data需要的数据;data.sdata 对应x轴的数据,即series[0].data需要的数据
     */
    @RequestMapping(value="/demandsRiseTrend",method = {RequestMethod.POST,RequestMethod.GET})
    public ReturnResult demandsRiseTrend(){
        List<DemandsEntity> list = demandsService.demandsRiseTrend();
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

}
