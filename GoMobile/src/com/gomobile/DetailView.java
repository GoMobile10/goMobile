package com.gomobile;

import android.annotation.TargetApi;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.gomobile.navigation.Navigation;
import com.gomobile.scanner.model.Component;
import com.gomobile.technicalservices.BarcodeScanner;

public class DetailView extends ViewWithNavigation  {

	SensorManager sm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_detail_view);
		this.display(ScannerController.getInstance().getComponentInUse());
	}

	public void display(Component comp){
		TextView textView = (TextView) findViewById(R.id.textViewName);
		textView.setText(comp.getName());
		textView = (TextView) findViewById(R.id.textViewPrice);
		textView.setText(String.valueOf(comp.getPrice()));
	}

	@Override
	void navigateRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void navigateLeft() {
		startActivity(new Intent(this, LowDetailView.class));		
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
