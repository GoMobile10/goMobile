package com.gomobile.shoppingcart;

import com.gomobile.model.BikeComponentInterface;

/**
 * Wrapper for the items in the shopping cart
 * 
 * @author Tim and Arndt
 * 
 */
public class ShoppingCartItem {

	private int quantity;
	private BikeComponentInterface item;

	public ShoppingCartItem(BikeComponentInterface item) {
		this.item = item;
		this.quantity = 1;
	}

	/**
	 * @return Price of the Component
	 */
	public double getPrice() {
		return item.getPrice();
	}

	/**
	 * @return total price of the components
	 */
	public double getTotalPrice() {
		return this.getPrice() * this.quantity;
	}

	/**
	 * @return the EAN number
	 */
	public long getEAN() {
		return item.getEanNumber();
	}

	/**
	 * @return the description of the component
	 */
	public String getName() {
		return item.getDescription();
	}

	/**
	 * @return the item of type BikeComponentInterface
	 */
	public BikeComponentInterface getItem() {
		return item;
	}

	public void add() {
		this.quantity += 1;
	}

	public boolean remove() {
		this.quantity -= 1;
		if (this.quantity <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * @return the quantity
	 */
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
