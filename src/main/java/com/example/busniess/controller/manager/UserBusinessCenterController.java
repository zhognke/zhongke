package com.example.busniess.controller.manager;

import com.example.busniess.annotation.SysLog;
import com.example.busniess.dao.BusinessCenterDao;
import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.InformEntity;
import com.example.busniess.entity.Reject;
import com.example.busniess.entity.User;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.AdminUserService;
import com.example.busniess.service.BusinessCenterService;
import com.example.busniess.service.UserService;

import com.example.busniess.utiles.RabbitUtil;
import com.example.busniess.validator.UserValidator;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserBusinessCenterController {

    @Autowired
    BusinessCenterDao businessCenterDao;
    @Autowired
    RabbitTemplate rabbitTemplate;


    @Resource
    BusinessCenterService businessCenterServiceImpl;

    @Autowired
    AdminUserService adminUserServiceImpl;
    @Autowired
    UserService userServiceImpl;

    /**
     * 添加企业用户
     *
     * @param user
     * @param user
     * @return
     */
    @RequestMapping("/addAdminBusinessCenter")
    public ReturnResult addAdminBusinessCenter(User user) {
        if (adminUserServiceImpl.adminAddUser(user, user.getBusinessCenter())) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.ADD_LERROR);
    }

    /**
     * 删除用户
     * 企业和私人
     *
     * @param user 参数 id
     * @return
     */
    @RequestMapping("/delectAdminUser")
    public ReturnResult delectAdminUser(User user) {
        if (adminUserServiceImpl.adminDelletUser(user)) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.DELETE_ERROR);
    }

    /**
     * 修改企业用户信息
     */

    @RequestMapping("/upAdminBusinessUser")
    public ReturnResult upAdminBusinessUser(User user) {
        if (adminUserServiceImpl.adminUpUser(user, user.getBusinessCenter())) {
            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.UPDATE_ERROR);
    }

    /**
     * 按条件检索企业用户
     */
    @RequestMapping("/selectAdminBusinessUser")
    public ReturnResult selectAdminBusinessUser(BusinessCenter businessCenter, Integer pageNumber, Integer pageSize) {
        PageInfo p = userServiceImpl.searchBusinessUser(businessCenter, pageNumber, pageSize);
        return ReturnResult.success(p);
    }
/**
 * 审核
 */

    /**
     * 审核通过
     *
     * @param id       企业信息id
     * @param rid      角色
     * @param userName 用户名
     * @param statue   审核状态
     * @param reId     驳回原因id
     * @return
     */
    @SysLog(value = "审核企业认证-通过", type = "企业认证")
    @RequestMapping("/passTheAudit")
    public ReturnResult passTheAudit(@NotNull(message = "企业id不能为空") Integer id, @NotNull(message = "角色名不能为空") Integer rid, @NotNull(message = "用户名不能为空") String userName, @NotNull(message = "状态不能为空") Integer statue, @NotNull(message = "驳回原因id不能为空") Integer reId) {
        if (businessCenterServiceImpl.updateAuditStatue(id, rid, userName, statue, reId)) {
            //通知
            BusinessCenter businessCenter = businessCenterDao.selectBussinessByid(id);
            InformEntity informEntity = RabbitUtil.sendRabbic(userName, "提交的" + businessCenter.getFirmName() + "的企业认证已经通过", new Date());

            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);


            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUBMIT_ERROR);
    }

    /**
     * 驳回认证
     */
    @SysLog(value = "驳回企业认证", type = "企业认证")
    @RequestMapping("/dismissTheCertification")
    public ReturnResult dismissTheCertification(@Validated({UserValidator.InSet.class}) Reject reject) {
        if (businessCenterServiceImpl.rejectAudit(reject)) {
            //通知

            BusinessCenter businessCenter = businessCenterDao.selectBussinessByid(reject.getBId());
            InformEntity informEntity = RabbitUtil.sendRabbic(businessCenter.getUName(), "提交的" + businessCenter.getFirmName() + "的企业认证被驳回了请重新认证", new Date());

            rabbitTemplate.convertAndSend(RabbitUtil.EXCHANGE, RabbitUtil.USERKEY, informEntity);

            return ReturnResult.success();
        }
        return ReturnResult.erro(CodeMsg.SUBMIT_ERROR);
    }


}
