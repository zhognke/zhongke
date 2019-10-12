package com.example.busniess.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class Echarts implements Serializable {
    private Integer total;
    private List xdata;
    private List ydata;


}
