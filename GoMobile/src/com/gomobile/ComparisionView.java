package com.gomobile;

import com.gomobile.navigation.Navigation;
import com.gomobile.scanner.model.Component;
import com.gomobile.technicalservices.BarcodeScanner;

import android.content.Intent;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.widget.TextView;

public class ComparisionView extends ViewWithNavigation {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comparision_view);
		Component comp1 = ScannerController.getInstance().getComponentInUse();
		Component comp2 = ScannerController.getInstance().getComponentBefore();
		display(comp1, comp2);
	}

	public void display(Component comp1, Component comp2) {
		TextView textView = (TextView) findViewById(R.id.textView11);
		textView.setText(comp1.getName());
		textView = (TextView) findViewById(R.id.textView12);
		textView.setText(comp2.getName());
	}

	@Override
	void navigateRight() {
		startActivity(new Intent(this, LowDetailView.class));
	}

	@Override
	void navigateLeft() {
		startActivity(new Intent(this, BarcodeScanner.class));
	}

	@Override
	void navigateUp() {
		// TODO Auto-generated method stub

	}

	@Override
	void navigateDown() {
		// TODO Auto-generated method stub

	}

}
