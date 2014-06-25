package com.gomobile;

import java.text.NumberFormat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.gomobile.model.Bike;
import com.gomobile.navigation.ViewWithNavigation;
import com.gomobile.technicalservices.BarcodeScanner;

public class ComparisionView extends ViewWithNavigation {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comparision_view);
    	
		Bike comp1 = (Bike) ScannerController.getInstance().getMaterialInUse();
    	Bike comp2 = (Bike) ScannerController.getInstance().getMaterialBefore();
    	display(comp1, comp2);
		
	}

	public void display(Bike comp1, Bike comp2) {
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

		TextView textView = (TextView) findViewById(R.id.textNameCompare1);
		textView.setText(comp1.getDescription());
		
		TextView textView2 = (TextView) findViewById(R.id.textNameCompare2);
		textView2.setText(comp2.getDescription());
		
		textView = (TextView) findViewById(R.id.textPriceCompare1);
		textView.setText(currencyFormatter.format(comp1.getPrice()));
		
		textView = (TextView) findViewById(R.id.textPriceCompare2);
		textView.setText(currencyFormatter.format(comp2.getPrice()));
		
		textView = (TextView) findViewById(R.id.textViewCategory1);
		textView.setText(comp1.getDescription());
		
		textView = (TextView) findViewById(R.id.textViewCategory2);
		textView.setText(comp2.getDescription());
		
		textView = (TextView) findViewById(R.id.textWeight1);
		textView.setText(comp1.getWeight());
		
		textView = (TextView) findViewById(R.id.textWeight2);
		textView.setText(comp2.getWeight());
		
		textView.setText(currencyFormatter.format(comp1.getPrice()));
		textView.setText(currencyFormatter.format(comp2.getPrice()));
		
		TextView textView3 = (TextView) findViewById(R.id.textViewAvailability1);
		if(comp1.isAvailable()){
			textView3.setText("is available");
		}
		else{
			textView3.setText("is not available");
		}
		
		TextView textView4 = (TextView) findViewById(R.id.textViewAvailability1);
		if(comp1.isAvailable()){
			textView4.setText("is available");
		}
		else{
			textView4.setText("is not available");
		}
	}

	@Override
	public void navigateRight() {
		Intent intent = new Intent(this, LowDetailView.class);
		intent.putExtra("compare", true);
		startActivity(intent);
	}

	@Override
	public void navigateLeft() {
		startActivity(new Intent(this, BarcodeScanner.class));
	}

	@Override
	public void navigateUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void navigateDown() {
		// TODO Auto-generated method stub

	}

}
