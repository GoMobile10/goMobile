package com.gomobile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gomobile.data.controller.BikeDataController;
import com.gomobile.model.Bike;
import com.gomobile.model.Component;
import com.gomobile.model.RepairOrder;
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
	List<Component> needed_sparparts; //Arraylist for sparepart management
	Boolean repairedCheck;
	RelativeLayout navigationView;
	BikeDataController databaseController;
	List<Bike> bikesToRepair;
	ArrayList<String> scannedComponents;
	boolean scannedBefore;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pickuplist);	
		//Fill the static view parts		
		populateView();	
		//Repair Parts View
		//create a list with components for repairing
		populateSpareParts();
		scannedBefore = false;
		checkScannedSpareParts();
		
		ListAdapter listenAdapter = new PickupSparePartsListAdapter(Pickuplist.this, R.layout.rowlayout_sparepartspickuplist_item ,needed_sparparts);		
		meineListView = (ListView) findViewById(R.id.listView1);
		meineListView.setAdapter(listenAdapter);
		
		setContentView(createNavigationInfo(R.id.pickuplistId,this,"back","scan","detail",null));

		
	}
	
	private void checkScannedSpareParts(){
		Intent intent = getIntent();
		System.out.println("scannedEAN: " + intent.getStringExtra("scannedEAN"));
		if (intent.getStringExtra("scannedEAN")!= null) {
			for (int i = 0; i < needed_sparparts.size(); i++) {
				System.out.println("i: "+ i +" "+needed_sparparts.get(i).getEanNumber() +"| "+ Long.parseLong(intent.getStringExtra("scannedEAN"), 10) );
				if(needed_sparparts.get(i).getEanNumber()== Long.parseLong(intent.getStringExtra("scannedEAN"), 10)){
					needed_sparparts.get(i).setPicukuped(true);
					if(scannedBefore == false){
						System.out.println("scannedBefore false");
						
					}
					System.out.println("add " +intent.getStringExtra("scannedEAN")+"to scanned Components");
					scannedComponents.add(intent.getStringExtra("scannedEAN"));
					scannedBefore = true;
				}
			}
		}
		System.out.println("before scanned Components check ");
		if (intent.getStringExtra("scannedEAN") != null) {
			for (int h= 0; h < scannedComponents.size(); h++) {
				for (int i = 0; i < needed_sparparts.size(); i++) {
					System.out.println("i and h: "+ i +" "+needed_sparparts.get(i).getEanNumber() +"| "+ Long.parseLong(scannedComponents.get(h), 10) );
					if(needed_sparparts.get(i).getEanNumber()== Long.parseLong(scannedComponents.get(h), 10)){
						System.out.println("treffer! " +needed_sparparts.get(i).getEanNumber());
						needed_sparparts.get(i).setPicukuped(true);					}
				}
			}
		}
	}
	private void populateSpareParts(){
		
		//Loading data from database
        BikeDataController bikeDataController = new BikeDataController();
        List<RepairOrder> repairorderlist;
        System.out.println("EAN:"+getIntent().getExtras().getString("EanNumber"));
        repairorderlist = bikeDataController.getRepairOrders("BikeEAN="+getIntent().getExtras().getString("EanNumber"));
        System.out.println("repairorderlist: "+ repairorderlist.size()+" | name: "+repairorderlist.get(0).getDefectBike().getDescription());
        RepairOrder tempRepairOrder = repairorderlist.get(0);
        
        Bike defectBike = tempRepairOrder.getDefectBike();
        
        needed_sparparts = new ArrayList<Component>();
        
        HashMap<Component, Component> defectcomponents = (HashMap<Component, Component>) tempRepairOrder.getDefectReplacementComponentMap();
        
        Iterator it = defectcomponents.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            System.out.println(((Component)pairs.getKey()).getDescription() + " = " + ((Component)pairs.getValue()).getDescription());
            needed_sparparts.add(((Component)pairs.getValue()));
            it.remove(); // avoids a ConcurrentModificationException
        }
	}
	private void populateView(){
		TextView textView = (TextView) findViewById(R.id.headline);
		textView.setText("Bike: "+ getIntent().getExtras().getString("BikeName"));
		scannedBefore = true;
		try {
			scannedComponents = getIntent().getStringArrayListExtra("scannedComponents");	
			System.out.println("scannedComponents from Intent size: "+scannedComponents.size());
		} catch (Exception e) {
			System.out.println("set scanned before false and create a new arraylist");
			scannedComponents = new ArrayList<String>();
			scannedBefore = false;
		}
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
	}
	
	public void navigateRight() {
		//Pickup a spare part with scanning the barcode		
		Intent intent = new Intent(this, BarcodeScanner.class);
		intent.putExtra("pickupspareparts", true);
		intent.putExtra("BikeEanNumber", getIntent().getExtras().getLong("EanNumber"));
		intent.putExtra("RepairOrder", "id");
		intent.putExtra("BikeName", getIntent().getExtras().getString("BikeName"));
		intent.putStringArrayListExtra("scannedComponents", scannedComponents);
		startActivity(intent);
	}

	@Override
	public void navigateLeft() {
		startActivity(new Intent(this, Overviewer.class));

	}

	@Override
	public void navigateUp() {

		startActivity(new Intent(this, RepairToDoList.class));

	}

	@Override
	public void navigateDown() {

		startActivity(new Intent(this, Overviewer.class));

	}
	

	public Boolean getRepairedCheck() {
		return repairedCheck;
	}

}
