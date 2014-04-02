package com.gomobile.scanner.model;

public class Bike extends Component {
	
//	public String type = "bike";
	
	public Bike(String name, int price, String type){
		super(name, price, type);
		this.bike = true;
	}

}
