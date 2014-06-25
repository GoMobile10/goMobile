package com.gomobile;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gomobile.model.Bike;
import com.gomobile.model.Component;
import com.gomobile.navigation.ViewWithNavigation;
import com.gomobile.repair.PickupSparePartsListAdapter;
import com.gomobile.repair.RepairListAdapter;
import com.gomobile.technicalservices.BarcodeScanner;

/**
 * 
 * @author danielschopp,Patrick, Arndt
 * 
 *
 */


public class Pickuplist extends ViewWithNavigation {

	ImageView repairPicture;
	TextView repairDescription;
	ListView meineListView;
	List<Component> needed_sparparts;
	Boolean repairedCheck;
	RelativeLayout navigationView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pickuplist);
		
		//Fill the TextView 
		TextView textView = (TextView) findViewById(R.id.headline);
		textView.setText("Bike: "+ getIntent().getExtras().getString("Description"));
		repairPicture = (ImageView) findViewById(R.id.repairPic);
		repairDescription = (TextView) findViewById(R.id.repairDescription);

		if(7613257813441L == getIntent().getExtras().getLong("EanNumber")) {
			
			repairPicture.setImageResource(com.gomobile.R.drawable.bike_wheelfront);
			repairDescription.setText("The bike wheel is flat.\nThe bike rim is bent.");
		}else{
			repairPicture.setImageResource(com.gomobile.R.drawable.bike_frame);
			repairDescription.setText("The frame is broken.\nThe broken part is marked.");		
		}
		repairPicture = (ImageView) findViewById(R.id.repairPic);
		
		//Repair Parts View
		String[] values = new String[] { "Schlauch", "Mantel"};
		Component c1 = new Component(8710259064051L, "Schlauch", 3, "Sparpart");
		Component c2 = new Component(8710259062705L, "Mantel", 4, "Sparpart");
		needed_sparparts = new ArrayList<Component>();
		needed_sparparts.add(c1);
		needed_sparparts.add(c2);
		System.out.println("Component List initialized: " + needed_sparparts.size());
		//scanned spar part?
		Intent intent = getIntent();
		System.out.println("scannedEAN: " + intent.getStringExtra("scannedEAN"));
		if (intent.getStringExtra("scannedEAN")!= null) {
			for (int i = 0; i < needed_sparparts.size(); i++) {
				System.out.println("i: "+ i +" "+needed_sparparts.get(i).getEanNumber() +"| "+ Long.parseLong(intent.getStringExtra("scannedEAN"), 10) );
				if(needed_sparparts.get(i).getEanNumber()== Long.parseLong(intent.getStringExtra("scannedEAN"), 10)){
					needed_sparparts.get(i).setPicukuped(true);
				}
			}
		}
		
		ListAdapter listenAdapter = new PickupSparePartsListAdapter(Pickuplist.this, R.layout.rowlayout_sparepartspickuplist_item ,needed_sparparts);		
		meineListView = (ListView) findViewById(R.id.listView1);
		meineListView.setAdapter(listenAdapter);
		
		setContentView(createNavigationInfo(R.id.pickuplistId,this,"back","detail","help","save"));

		
	}
	
	public void navigateRight() {
		//Pickup a spare part with scanning the barcode		
		Intent intent = new Intent(this, BarcodeScanner.class);
		intent.putExtra("pickupspareparts", true);
		startActivity(intent);
	}

	@Override
	public void navigateLeft() {
		startActivity(new Intent(this, Overviewer.class));

	}

	@Override
	public void navigateUp() {
		ImageView left = (ImageView)findViewById(R.id.pickuplistLEFT);
		left.setImageResource(com.gomobile.R.drawable.back);
		ImageView right = (ImageView)findViewById(R.id.pickuplistRIGHT);
		right.setImageResource(com.gomobile.R.drawable.detail);
		ImageView down = (ImageView)findViewById(R.id.pickuplistDOWN);
		down.setImageResource(com.gomobile.R.drawable.save);
		ImageView up = (ImageView)findViewById(R.id.pickuplistUP);
		up.setImageResource(com.gomobile.R.drawable.help);
		
		navigationView =(RelativeLayout) findViewById(R.id.naviInfo);
		navigationView.setVisibility(RelativeLayout.VISIBLE);
		// startActivity(new Intent(this, Overviewer.class));

	}

	@Override
	public void navigateDown() {

		
		startActivity(new Intent(this, Overviewer.class));

	}
	

	public Boolean getRepairedCheck() {
		return repairedCheck;
	}

}
