package com.gomobile;

import com.gomobile.R;
import com.gomobile.technicalservices.BarcodeScanner;
import com.gomobile.technicalservices.DataConnectionActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Intent intent = new Intent(this, DataConnectionActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void startScanner(View view){
		Intent intent = new Intent(this, BarcodeScanner.class);
	    startActivity(intent);
	}

}
