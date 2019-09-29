package com.example.busniess.utiles;

public class EchartsLabelNormal {
	private boolean show;
	private String formatter;
	private String color;
	private String position;

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	
	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public EchartsLabelNormal() {
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public EchartsLabelNormal(boolean show, String formatter) {
		this.show = show;
		this.formatter = formatter;
	}

	public EchartsLabelNormal(boolean show, String formatter, String position) {
		super();
		this.show = show;
		this.formatter = formatter;
		this.position = position;
	}

	public EchartsLabelNormal(boolean show, String formatter, String position, String color) {
		super();
		this.show = show;
		this.formatter = formatter;
		this.color = color;
		this.position = position;
	}
	
}
