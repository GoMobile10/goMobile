package com.gomobile;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.gomobile.scanner.model.Part;
import com.gomobile.navigation.ViewWithNavigation;
import com.gomobile.technicalservices.BarcodeScanner;

public class Pickuplist extends ViewWithNavigation {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pickuplist);
		//ArrayList<Part> pickuplist = getIntent().getExtras().getParcelableArrayList("PL");
		//System.out.println("Pickuplist size: "+pickuplist.size());
		TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setText("Pickup List for Order "+getIntent().getExtras().getInt("OrderID"));

	}
	
	
	
	
	
	public void navigateRight() {
		
	}
	
	
	

	@Override
	public void navigateLeft() {
		startActivity(new Intent(this, Overviewer.class));

	}

	@Override
	public void navigateUp() {
		//startActivity(new Intent(this, Overviewer.class));

	}

	@Override
	public void navigateDown() {
		// TODO Auto-generated method stub

	}

}
