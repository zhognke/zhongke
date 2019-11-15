package com.example.busniess.service.imp;

import com.example.busniess.dao.BusinessCenterDao;
import com.example.busniess.dao.OccupancyDao;
import com.example.busniess.dao.RejectDao;
import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.DemandsEntity;
import com.example.busniess.entity.Occupancy;
import com.example.busniess.entity.Reject;
import com.example.busniess.service.BusinessCenterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BusinessCenterServiceImpl implements BusinessCenterService {
    @Autowired
    UserDao userDao;
    @Autowired
    BusinessCenterDao businessCenterDao;
    @Autowired
    RejectDao rejectDao;
    @Autowired
    OccupancyDao occupancyDao;

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
        if(businessCenterDao.upStatue(2,reject.getBId())) {
            return    rejectDao.addReject(reject);
        }else {
            return false;
        }
    }

    /**
     * 产看企业认证中心具体信息
     * @param userName
     * @return
     */
    @Override
    public BusinessCenter selectMyBusinessCenter(String userName) {

        return  businessCenterDao.selectOneBusinessCenter(userName);
    }

    /**
     * 查看所有企业认证信息
     * 按条件查询
     * 企业名称
     * 人数
     * 行业
     * 审核状态
     * @param businessCenter
     * @return
     */
    @Override
    public PageInfo selectAllBusinessCenter(BusinessCenter businessCenter,Integer pageNumber,Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);

        return   new  PageInfo(businessCenterDao.selectAllBusinessCenter(businessCenter));
    }

    /**
     * 查看具体的企业认证
     * @param id
     * @return
     */
    @Override
    public BusinessCenter selectBusinessCenterById(Integer id) {
        BusinessCenter businessCenter = businessCenterDao.selectBussinessByid(id);
        if(businessCenter!=null){
            List<Occupancy> occupancyList = occupancyDao.selectMyOccupancy(businessCenter.getUName());
            businessCenter.setOccupancyList(occupancyList);
        }
        return businessCenter;
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

    /**
     *修改认证信息
     * @param businessCenter
     * @return
     */
    @Override
    public Boolean updateBusinessCenter(BusinessCenter businessCenter) {
        return businessCenterDao.updateBusinessCenter(businessCenter);
    }

    /**
     * 分页展示-大厅
     * @param businessCenter
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo showByPage(BusinessCenter businessCenter, Integer pageNum, Integer pageSize) {
        businessCenter.setStatue(1);
        PageHelper.startPage(pageNum, pageSize);
        List<BusinessCenter> list = businessCenterDao.search(businessCenter);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
