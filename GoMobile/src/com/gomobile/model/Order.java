package com.gomobile.model;

import java.util.ArrayList;

public class Order {
	int orderNumber;
	ArrayList<Component> pickuplist;
	
	public Order(int orderNumber, ArrayList<Component> pickuplist){
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
	public ArrayList<Component> getPickuplist() {
		return pickuplist;
	}
	public void setPickuplist(ArrayList<Component> pickuplist) {
		this.pickuplist = pickuplist;
	}


}