package com.example.busniess.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Reject {
    private Integer id;//主id
    private Integer bid;//关联的企业认证信息id
    private String content;//驳回的内容
    private Date rejectTime;//驳回时间
}
