package com.example.busniess.controller.manager;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.entity.InformEntity;
import com.example.busniess.entity.Occupancy;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessCenterService;
import com.example.busniess.service.OccupancyService;
import com.example.busniess.utiles.RabbitUtil;
import com.example.busniess.validator.UserValidator;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Date;

@RequestMapping("/admin")
@RestController
public class OccupancyAdminController {
    //转义审核状态
    private String str;
    @Autowired
    OccupancyService occupancyServiceImpl;
    @Autowired
    BusinessCenterService businessCenterServiceImpl;
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 按条件搜索 科技成果
     *
     * @param occupancy
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectAdminOccupancy")
    public ReturnResult selectAdminOccupancy(Occupancy occupancy, Integer pageNumber, Integer pageSize) {

        return ReturnResult.success(occupancyServiceImpl.showByPageAdmin(occupancy, pageNumber, pageSize));
    }

    /**
     * 分配账号查询statue=1
     * 查询可以发布的账号
     */
    @RequestMapping("/selectAdminUser")
    public ReturnResult selectAdminUser(Integer statue) {
        return ReturnResult.success(businessCenterServiceImpl.selectBusinessCenter(statue));
    }

    /**
     * 更新科技成果审核状态
     *
     * @param statue
     * @param id
     * @return
     */
    @SysLog(value = "修改科技成果审核状态", type = "科技成果")
    @RequestMapping("/updateOccupancyStatue")
    public ReturnResult updateOccupancyStatue(@NotNull(message = "状态值不能为空") Integer statue,
                                              @NotNull(message = "id号不能为空") Integer id,
                                              @RequestParam(required = false) String reject) {
        if (statue == 1) {
            str = "审核通过";
        } else if (statue == 2) {
            str = "审核驳回";
        }


        if (occupancyServiceImpl.updateStatue(statue, id, reject)) {
//通知

            Occupancy occupancy = occupancyServiceImpl.seleOccupancyById(id);

            InformEntity informEntity = RabbitUtil.sendRabbic(occupancy.getUserName(), occupancy.getResultTechnolo() + str, new Date());
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);


            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);
    }

    /**
     * 设置为推荐热门
     * 1为热门
     * 其他的为null
     */
@RequestMapping("/setOccupancyAdminHot")
    public ReturnResult setOccupancyAdminHot(@RequestParam(required = false,defaultValue = "2") Integer hot,@NotNull(message = "id不能为空") Integer id) {
        String setHot;
        if (hot == 1) {
            setHot = "热门";
        } else {
            setHot = null;
        }
        if (occupancyServiceImpl.setAndresetHotOccupancy(setHot, id)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);

    }

    /**
     * 增加科技成果
     * @param occupancy
     * @return
     */
    @SysLog(value = "新增科技成果", type = "科技成果")
    @RequestMapping("/addOccupancy")
    public ReturnResult addOccupancy(
            @Validated({UserValidator.InSet.class})
                    Occupancy occupancy) {


        if (occupancyServiceImpl.addOccupancy(occupancy)) {
            //通知

//            InformEntity informEntity = RabbitUtil.sendRabbic(occupancy.getUserName(), "发布了" + occupancy.getResultTechnolo() + "待审核", new Date());

//            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);


            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }

    }

}
