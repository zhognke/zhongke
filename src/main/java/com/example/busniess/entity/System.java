package com.example.busniess.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class System {
    private  int id;//id
    private String url;//系统名
    private  int statue;// 状态
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date upDateTime;//禁用时间
}
