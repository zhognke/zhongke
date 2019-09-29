package com.example.busniess.service;


import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.Reject;

public interface BusinessCenterService {
    /**
     * 提交认证
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
    Boolean updateAuditStatue( Integer id, Integer rid, String userName);

    /**
     * 驳回
     * @param reject
     * @return
     */
    Boolean rejectAudit(Reject reject);
}
