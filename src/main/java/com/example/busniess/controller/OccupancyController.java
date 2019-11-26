package com.example.busniess.controller;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.entity.*;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.BusinessInformationService;
import com.example.busniess.service.OccupancyService;
import com.example.busniess.utiles.RabbitUtil;
import com.example.busniess.utiles.ShiroUtils;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/occupancy")
@Validated
public class OccupancyController {
    //转义审核状态
    private String str;
    @Autowired
    BusinessInformationService usinessInformationServiceImpl;
    @Resource
    OccupancyService OccupancyServiceimpl;
    @Autowired
    RabbitTemplate rabbitTemplate;
//    @Autowired
//    OccupancyDao occupancyDao;

    /**
     * 查询具体的科技成果
     *
     * @param id
     * @return
     */
//    @RequestMapping("/selectOneOccupancy")
//    public ReturnResult selectOneOccupancy(@NotNull(message = "id不能为空") Integer id) {
//        return ReturnResult.success(occupancyDao.selectOneOccupancy(id));
//    }


    /**
     * 新建入住成果
     *
     * @param occupancy
     * @return
     */
    @SysLog(value = "新增科技成果", type = "科技成果")
    @RequestMapping("/addOccupancy")
    public ReturnResult addOccupancy(@Validated({UserValidator.InSet.class}) Occupancy occupancy) {


        if (OccupancyServiceimpl.addOccupancy(occupancy)) {
            //通知
            InformEntity informEntity = new InformEntity();//创建消息
            informEntity.setUserName(occupancy.getUserName());
            informEntity.setCount("发布了" + occupancy.getResultTechnolo() + "待审核");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
            informEntity.setTime(df.format(new Date()));
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);


            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }

    }

    /**
     * 删除入住成果
     */
    @SysLog(value = "删除科技成果", type = "科技成果")
    @RequestMapping("/delectOccupancy")
    public ReturnResult delectOccupancy(Integer id) {
        if (OccupancyServiceimpl.delectOccupancy(id)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 查看自己发布的科技成果
     *
     * @param pageNumber//第几页
     * @param pageSize//显示多少
     * @return
     */
    @RequestMapping("/examineMyOccupancy")
    public ReturnResult examineMyOccupancy(@Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pageNumber, @Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pageSize) {
        String userName = ShiroUtils.getUserName();
        if (userName == null) {
            return ReturnResult.erro(CodeMsg.NOT_HAVE_LIMITS);
        }
        PageInfo o = OccupancyServiceimpl.selectMyOccupancy(userName, pageNumber, pageSize);
        return ReturnResult.success(o);
    }

    /**
     * 查看具体的科技成果
     */
    @RequestMapping("/selectOneOccupancyById")
    public ReturnResult selectOneOccupancyById(Integer id) {
        Occupancy occupancy = OccupancyServiceimpl.seleOccupancyById(id);
        return ReturnResult.success(occupancy);
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
        PageInfo pageInfo = OccupancyServiceimpl.selectOnShowOccupancy(pageNum, pagesize);
        return ReturnResult.success(pageInfo);
    }


    /**
     * 更新发布状态
     *
     * @param kStatue
     * @param id
     * @return
     */
    @SysLog(value = "修改科技成果状态", type = "科技成果")
    @RequestMapping("/upKstatue")
    public ReturnResult upKstatue(@NotNull(message = "跟新状态不能为空") Integer kStatue, @NotNull(message = "发布成果的id不能为空") Integer id) {
        if (OccupancyServiceimpl.upDateKstatue(kStatue, id)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.ISSUE_ERROR);
        }
    }

    /**
     * 根据行业显示科技成果
     * industry
     */
    @RequestMapping("/selectByIndustry")
    public ReturnResult selectByIndustry(Occupancy occupancy, @NotNull(message = "参数不能为空") @Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pageNum, @NotNull(message = "参数不能为空") @Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pagesize) {

        return ReturnResult.success(OccupancyServiceimpl.selectOccupancyByIndustry(occupancy, pageNum, pagesize));
    }

    /**
     * 根据状态查看
     *
     * @param statue
     * @return
     */
    @RequestMapping("/selectBystatue")
    public ReturnResult selectBystatue(Integer statue) {
        return ReturnResult.success(OccupancyServiceimpl.seleOccupancyByStatue(statue));
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
    public ReturnResult updateOccupancyStatue(Integer statue, Integer id, @RequestParam(required = false) String reject) {
        if (statue == 1) {
            str = "审核通过";
        }
        str = "审核驳回";


        if (OccupancyServiceimpl.updateStatue(statue, id, reject)) {
//通知
            Occupancy occupancy = OccupancyServiceimpl.seleOccupancyById(id);
            InformEntity informEntity = new InformEntity();//创建消息
            informEntity.setUserName(occupancy.getUserName());
            informEntity.setCount(occupancy.getResultTechnolo() + str);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
            informEntity.setTime(df.format(new Date()));
            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);


            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);
    }

    /**
     * 修改科技成果入住信息
     *
     * @param occupancy
     * @return
     */
    @SysLog(value = "修改科技成果", type = "科技成果")
    @RequestMapping("/updateOccupancy")
    public ReturnResult updateOccupancy(Occupancy occupancy) {
        if (OccupancyServiceimpl.updateOccupancy(occupancy)) {
            return ReturnResult.success();
        }


        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);
    }


    /**
     * 折线图
     *
     * @return
     */
    @RequestMapping("/selectBrokenImg")
    public ReturnResult selectBrokenImg() {
        Echarts echarts = OccupancyServiceimpl.returnBrokenImg();

        return ReturnResult.success(echarts);
    }

    /**
     * 饼状
     *
     * @return
     */
    @RequestMapping("/selectPieImg")
    public ReturnResult selectPieImg() {
        Echarts echarts = OccupancyServiceimpl.returnPieImg();

        return ReturnResult.success(echarts);
    }

    /**
     * 分页展示(大厅,包含检索功能)
     *
     * @param occupancy
     * @param pageNum
     * @param pagesize
     * @return
     */
    @RequestMapping("/showByPage")
    public ReturnResult showByPage(Occupancy occupancy, @RequestParam(defaultValue = "1") @Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pageNum, @RequestParam(defaultValue = "9") @Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pageSize) {
        if (occupancy.getAdvantages() != null) {
            occupancy.setAdvantages(occupancy.getAdvantages().replaceAll(",", "','"));
        }
        if (occupancy.getTransferType() != null) {
            occupancy.setTransferType(occupancy.getTransferType().replaceAll(",", "','"));
        }
        if (occupancy.getStage() != null) {
            occupancy.setStage(occupancy.getStage().replaceAll(",", "','"));
        }
        if (occupancy.getAttribute() != null) {
            occupancy.setAttribute(occupancy.getAttribute().replaceAll(",", "','"));
        }
        return ReturnResult.success(OccupancyServiceimpl.showByPage(occupancy, pageNum, pageSize));
    }

    /**
     * 分页展示(企业中心,包含检索功能)
     *
     * @param occupancy
     * @param pageNum
     * @param pagesize
     * @return
     */
    @RequestMapping("/showByPageForCenter")
    public ReturnResult showByPageForCenter(Occupancy occupancy, @RequestParam(defaultValue = "1") @Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pageNum, @RequestParam(defaultValue = "10") @Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pagesize) {
        String userName = ShiroUtils.getUserName();
        if (userName == null) {
            return ReturnResult.erro(CodeMsg.NOT_HAVE_LIMITS);
        } else {
            occupancy.setUserName(userName);
        }
        return ReturnResult.success(OccupancyServiceimpl.showByPageForCenter(occupancy, pageNum, pagesize));
    }

    /**
     * 热门行业
     *
     * @param size
     * @return
     */
    @GetMapping("/getHotIndustry")
    public ReturnResult getHotIndustry(@RequestParam(defaultValue = "8") Integer size) {
        return ReturnResult.success(OccupancyServiceimpl.getHotIndustry(size));
    }

    /**
     * 更新发布状态
     *
     * @param id
     * @param closeReason
     * @return
     */
    @SysLog(value = "关闭科技成果-企业中心", type = "科技成果")
    @RequestMapping("/closeById")
    public ReturnResult closeById(@NotNull(message = "发布成果的id不能为空") Integer id, @NotNull(message = "关闭原因不能为空") String closeReason) {
        if (OccupancyServiceimpl.closeById(id, closeReason)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.ISSUE_ERROR);
        }
    }

    /**
     * 更新发布状态
     *
     * @param id
     * @param closeReason
     * @return
     */
    @SysLog(value = "关闭科技成果-管理员", type = "科技成果")
    @RequestMapping("/closeByIdForManager")
    public ReturnResult closeByIdForManager(@NotNull(message = "发布成果的id不能为空") Integer id, @NotNull(message = "关闭原因不能为空") String closeReason) {
        if (OccupancyServiceimpl.closeByIdForManager(id, closeReason)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.ISSUE_ERROR);
        }
    }
}
