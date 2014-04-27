package com.gomobile.model;


public class Component extends Material{
	
	private String description;
	
	public Component(long eanNumber, String description, int price, String type){
		super(eanNumber);
		this.description = description;
		this.setPrice(price);
	}

	public String getDescription(){
		return description;
	}

}
