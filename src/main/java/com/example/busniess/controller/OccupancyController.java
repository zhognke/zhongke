package com.example.busniess.controller;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.entity.Echarts;
import com.example.busniess.entity.InformEntity;
import com.example.busniess.entity.Occupancy;
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
    BusinessInformationService businessInformationServiceImpl;
    @Resource
    OccupancyService occupancyServiceImpl;
    @Autowired
    RabbitTemplate rabbitTemplate;


    /**
     * 新建入住成果
     *
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

            InformEntity informEntity = RabbitUtil.sendRabbic(occupancy.getUserName(), "发布了" + occupancy.getResultTechnolo() + "待审核", new Date());

            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.ADMINkEY, informEntity);


            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }

    }

    /**
     * 删除入住成果
     * 管理员彻底删除
     */
    @SysLog(value = "删除科技成果", type = "科技成果")
    @RequestMapping("/delectOccupancy")
    public ReturnResult delectOccupancy(@NotNull(message = "id号不能为空") Integer id) {
        if (occupancyServiceImpl.delectOccupancy(id)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 查看自己发布的科技成果
     *可以检索
     * @param pageNumber//第几页
     * @param pageSize//显示多少
     * @return
     */
    @RequestMapping("/examineMyOccupancy")
    public ReturnResult examineMyOccupancy(Occupancy occupancy,
            @Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pageNumber,
            @Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pageSize) {
        String userName = ShiroUtils.getUserName();
       occupancy.setUserName(userName);
        if (userName == null) {
            return ReturnResult.erro(CodeMsg.NOT_HAVE_LIMITS);
        }
        PageInfo o = occupancyServiceImpl.selectMyOccupancy(occupancy, pageNumber, pageSize);
        return ReturnResult.success(o);
    }

    /**
     * 查看具体的科技成果
     */
    @RequestMapping("/selectOneOccupancyById")
    public ReturnResult selectOneOccupancyById(
            @NotNull(message = "id号不能为空")
                    Integer id) {
        Occupancy occupancy = occupancyServiceImpl.seleOccupancyById(id);
        return ReturnResult.success(occupancy);
    }

    /**
     * 显示所有能显示的内容
     * 没有检索条件
     * @param pageNum//页数
     * @param pagesize//显示的数量
     * @return
     */
    @RequestMapping("/selectOnShowOccupancy")
    public ReturnResult selectOnShowOccupancy(
            @NotNull(message = "参数不能为空") @Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pageNum,
            @NotNull(message = "参数不能为空") @Min(value = 1, message = "传入值必须是数字且不能小于1") Integer pagesize) {
        PageInfo pageInfo = occupancyServiceImpl.selectOnShowOccupancy(pageNum, pagesize);
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
    public ReturnResult upKstatue(@NotNull(message = "跟新状态不能为空")
                                          Integer kStatue,
                                  @NotNull(message = "发布成果的id不能为空")
                                          Integer id) {
        if (occupancyServiceImpl.upDateKstatue(kStatue, id)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.ISSUE_ERROR);
        }
    }



    /**
     * 根据状态查看
     *
     * @param statue
     * @return
     */
    @RequestMapping("/selectBystatue")
    public ReturnResult selectBystatue(@NotNull(message = "状态值不能为空") Integer statue) {
        return ReturnResult.success(occupancyServiceImpl.seleOccupancyByStatue(statue));
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
        }else if(statue==2){
        str = "审核驳回";}


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
     * 修改科技成果入住信息
     *
     * @param occupancy
     * @return
     */
    @SysLog(value = "修改科技成果", type = "科技成果")
    @RequestMapping("/updateOccupancy")
    public ReturnResult updateOccupancy(@Validated({UserValidator.InSet.class})
                                                Occupancy occupancy) {
        if (occupancyServiceImpl.updateOccupancy(occupancy)) {
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
        Echarts echarts = occupancyServiceImpl.returnBrokenImg();

        return ReturnResult.success(echarts);
    }

    /**
     * 饼状
     *
     * @return
     */
    @RequestMapping("/selectPieImg")
    public ReturnResult selectPieImg() {
        Echarts echarts = occupancyServiceImpl.returnPieImg();

        return ReturnResult.success(echarts);
    }

    /**
     * 分页展示(大厅,包含检索功能)
     * 默认都是审核通过的才能发布
     *
     * @param occupancy
     * @param pageNum
     * @param pageSize
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
        return ReturnResult.success(occupancyServiceImpl.showByPage(occupancy, pageNum, pageSize));
    }

    /**
     * 分页展示(企业中心,包含检索功能)1
     *搜索自己的
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
        return ReturnResult.success(occupancyServiceImpl.showByPageForCenter(occupancy, pageNum, pagesize));
    }

    /**
     * 热门行业
     *
     * @param size
     * @return
     */
    @GetMapping("/getHotIndustry")
    public ReturnResult getHotIndustry(@RequestParam(defaultValue = "6") Integer size) {
        return ReturnResult.success(occupancyServiceImpl.getHotIndustry(size));
    }

    /**
     * 用户结束发布科技成果
     *
     * @param id
     * @param closeReason
     * @return
     */
    @SysLog(value = "关闭科技成果-企业中心", type = "科技成果")
    @RequestMapping("/closeById")
    public ReturnResult closeById(@NotNull(message = "发布成果的id不能为空") Integer id, @NotNull(message = "关闭原因不能为空") String closeReason) {
        if (occupancyServiceImpl.closeById(id, closeReason)) {
            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.ISSUE_ERROR);
        }
    }

    /**
     * 管理员驳回1
     * @param id
     * @param closeReason
     * @return
     */
    @SysLog(value = "关闭科技成果-管理员", type = "科技成果")
    @RequestMapping("/closeByIdForManager")
    public ReturnResult closeByIdForManager(@NotNull(message = "发布成果的id不能为空") Integer id, @NotNull(message = "关闭原因不能为空") String closeReason) {
        if (occupancyServiceImpl.closeByIdForManager(id, closeReason)) {

            return ReturnResult.success();
        } else {
            return ReturnResult.erro(CodeMsg.ISSUE_ERROR);
        }
    }





}
