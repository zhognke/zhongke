package com.example.busniess.entity;

import com.example.busniess.validator.UserValidator;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

//科技成果入住信息
@Data
public class Occupancy implements Serializable{
    @NotNull(message = "id参数不能为空", groups = UserValidator.UpDate.class)
    private  Integer id;//id
    @NotNull(message = "用户不能为空", groups = {UserValidator.UpDate.class,UserValidator.InSet.class})
    private  String userName;//关联的用户

    private  String  nameFirm;//企业名称
    private String resultTechnolo;//科技成果
    private String describe;//详情描述
    private  String patentNumber;//所获专利号和名称
    private String serialNumber;//知识产权编号
    private  String imgAddress;//图片地址
    private String industry;//所属行业
    private  String spIndustries;//具体行业
    private  String country;//省
    private  String city;//市
    private  String district;//区
    private  Data creatTime;//创建时间
    private  Data upTiem;//修改时间
    private  Integer statue;//入住状态

}
