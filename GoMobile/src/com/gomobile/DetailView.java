package com.gomobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.gomobile.model.Bike;
import com.gomobile.navigation.ViewWithNavigation;

public class DetailView extends ViewWithNavigation  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_detail_view);
		this.display((Bike) ScannerController.getInstance().getMaterialInUse());
	}

	public void display(Bike comp){
		TextView textView = (TextView) findViewById(R.id.textViewName);
		textView.setText(comp.getDescription());
		textView = (TextView) findViewById(R.id.textViewPrice);
		textView.setText(String.valueOf(comp.getPrice()));
		textView = (TextView) findViewById(R.id.textViewCategory);
		textView.setText(String.valueOf(comp.getCategory()));
	}

	@Override
	public void navigateRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void navigateLeft() {
		startActivity(new Intent(this, LowDetailView.class));		
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
