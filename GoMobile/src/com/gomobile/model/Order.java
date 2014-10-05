package com.gomobile.model;

/**
 * This class provides constructors for the class Order
 * @author Arndt and Daniel
 *
 */

import java.util.ArrayList;

public class Order {
	int orderNumber;
	ArrayList<Component> pickuplist;

	public Order(int orderNumber, ArrayList<Component> pickuplist) {
		this.orderNumber = orderNumber;
		this.pickuplist = pickuplist;
	}

	@Override
	public String toString() {
		return "Order " + orderNumber;
	}

	/**
	 * @return the number of the order
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber
	 */
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @return the list with the components which have to be pickedup
	 */
	public ArrayList<Component> getPickuplist() {
		return pickuplist;
	}

	/**
	 * @param pickuplist
	 */
	public void setPickuplist(ArrayList<Component> pickuplist) {
		this.pickuplist = pickuplist;
	}

}