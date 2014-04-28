package com.gomobile.model;

import android.text.GetChars;


public class Bike extends Material implements BikeComponentInterface{
	
//	public String type = "bike";
	private String description;
	private String category;

	public Bike(long eanNumber, String name, double price, String category){
		super(eanNumber);
		this.description = name;
		this.setPrice(price);
		this.category = category;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getCategory(){
		return category;
	}
	
}
