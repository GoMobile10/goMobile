package com.gomobile.model;


public class Component extends Material implements BikeComponentInterface{
	
	private String description;
	private boolean picukuped;
	
	public Component(long eanNumber, String description, int price, String type){
		super(eanNumber);
		this.description = description;
		this.setPrice(price);
		this.picukuped =false;
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
	
	

}
