package com.example.busniess.controller;

import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("dataDisplay")
@RequestMapping("dataDisplay")
public class DataDisplayController {
    @Autowired
    private DemandsService demandsService;  //企业需求
    @Autowired
    private IndustrialDeclarationService industrialDeclarationService;  //工业申报
    @Autowired
    BusinessCenterInformationService businessCenterInformationService;  //企业信息
    @Autowired
    FinancingService financingService;  //工业融资
    @Autowired
    OccupancyService occupancyServce;   //科技成果
    @Autowired
    ProfessionalsService professionalsService;  //专家
    @Autowired
    private TalentDemandService talentDemandService;    //人才
    @Autowired
    private IntentionService intentionService;  //意向对接

    /*平台数据统计-start*/
    /**
     * 平台数据统计
     * @return
     */
    @GetMapping("dataCounts")
    public ReturnResult dataCounts() {
        Map<String, Integer> map = new HashMap<>();
        int companyNum = businessCenterInformationService.getCounts();
        int talentDemandsNum = talentDemandService.getCounts();
        int financingNum = financingService.getCounts();
        int demandsNum = demandsService.getCount(null);
        int occupancyNum = occupancyServce.getCounts();
        int intentionNum = intentionService.getCounts();
        map.put("companyNum",companyNum);
        map.put("talentDemandsNum",talentDemandsNum);
        map.put("financingNum",financingNum);
        map.put("demandsNum",demandsNum);
        map.put("occupancyNum",occupancyNum);
        map.put("intentionNum",intentionNum);
        return ReturnResult.success(map);
    }
    /*平台数据统计-end*/

    /*各类行业占比-pie-start*/
    /**
     * 企业需求行业占比统计(饼图)
     *
     * @return ReturnResult data.ldata legend数据,即legend.data需要的数据;data.sdata 对应x轴的数据,即series[0].data需要的数据
     */
    @GetMapping("/demandsIndustryProp")
    public ReturnResult demandsIndustryProp(@RequestParam(defaultValue = "16")Integer size) {
        Map<String, Object> map = demandsService.demandsIndustryProp(size);
        if(map!=null){
            return ReturnResult.success(map);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }
    /**
     * 人才需求行业占比统计(饼图)
     *
     * @return ReturnResult data.ldata legend数据,即legend.data需要的数据;data.sdata 对应x轴的数据,即series[0].data需要的数据
     */
    @GetMapping("/talentIndustryProp")
    public ReturnResult talentIndustryProp(@RequestParam(defaultValue = "16")Integer size) {
        Map<String,Object> map = talentDemandService.demandsIndustryProp(size);
        if(map!=null){
            return ReturnResult.success(map);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }
    /**
     * 科技成果行业占比统计(饼图)
     *
     * @return
     */
    @GetMapping("/occupancyIndustryProp")
    public ReturnResult occupancyIndustryProp(@RequestParam(defaultValue = "16") Integer size) {
        Map<String,Object> map = occupancyServce.getIndustryProp(size);
        if(map!=null){
            return ReturnResult.success(map);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }
    /**
     * 融资行业占比统计(饼图)
     *
     * @return ReturnResult data.ldata legend数据,即legend.data需要的数据;data.sdata 对应x轴的数据,即series[0].data需要的数据
     */
    @GetMapping("/financingIndustryProp")
    public ReturnResult getFinancingIndustryProp(@RequestParam(defaultValue = "16") Integer size) {
        Map<String,Object> map = financingService.getIndustryProp(size);
        if(map!=null){
            return ReturnResult.success(map);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }
    /*各类行业占比-end*/

    /*工业申报统计-start*/
    /**
     * 工业申报统计
     * @param year 年份
     * @return ReturnResult
     */
    @GetMapping("/getIndustrialDeclarationTypeByYear")
    public ReturnResult getIndustrialDeclarationTypeByYear(Integer year) {
        Map<String,Object> map = industrialDeclarationService.getIndustrialDeclarationTypeByYear(year);
        if(map!=null){
            return ReturnResult.success(map);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }
    /*工业申报统计-end*/

    /*数据增长统计-start*/
    /**
     * 平台数据增长趋势
     * @param type
     * @param dateType
     * @param size
     * @return
     */
    @GetMapping("/dataRiseTrend")
    public ReturnResult dataRiseTrend(String type,String dateType,Integer size) {
        Map<String,Object> map = null;
        if("demands".equalsIgnoreCase(type)){
            map = demandsService.demandsRiseTrend(dateType,size);
        }else if("financing".equalsIgnoreCase(type)){
            map = financingService.financingRiseTrend(dateType,size);
        }else if("talent".equalsIgnoreCase(type)){
            map = talentDemandService.demandsRiseTrend(dateType,size);
        }else{
            map = financingService.financingRiseTrend(dateType,size);
        }
        if(map!=null){
            return ReturnResult.success(map);
        }else{
            return ReturnResult.erro(CodeMsg.DATA_EMPTY);
        }
    }
    /*数据增长统计-end*/
}
