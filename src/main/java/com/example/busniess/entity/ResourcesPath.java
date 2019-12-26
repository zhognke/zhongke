package com.example.busniess.entity;

import lombok.Data;

@Data
public class ResourcesPath {
    private int id;//id号
    private String path;//实际的路径
    private String explain;//路径说明
    private String alias;//路径别名

}
