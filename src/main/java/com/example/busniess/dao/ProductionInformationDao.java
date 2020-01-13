package com.example.busniess.dao;

import com.example.busniess.entity.ProductionInformationEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 企业生产信息
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2020-01-09 08:13:22
 */
public interface ProductionInformationDao {
    /**
     * 新增
     *
     * @param productionInformationEntity
     * @return
     */
    @Insert("insert into production_information(username,company_name,province,city,district,industry,industry_detail,sub_date,purchase_input,material_input,staff_input,total_input," +
            "turnover,profit,production_value,defective_rate,sales_num,electricity_consumption) " +
            "values (#{username},#{companyName},#{province},#{city},#{district},#{industry},#{industryDetail},#{subDate},#{purchaseInput},#{materialInput},#{staffInput},#{totalInput}," +
            "#{turnover},#{profit},#{productionValue},#{defectiveRate},#{salesNum},#{electricityConsumption})")
    boolean add(ProductionInformationEntity productionInformationEntity);

    /**
     * 逻辑删除
     *
     * @return
     */
    @Update("update production_information set del_flag = 1 where id= #{id}")
    boolean deleteByID(@Param("id") Integer id);

    /**
     * 批量逻辑删除
     *
     * @return
     */
    @Update("update production_information set del_flag = 1 where id in ('${ids}')")
    boolean deleteBatch(@Param("ids") String ids);

    /**
     * 修改
     *
     * @param productionInformationEntity
     * @return
     */
    @Update("update production_information set username=#{username},company_name =#{companyName},province =#{province},city =#{city},district =#{district},industry =#{industry},industry_detail =#{industryDetail}," +
            "sub_date =#{subDate},purchase_input =#{purchaseInput},material_input =#{materialInput},staff_input =#{staffInput},total_input =#{totalInput},turnover =#{turnover},profit =#{profit}," +
            "production_value =#{productionValue},defective_rate =#{defectiveRate},sales_num =#{salesNum},electricity_consumption =#{electricityConsumption} where id = #{id}")
    boolean updateById(ProductionInformationEntity productionInformationEntity);

    /**
     * 根据id查找
     *
     * @return
     */
    @Select("select * from production_information where id= #{id}")
    ProductionInformationEntity selectById(Integer id);

    /**
     * 根据条件查找
     *
     * @param productionInformationEntity
     * @return
     */
    List<ProductionInformationEntity> search(ProductionInformationEntity productionInformationEntity);

    /**
     * 检查当月是否已经提交
     * @param productionInformationEntity
     * @return
     */
    @Select("select * from production_information where username = #{username} and sub_date = #{subDate} and del_flag = 0 limit 1")
    ProductionInformationEntity selectByDate(ProductionInformationEntity productionInformationEntity);
}
