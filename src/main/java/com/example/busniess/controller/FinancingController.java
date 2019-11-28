package com.example.busniess.controller;

import com.example.busniess.entity.FinancingEntity;
import com.example.busniess.entity.InformEntity;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.FinancingService;
import com.example.busniess.utiles.RabbitUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/Financing")
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
    public ReturnResult addFinancing(FinancingEntity financing) {
        if (financingServiceImpl.insertFinacing(financing)) {
            //通知
            InformEntity informEntity = new InformEntity();//创建消息
            informEntity.setUserName(financing.getUName());
            informEntity.setCount("发布了" + financing.getProjectName());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
            informEntity.setTime(df.format(new Date()));
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);
            return ReturnResult.success();
        }
        return ReturnResult.success(CodeMsg.SUBMIT_ERROR);
    }

    /**
     * 删除融资
     */
    @RequestMapping("/delectFinancing")
    public ReturnResult delectFinancing(Integer id) {
        if (financingServiceImpl.delectFinacing(id)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELETE_ERROR);
    }

    /**
     * 修改融资
     */
    @RequestMapping("/updateFinancing")
    public ReturnResult updateFinancing(FinancingEntity financing) {
        if (financingServiceImpl.updateFinacing(financing)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);

    }

    /**
     * 查询自己的
     */
    @RequestMapping("/findMyFinancing")
    public ReturnResult findMyFinancing(String uName, Integer pagenum, Integer pageSize) {
        return ReturnResult.success(financingServiceImpl.selectMyFiancing(uName, pagenum, pageSize));

    }

    /**
     * 根据条件查询
     */
    @RequestMapping("/findFinancingByCondition")
    public ReturnResult findFinancingByCondition(FinancingEntity financing, Integer pagenum, Integer pageSize) {
        return ReturnResult.success(financingServiceImpl.SelectAllFinacing(financing, pagenum, pageSize));
    }

    /**
     * 修改状态
     */
    @RequestMapping("/upFinancingStatue")
    public ReturnResult upFinancingStatue(Integer id, Integer statue, @RequestParam(required = false) String reject) {
        if (statue == 1) {
            str = "审核通过了";
        } else {
            str = "审核被驳回";
        }


        if (financingServiceImpl.updateFinacingStatue(id, statue, reject)) {

            //通知 1通过 2驳回
            //通知
            FinancingEntity financing = financingServiceImpl.selectFinancingById(id);
            InformEntity informEntity = new InformEntity();//创建消息
            informEntity.setUserName(financing.getUName());
            informEntity.setCount(financing.getProjectName() + str);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
            informEntity.setTime(df.format(new Date()));
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
    public ReturnResult selectOneById(Integer id) {
        return ReturnResult.success(financingServiceImpl.selectFinancingById(id));

    }

}
