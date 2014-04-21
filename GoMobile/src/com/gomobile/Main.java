package com.gomobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gomobile.navigation.ViewWithNavigation;
import com.gomobile.technicalservices.BarcodeScanner;

public class Main extends ViewWithNavigation {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void startScanner(View view) {
		Intent intent = new Intent(this, BarcodeScanner.class);
		startActivity(intent);
	}

	public void navigateRight() {
		startScanner(this.findViewById(R.layout.main));
	}

	@Override
	public void navigateLeft() {
		// TODO Auto-generated method stub

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
