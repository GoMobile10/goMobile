package com.gomobile.shoppingcart;

import java.util.ArrayList;

import android.content.Context;
import android.widget.Toast;

import com.gomobile.data.controller.BikeDataController;
import com.gomobile.model.BikeComponentInterface;
import com.gomobile.navigation.ViewWithNavigation;

/**
 * model of the shopping cart
 * @author tendlich
 *
 */
public class ShoppingCart {

	private ArrayList<ShoppingCartItem> cart = new ArrayList<ShoppingCartItem>();

	public void add(BikeComponentInterface item, Context context) {
		this.add(item);

		CharSequence text = "This bike has been saved in your Shopping Cart!";
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	public void add(BikeComponentInterface item) {
		ShoppingCartItem cartItem = new ShoppingCartItem(item);

		if (cart.contains(cartItem)) {
			int positionInCart = cart.indexOf(cartItem);
			cartItem = cart.get(positionInCart);
			cartItem.add();
			cart.remove(positionInCart);
		}

		cart.add(0, cartItem);

	}

	public boolean delete(Long EAN) {
		// TODO
		for(ShoppingCartItem item:cart){
			if(item.getEAN()==EAN){
				cart.remove(item);				
				break;
			}
			
		}
		// if(cart.containsKey(key)){
		// cart.remove(key);
		// return true;
		// }
		return false;
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
	
	//TODO change to sth better! waitin for anton
	public double getTotalPrice(){
		double total = 0;
		for (ShoppingCartItem item : cart) {
			total += item.getTotalPrice();
		}
		return total;
	}
	
	public int getTotalQuantity(){
		int total = 0;
		for (ShoppingCartItem item : cart) {
			total += item.getQuantity();
		}
		return total;
	}

	// singleton
	private static ShoppingCart instance = null;

	public ShoppingCart() {
		BikeDataController bdc = new BikeDataController();
//		add(bdc.getBikeByEAN(7613257813441L));
//		add(bdc.getBikeByEAN(7613257813441L));
		add(bdc.getBikeByEAN(8717185926842L));

	}

	public static ShoppingCart getInstance() {
		if (instance == null) {
			instance = new ShoppingCart();
		}
		return instance;
	}
	// singleton end

}
