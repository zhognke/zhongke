package com.example.busniess.controller;

import com.example.busniess.entity.FinancingEntity;
import com.example.busniess.entity.InformEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.FinancingService;
import com.example.busniess.utiles.RabbitUtil;
import com.example.busniess.validator.UserValidator;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/Financing")
@Validated
public class FinancingController {
    //审核通过或者没通过的转义
    private String str;
    @Autowired
RabbitTemplate rabbitTemplate;

    @Resource
    FinancingService financingServiceImpl;

    /**
     * 添加融资
     *
     * @param financing
     * @return
     */
    @RequestMapping("/addFinancing")
    public ReturnResult addFinancing(@Validated(UserValidator.InSet.class)
                                                 FinancingEntity financing) {
        if (financingServiceImpl.insertFinacing(financing)) {
            //通知

           InformEntity informEntity= RabbitUtil.sendRabbic(financing.getUName(),"发布了" +
                    financing.getProjectName(),new Date());

            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);
            return ReturnResult.success();
        }
        return ReturnResult.success(CodeMsg.SUBMIT_ERROR);
    }

    /**
     * 删除融资
     */
    @RequestMapping("/delectFinancing")
    public ReturnResult delectFinancing(@NotNull(message = "id号不能为空") Integer id) {
        if (financingServiceImpl.delectFinacing(id)) {
            //通知



            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELETE_ERROR);
    }

    /**
     * 修改融资
     */
    @RequestMapping("/updateFinancing")
    public ReturnResult updateFinancing(@Validated({UserValidator.UpDate.class})
                                                    FinancingEntity financing) {
        if (financingServiceImpl.updateFinacing(financing)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);

    }

    /**
     * 查询自己的
     */
    @RequestMapping("/findMyFinancing")
    public ReturnResult findMyFinancing(@NotNull(message = "用户名不能为空")
                                                    String uName,
                                        @RequestParam(required = false,defaultValue = "1")
                                        Integer pagenum,
                                        @RequestParam(required = false,defaultValue = "5")
                                        Integer pageSize) {
        return ReturnResult.success(financingServiceImpl.selectMyFiancing(uName, pagenum, pageSize));

    }

    /**
     * 根据条件查询
     */
    @RequestMapping("/findFinancingByCondition")
    public ReturnResult findFinancingByCondition(FinancingEntity financing,
                                                 @RequestParam(required = false,defaultValue = "1")
                                                 Integer pagenum,
                                                 @RequestParam(required = false,defaultValue = "5")
                                                 Integer pageSize) {
        return ReturnResult.success(financingServiceImpl.SelectAllFinacing(financing, pagenum, pageSize));
    }

    /**
     * 修改状态
     */
    @RequestMapping("/upFinancingStatue")
    public ReturnResult upFinancingStatue(@NotNull(message = "id号不能为空")
                                                      Integer id,
                                          @NotNull(message = "状态号不能为空")
                                          Integer statue,
                                          @RequestParam(required = false) String reject) {
        if (statue == 1) {
            str = "审核通过了";
        } else if(statue==2) {
            str = "审核被驳回";
        }else if(statue==3){
            str ="信息结束";
        }


        if (financingServiceImpl.updateFinacingStatue(id, statue, reject)) {

            //通知 1通过 2驳回
            //通知
            FinancingEntity financing = financingServiceImpl.selectFinancingById(id);
            InformEntity informEntity= RabbitUtil.sendRabbic(financing.getUName(),financing.getProjectName() + str,new Date());


            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);

            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.ISSUE_ERROR);
    }

    /**
     * 查看具体
     *
     * @param id
     * @return
     */
    @RequestMapping("/selectOneById")
    public ReturnResult selectOneById(@NotNull(message = "id号不能为空") Integer id) {
        return ReturnResult.success(financingServiceImpl.selectFinancingById(id));

    }


    /**
     * 融资热门行业
     */
    @RequestMapping("/selectIndustry")
    public  ReturnResult selectIndustry(){
        return ReturnResult.success(financingServiceImpl.selectIndustry());
    }


}
