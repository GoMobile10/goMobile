package com.gomobile.model;

import java.util.ArrayList;

import com.gomobile.scanner.model.Part;

public class Order {
	int orderNumber;
	ArrayList<Part> pickuplist;
	
	public Order(int orderNumber, ArrayList<Part> pickuplist){
		this.orderNumber= orderNumber;
		this.pickuplist = pickuplist;
	}
	@Override
	public String toString() {
	    return "Order " + orderNumber;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public ArrayList<Part> getPickuplist() {
		return pickuplist;
	}
	public void setPickuplist(ArrayList<Part> pickuplist) {
		this.pickuplist = pickuplist;
	}


}