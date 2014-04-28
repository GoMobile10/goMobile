package com.gomobile.shoppingcart;

import com.gomobile.model.BikeComponentInterface;

/**
 * Wrapper for the items in the shopping cart
 * 
 * @author tendlich
 * 
 */
public class ShoppingCartItem {

	private int quantity;
	private BikeComponentInterface item;

	public ShoppingCartItem(BikeComponentInterface item) {
		this.item = item;
		this.quantity = 1;
	}

	// TODO change to sth better! waitin for anton
	public double getPrice() {
		return item.getPrice();
	}

	// TODO change to sth better! waitin for anton
	public double getTotalPrice() {
		return this.getPrice() * this.quantity;
	}

	public long getEAN() {
		return item.getEanNumber();
	}

	public String getName() {
		return item.getDescription();
	}

	public BikeComponentInterface getItem() {
		return item;
	}

	public void add() {
		this.quantity += 1;
	}

	public int getQuantity() {
		return this.quantity;
	}

	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof ShoppingCartItem) {
			o = (ShoppingCartItem) o;

			if (this.getEAN() == ((ShoppingCartItem) o).getEAN())
				return true;
		}
		return false;
	}
}
