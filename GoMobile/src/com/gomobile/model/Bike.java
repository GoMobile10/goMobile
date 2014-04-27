package com.gomobile.model;

import android.graphics.Picture;
import android.text.GetChars;


public class Bike extends Material {
	
//	public String type = "bike";
	private String description;
	private String category;
//	private long eanNumber;
	
	
	

	public Bike(long eanNumber, String name, int price, String category){
		super(eanNumber);
		this.description = name;
		this.setPrice(price);
		this.category = category;
		
	}
	
	public String toString(){
		return description;
	}
	

	public String getDescription(){
		return description;
	}
	
	public String getCategory(){
		return category;
	}
}
