package com.example.busniess.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Data
public class ImageAddress {
    private  Integer id;//id
//    @NotBlank(message = "id不能为空")
    private  Integer oId;//关联科技成果id
    @NotBlank(message = "图片地址不能为空")
    private  String img;//图片地址
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date insertTime;//插入时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;//更新时间
}
