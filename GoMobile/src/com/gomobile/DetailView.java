package com.gomobile;

import java.text.NumberFormat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.gomobile.model.Bike;
import com.gomobile.model.BikeComponentInterface;
import com.gomobile.navigation.ViewWithNavigation;
import com.gomobile.shoppingcart.ShoppingCart;
import com.gomobile.shoppingcart.ShoppingCartView;

public class DetailView extends ViewWithNavigation  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_detail_view);
		this.display((Bike) ScannerController.getInstance().getMaterialInUse());
	}

	public void display(Bike comp){
		TextView textView = (TextView) findViewById(R.id.textViewName);
		textView.setText(comp.getDescription());

		textView = (TextView) findViewById(R.id.textViewCategory);
		textView.setText(String.valueOf(comp.getCategory()));
		
		//display price in the correct format		
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
		double price = comp.getPrice();
		textView = (TextView) findViewById(R.id.textViewPrice);
		textView.setText(currencyFormatter.format(price));
		
		/*textView = (TextView) findViewById(R.id.textViewWeight);
		textView.setText(String.valueOf(comp.getWeight()));
		
		textView = (TextView) findViewById(R.id.textViewAvailability);
		textView.setText((comp.isAvailable()) ? "In Stock" : "Sorry, this bike is not available");*/
	}

	@Override
	public void navigateRight() {
		Context context = getApplicationContext();
		BikeComponentInterface item = (BikeComponentInterface)ScannerController.getInstance().getMaterialInUse();
		ShoppingCart.getInstance().add(item,context);		
	}

	@Override
	public void navigateLeft() {
		startActivity(new Intent(this, LowDetailView.class));		
	}

	@Override
	public void navigateUp() {
		startActivity(new Intent(this,ShoppingCartView.class));		
	}

	@Override
	public void navigateDown() {
		// TODO Auto-generated method stub
		
	}

}
