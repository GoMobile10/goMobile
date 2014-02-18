package com.gomobile.scanner.model;

public class Bike extends Component {
	
	public String type = "bike";
	
	public Bike(String name, int price){
		super(name, price);
		this.bike = true;
	}

}
