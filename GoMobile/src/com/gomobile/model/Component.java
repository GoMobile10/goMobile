package com.gomobile.model;


public class Component extends Material implements BikeComponentInterface{
	
	private String description;
	private boolean picukuped;
	private int averageAssemblingTime;
	
	public Component(long eanNumber, String description, int price, String type){
		super(eanNumber);
		this.description = description;
		this.setPrice(price);
		this.picukuped =false;
	}

	public Component(long eanNumber, String description, int price){
		this(eanNumber, description, price, null);
	}

	public String getDescription(){
		return description;
	}

	public boolean isPicukuped() {
		return picukuped;
	}

	public void setPicukuped(boolean picukuped) {
		this.picukuped = picukuped;
	}

	public int getAverageAssemblingTime() {
		return averageAssemblingTime;
	}

	public void setAverageAssemblingTime(int averageAssemblingTime) {
		this.averageAssemblingTime = averageAssemblingTime;
	}
	
	

}
