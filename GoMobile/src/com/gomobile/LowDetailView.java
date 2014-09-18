package com.gomobile;

import java.text.NumberFormat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.gomobile.model.Bike;
import com.gomobile.model.BikeComponentInterface;
import com.gomobile.navigation.Navigation;
import com.gomobile.navigation.ViewWithNavigation;
import com.gomobile.shoppingcart.ShoppingCart;
import com.gomobile.shoppingcart.ShoppingCartView;
import com.gomobile.technicalservices.BarcodeScanner;

public class LowDetailView extends ViewWithNavigation {

	private static boolean compare = false;
	private static boolean shoppingCart = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_low_detail_view);
		setContentView(createNavigationInfo(R.id.lowdetailviewId,this,"scan","detail","shop",null));
		Bundle intentExtras = getIntent().getExtras();

//		String bikeDescription = intentExtras.getString("bike_description");
		display((Bike)ScannerController.getInstance().getMaterialInUse());

		if(intentExtras != null ){
			if(intentExtras.containsKey("compare"))
				compare = getIntent().getExtras().getBoolean("compare");
				setContentView(createNavigationInfo(R.id.lowdetailviewId,this,"compare","detail","shop",null));

			if(intentExtras.containsKey("shoppingCart")){
				shoppingCart = getIntent().getExtras().getBoolean("shoppingCart");
				setContentView(createNavigationInfo(R.id.lowdetailviewId,this,"shop","detail","shop",null));
				//display the item
			}
		}
		
//		
	}

	public void display(Bike comp) {
		TextView textView = (TextView) findViewById(R.id.textViewName);
		textView.setText(comp.getDescription());
		textView = (TextView) findViewById(R.id.textViewPrice);
		
		//display price in correct format
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
		double price = comp.getPrice();
		textView.setText(currencyFormatter.format(price));
	}

	public void navigateRight() {
		startActivity(new Intent(this, DetailView.class));
	}

	public void navigateLeft() {
		if(compare){
			startActivity(new Intent(this, ComparisionView.class));
			compare = false;
		}else
		if(shoppingCart){
			startActivity(new Intent(this, ShoppingCartView.class));
			shoppingCart = false;
		}else{
			startActivity(new Intent(this, BarcodeScanner.class));	
		}
	}

	@Override
	public void navigateUp() {
		startActivity(new Intent(this,ShoppingCartView.class));
	}

	@Override
	public void navigateDown() {
		if(shoppingCart){
			BikeComponentInterface item = (BikeComponentInterface)ScannerController.getInstance().getMaterialInUse();
			ShoppingCart.getInstance().delete(item, this);				
		}
	}
}