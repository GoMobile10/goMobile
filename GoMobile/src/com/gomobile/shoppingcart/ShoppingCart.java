package com.gomobile.shoppingcart;

import java.util.ArrayList;

import android.content.Context;
import android.widget.Toast;
import com.gomobile.model.BikeComponentInterface;

/**
 * This class includes the model of the shopping cart
 * 
 * @author Tim
 * 
 */
public class ShoppingCart {

	private ArrayList<ShoppingCartItem> cart = new ArrayList<ShoppingCartItem>();

	/**
	 * Add a component to the shopping card
	 * @param item
	 * @param context
	 */
	public void add(BikeComponentInterface item, Context context) {
		this.add(item);

		CharSequence text = "This bike has been saved in your Shopping Cart!";
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	/**
	 * Removes a component form the shopping card
	 * @param item
	 * @param context
	 */
	public void delete(BikeComponentInterface item, Context context) {
		int quantity = this.delete(item);
		CharSequence text = "";
		if (quantity != -1) {
			text = "Item has been delete!";
			if (quantity > 0)
				text = text + " There are " + quantity + " left in the Cart";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		} else {

		}
	}

	private void add(BikeComponentInterface item) {
		ShoppingCartItem cartItem = new ShoppingCartItem(item);

		if (cart.contains(cartItem)) {
			int positionInCart = cart.indexOf(cartItem);
			cartItem = cart.get(positionInCart);
			cartItem.add();
			cart.remove(positionInCart);
		}

		cart.add(0, cartItem);

	}

	private int delete(BikeComponentInterface item) {

		if (cart.contains(item)) {
			int index = cart.indexOf(item);

			if (cart.get(index).remove())
				cart.remove(index);
			ShoppingCartItem cartItem = cart.get(index);
			return cartItem.getQuantity();
		}
		return -1;
	}

	public ShoppingCartItem[] view() {
		ShoppingCartItem[] material = new ShoppingCartItem[cart.size()];
		int n = 0;
		for (ShoppingCartItem item : cart) {
			material[n] = item;
			n++;
		}
		return material;
	}

	/**
	 * Counts the total price
	 * @return the total Price
	 */
	public double getTotalPrice() {
		double total = 0;
		for (ShoppingCartItem item : cart) {
			total += item.getTotalPrice();
		}
		return total;
	}
	/**
	 * Counts the total Quantity
	 * @return the total quantity
	 */
	public int getTotalQuantity() {
		int total = 0;
		for (ShoppingCartItem item : cart) {
			total += item.getQuantity();
		}
		return total;
	}

	// singleton
	private static ShoppingCart instance = null;

	private ShoppingCart() {

	}

	public static ShoppingCart getInstance() {
		if (instance == null) {
			instance = new ShoppingCart();
		}
		return instance;
	}
	// singleton end

}
