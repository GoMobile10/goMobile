package com.gomobile;

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
		TextView textView = (TextView) findViewById(R.id.textView11);
		textView.setText(comp1.getDescription());
		textView = (TextView) findViewById(R.id.textView12);
		textView.setText(comp2.getDescription());
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
