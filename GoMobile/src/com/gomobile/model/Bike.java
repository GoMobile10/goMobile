package com.gomobile.model;

import android.text.GetChars;


public class Bike extends Material implements BikeComponentInterface{
	
//	public String type = "bike";

	private String description;
	private String category;
	private int isDefect;

	public Bike(long eanNumber, String name, int price, String category) {
		super(eanNumber);
		this.description = name;
		this.setPrice(price);
		this.category = category;
	}
	
	public Bike(long eanNumber, String name, int price, String category, int isDefect) {
		super(eanNumber);
		this.description = name;
		this.setPrice(price);
		this.category = category;
		this.isDefect = isDefect;
	}
	
	public Bike(long eanNumber, String name) {
		super(eanNumber);
		this.description = name;
		
	}
	
	public Bike(long eanNumber, String name, int price, String category, int weight, boolean isAvailable) {
		this(eanNumber, name, price, category);
		this.setAvailable(isAvailable);
		this.setWeight(weight);
	}
	

	public String toString() {
		return description;
	}

	public String getDescription() {
		return description;
	}

	public String getCategory() {
		return category;
	}
	
}
