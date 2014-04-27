package com.gomobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.gomobile.navigation.ViewWithNavigation;

public class Pickuplist extends ViewWithNavigation {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pickuplist);
		//ArrayList<Part> pickuplist = getIntent().getExtras().getParcelableArrayList("PL");
		//System.out.println("Pickuplist size: "+pickuplist.size());
		TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setText("RepairView  "+getIntent().getExtras().getLong("EanNumber"));

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
