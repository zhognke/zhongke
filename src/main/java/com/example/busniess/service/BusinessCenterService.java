package com.example.busniess.service;


import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.Reject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BusinessCenterService {
    /**
     * 提交认证
     *
     * @param businessCenter
     * @return
     */
    Boolean addBusinessCenter(BusinessCenter businessCenter);

    /**
     * 审核通过
     * @param id
     * @param rid
     * @param userName
     * @return
     */
    Boolean updateAuditStatue(Integer id, Integer rid, String userName, Integer statue, Integer reId);

    /**
     * 驳回
     *
     * @param reject
     * @return
     */
    Boolean rejectAudit(Reject reject);

    /**
     * 查看所有的
     */
    PageInfo selectAllBusinessCenter(BusinessCenter businessCenter,Integer pageNumber,Integer pageSize);

    /**
     * 查看自己的企业认证
     */
    BusinessCenter selectMyBusinessCenter(String userName);

    /**
     * 查看具体的企业认证
     * @param id
     * @return
     */
    BusinessCenter selectBusinessCenterById(Integer id);

    /**
     * 修改认证
     */
    Boolean updateBusinessCenter(BusinessCenter businessCenter);

    /**
     * 条件检索
     * @param businessCenter
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo showByPage(BusinessCenter businessCenter, Integer pageNum, Integer pageSize);

    /**
     * 根据状态查询企业中心并且没有填写企业认证补充信息的
     */
public List<BusinessCenter> selectBusinessCenterNoinformation(Integer sattue);
}
