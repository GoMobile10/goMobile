package com.gomobile.shoppingcart;

import java.text.NumberFormat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.gomobile.LowDetailView;
import com.gomobile.R;
import com.gomobile.ScannerController;
import com.gomobile.model.Material;
import com.gomobile.navigation.ViewWithNavigation;
import com.gomobile.technicalservices.BarcodeScanner;

/**
 * View that displays the content of the shopping cart
 * 
 * @author Tim, Arndt and Daniel
 */	
public class ShoppingCartView extends ViewWithNavigation {

	private ListView mainListView;
	private ShoppingCarListAdapter listAdapter;
	private ShoppingCartItem[] shoppingCartItems;
	private int listPosition = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping_cart_view);
		setContentView(createNavigationInfo(R.id.shoppingcartviewId, this, "scan", "detail", null, null));

		mainListView = (ListView) findViewById(R.id.listViewShoppingCart);
		ShoppingCart shoppingcart = ShoppingCart.getInstance();
		shoppingCartItems = shoppingcart.view();
		listAdapter = new ShoppingCarListAdapter(this, R.layout.shopping_cart_list, shoppingCartItems);

		mainListView.setAdapter(listAdapter);
		mainListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
		mainListView.setItemChecked(0, true);
		this.setShoppingCartFooter();
	}

	private void setShoppingCartFooter() {
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
		double totalPrice = ShoppingCart.getInstance().getTotalPrice();
		int totalQuantity = ShoppingCart.getInstance().getTotalQuantity();
		String totalItems = totalQuantity + " " + (!(totalQuantity > 1) ? "item" : "items");

		TextView textViewItem = (TextView) this.findViewById(R.id.totalPrice);
		textViewItem.setText(currencyFormatter.format(totalPrice));

		textViewItem = (TextView) this.findViewById(R.id.totalQuantity);
		textViewItem.setText(totalItems);
	}

	@Override
	public void navigateRight() {
		Intent intent = new Intent(this, LowDetailView.class);
		intent.putExtra("shoppingCart", true);
		ScannerController.getInstance().setMaterialInUse((Material) (shoppingCartItems[listPosition].getItem()));
		startActivity(intent);
	}

	@Override
	public void navigateLeft() {
		Intent intent = new Intent(this, BarcodeScanner.class);
		startActivity(intent);
	}

	@Override
	public void navigateUp() {
		if (listPosition <= 0) {
			listPosition = 0;
			((ShoppingCarListAdapter) listAdapter).setCurrentPosition(listPosition);
		} else {
			mainListView.setItemChecked(listPosition - 1, true);
			listPosition = listPosition - 1;
			((ShoppingCarListAdapter) listAdapter).setCurrentPosition(listPosition);
		}
	}

	@Override
	public void navigateDown() {
		if (listPosition >= shoppingCartItems.length - 1) {
			listPosition = shoppingCartItems.length - 1;
			((ShoppingCarListAdapter) listAdapter).setCurrentPosition(listPosition);
		} else {
			mainListView.setItemChecked(listPosition + 1, true);
			listPosition = listPosition + 1;
			((ShoppingCarListAdapter) listAdapter).setCurrentPosition(listPosition);
		}
	}

}
