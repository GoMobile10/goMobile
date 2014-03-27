package com.gomobile;

import com.gomobile.navigation.Navigation;
import com.gomobile.scanner.model.Component;
import com.gomobile.technicalservices.BarcodeScanner;

import android.content.Intent;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.widget.TextView;

public class ComparisionView extends View {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comparision_view);
		Component comp1 = ScannerController.getInstance().getComponentInUse();
		Component comp2 = ScannerController.getInstance().getComponentBefore();
		display(comp1,comp2);
	}
	

	@Override
	public void onSensorChanged(SensorEvent event) {
		switch (Navigation.getInstance().onSensorChanged(event)) {
			case Navigation.NAVIGATE_RIGHT :
				startActivity(new Intent(this, LowDetailView.class));
				break;

			case Navigation.NAVIGATE_LEFT :
				startActivity(new Intent(this, BarcodeScanner.class));
				break;
			default : 
				break;
		}
	}
	
	public void display(Component comp1, Component comp2){
		TextView textView = (TextView) findViewById(R.id.textView11);
		textView.setText(comp1.getName());
		textView = (TextView) findViewById(R.id.textView12);
		textView.setText(comp2.getName());
	}

	
}
