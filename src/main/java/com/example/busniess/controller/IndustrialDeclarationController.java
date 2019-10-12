package com.example.busniess.controller;

import com.example.busniess.entity.IndustrialDeclarationEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.IndustrialDeclarationService;
import com.example.busniess.utiles.EchartsEntity;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 工业项目投资导向计划申报
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-28 10:31:37
 */
@Validated
@RestController
@RequestMapping("/industrialdeclaration")
public class IndustrialDeclarationController {

    @Autowired
    private IndustrialDeclarationService industrialDeclarationService;

    /**
     * 新增申报
     * @param industrialDeclarationEntity
     * @return
     */
    @PostMapping("/addDeclaration")
    public ReturnResult addDeclaration(@Validated({UserValidator.InSet.class}) IndustrialDeclarationEntity industrialDeclarationEntity){
        if(industrialDeclarationService.add(industrialDeclarationEntity)){
            return ReturnResult.success("添加成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @RequestMapping(value="/deleteById",method = {RequestMethod.DELETE,RequestMethod.GET})
    public ReturnResult deleteById(Integer id){
        if(industrialDeclarationService.delectById(id)){
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
    @RequestMapping(value="/realDeleteById",method = {RequestMethod.DELETE,RequestMethod.GET})
    public ReturnResult realDeleteById(Integer id){
        if(industrialDeclarationService.realDeleteById(id)){
            return ReturnResult.success("删除成功");
        }else{
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改申报信息
     * @param industrialDeclarationEntity
     * @return
     */
    @PostMapping("/updateDeclaration")
    public ReturnResult updateDeclaration(@Validated({UserValidator.UpDate.class}) IndustrialDeclarationEntity industrialDeclarationEntity){
        if(industrialDeclarationService.update(industrialDeclarationEntity)){
            return ReturnResult.success("修改成功");
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
    public ReturnResult updateStatus(Integer id,Integer status){
        if(industrialDeclarationService.updateStatus(id,status)){
            return ReturnResult.success("修改成功");
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
            return ReturnResult.success("修改成功");
        }else{
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
    public ReturnResult showByPage(IndustrialDeclarationEntity industrialDeclarationEntity,Integer pageNum,Integer pageSize){
        if(pageNum==null||pageSize==null){
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
        }
        PageInfo pageInfo = industrialDeclarationService.showByPage(industrialDeclarationEntity,pageNum,pageSize);
        return ReturnResult.success(pageInfo);
    }

    /**
     * 根据id搜索
     * @param id
     * @return
     */
    @RequestMapping(value="getById",method = RequestMethod.GET)
    public ReturnResult getById(Integer id){
        if(id==null){
            return ReturnResult.erro(CodeMsg.BIND_ERROR);
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
