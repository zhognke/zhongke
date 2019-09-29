package com.example.busniess.utiles;

import lombok.Data;

/**
 * Echarts实体类
 * 折线图和柱状图
 */
@Data
public class EchartsEntity {
	private String name; // 名称
	private String barWidth;
	private String type; // "line":折线图;"bar":柱状图;""
	private String stack; // "总量";""
	private double value; // 值(可用于饼图,只用传name和value)
	private String[] data; // data:一般用于折线图\柱状图
	private boolean smooth; // 折线图展示样式,默认为false;true:曲线,false:直线
	private double amount;
	private boolean showSymbol = true;
	private boolean hoverAnimation = true;
	private double prop;
	private int max;
	private EchartsLabel label; // 标签样式

	public EchartsEntity() {}

	public EchartsEntity(String name, String type, String[] data, boolean smooth) {
		super();
		this.name = name;
		this.type = type;
		this.data = data;
		this.smooth = smooth;
	}

}
