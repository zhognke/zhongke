package com.example.busniess.service;

import com.example.busniess.dao.BusinessCenterDao;
import com.example.busniess.dao.RejectDao;
import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.Reject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BusinessCenterServiceImplent implements BusinessCenterService {
    @Autowired
    UserDao userDao;
    @Autowired
    BusinessCenterDao businessCenterDao;
    @Autowired
    RejectDao rejectDao;

    /**
     * 提交认证
     *
     * @param businessCenter
     * @return
     */
    public Boolean addBusinessCenter(BusinessCenter businessCenter) {
        return businessCenterDao.insertBusinessCenter(businessCenter);
    }

    /**
     * 审核通过给用户授权
     * @param id  //企业认证id
     * @param rid  角色id
     * @param userName 用户名
     * @param statue 审核状态
     * @param reId 驳回理由id
     * @return
     */

    public Boolean updateAuditStatue( Integer id, Integer rid, String userName,Integer statue,Integer reId) {

       if(statue==2){

           rejectDao.removeReject(reId);
       }

        if (businessCenterDao.upStatue(1 ,id)) {
   return userDao.authorization(rid,userName);
        }else {
            return false;
        }

    }

    /**
     * 审核驳回
     * @param reject
     * @return
     */

    public  Boolean rejectAudit(Reject reject){
        //1.更新状态默认驳回
       if(businessCenterDao.upStatue(2,reject.getBid())) {
        return    rejectDao.addReject(reject);
       }else {
           return false;
       }
    }

    /**
     * 根据状态查看
     * @param userName
     * @param statue
     * @return
     */
    public  BusinessCenter selectMyBusinessCenter(String userName, Integer statue){

    BusinessCenter businessCenter=   rejectDao.selectBussinessAndReject(userName,statue);
    return businessCenter;
}


}
