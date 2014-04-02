package com.gomobile.scanner.model;

public abstract class Component {
	
	private String name;
	private int price;
	private String type;
	public boolean bike;

	public Component(String name, int price, String type){
		this.name = name;
		this.price = price;
		this.type = type;
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
	
	public boolean isSameType(Component comp){
		return this.type.equalsIgnoreCase(comp.type);
	}
	

}
