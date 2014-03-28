package com.gomobile;

import com.gomobile.R;
import com.gomobile.navigation.Navigation;
import com.gomobile.technicalservices.BarcodeScanner;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Main extends ViewWithNavigation {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void startScanner() {
		Intent intent = new Intent(this, BarcodeScanner.class);
		startActivity(intent);
	}

	void navigateRight() {
		startScanner();
	}

	@Override
	void navigateLeft() {
		// TODO Auto-generated method stub

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
