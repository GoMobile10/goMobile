package com.gomobile.scanner.model;

public abstract class Component {
	
	private String name;
	private int price;
	private String type;
	public boolean bike;

	public Component(String name, int price){
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
	
	public boolean isBike(){
		return this.bike;
	}
	

}
