package com.example.busniess.dao;

import com.example.busniess.entity.DemandsEntity;
import com.example.busniess.search.model.EsDemands;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 搜索系统中的商品管理自定义Dao
 * Created by macro on 2018/6/19.
 */
public interface EsDemandsDao {
    List<EsDemands> getAllEsDemandsList(@Param("id") Integer id);
    /**
     * 根据id查找需求(详情)
     * @param id    需求id
     * @return
     */
    @Select("select `id`,`company_name` as companyName,`demand_type` as demandType,`cooperation_type` as cooperationType,`demand_industry` as demandIndustry,`demand_industry_detail` as demandIndustryDetail,`demand_outline` as demandOutline,`demand_content` as demandContent,`expected_result` as expectedResult,`city`,`district`,`contact`,`phone_num` as phoneNum,`email`,`pre_investment_amount` as preInvestmentAmount,`end_date` as endDate,`create_time` as createTime,`update_time` as updateTime,`remark`,`approval_status` as approvalStatus,`status` from `demands` where id = #{id}")
    public EsDemands getByID(@Param("id") int id);
}
