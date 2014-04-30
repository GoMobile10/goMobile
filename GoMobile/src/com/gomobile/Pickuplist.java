package com.gomobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.gomobile.navigation.ViewWithNavigation;

public class Pickuplist extends ViewWithNavigation {

	ImageView repairPicture;
	TextView repairDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pickuplist);
		//Fill the TextView 
		TextView textView = (TextView) findViewById(R.id.date);
		textView.setText("Bike: "
				+ getIntent().getExtras().getString("Description"));
		repairPicture = (ImageView) findViewById(R.id.repairPic);
		repairDescription = (TextView) findViewById(R.id.repairDescription);

		if(7613257813441L == getIntent().getExtras().getLong("EanNumber")) {
			
			repairPicture
					.setImageResource(com.gomobile.R.drawable.bike_wheelfront);
			repairDescription
					.setText("The bike wheel is flat.\nThe bike rim is bent.");
		}else{
			repairPicture.setImageResource(com.gomobile.R.drawable.bike_frame);
			repairDescription
					.setText("The frame is broken.\nThe broken part is marked.");
			
		}
		repairPicture = (ImageView) findViewById(R.id.repairPic);

	}

	public void navigateRight() {

	}

	@Override
	public void navigateLeft() {
		startActivity(new Intent(this, Overviewer.class));

	}

	@Override
	public void navigateUp() {
		// startActivity(new Intent(this, Overviewer.class));

	}

	@Override
	public void navigateDown() {
		// TODO Auto-generated method stub

	}

}
