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
import android.widget.Toast;

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
	RepairOrder tempRepairOrder;
	boolean allsparepartspickedup;
	List<RepairOrder> repairorderlist;
	String prename;
	String lastname;
	String bikeEAN;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pickuplist);	
		
		
		prename = new String(getIntent().getExtras().getString("FirstName"));		
		lastname = new String(getIntent().getExtras().getString("LastName"));
		
		
		//Fill the view with static stuff		
				populateView();	
				//create a list with components for repairing
				populateSpareParts();
				//define some paramters
				scannedBefore = false;
				allsparepartspickedup = false;	
				//check if a spare part was scanned 
				checkScannedSpareParts();
				
				ListAdapter listenAdapter = new PickupSparePartsListAdapter(Pickuplist.this, R.layout.rowlayout_sparepartspickuplist_item ,needed_sparparts);		
				meineListView = (ListView) findViewById(R.id.listView1);
				meineListView.setAdapter(listenAdapter);
				
				setContentView(createNavigationInfo(R.id.pickuplistId,this,"back","detail","help","save"));

		
	}
	
	private void checkScannedSpareParts(){
		Intent intent = getIntent();
		System.out.println("scanned EAN: " + intent.getStringExtra("scannedEAN"));
		if (intent.getStringExtra("scannedEAN")!= null) {
			for (int i = 0; i < needed_sparparts.size(); i++) {
				System.out.println("i: "+ i +" "+needed_sparparts.get(i).getEanNumber() +"| "+ Long.parseLong(intent.getStringExtra("scannedEAN"), 10) );
				if(needed_sparparts.get(i).getEanNumber()== Long.parseLong(intent.getStringExtra("scannedEAN"), 10)){
					needed_sparparts.get(i).setPicukuped(true);
					Toast.makeText(getApplicationContext(), "You picked up: "+needed_sparparts.get(i).getDescription()+ " ("+needed_sparparts.get(i).getEanNumber()+")", Toast.LENGTH_LONG).show();
					if(scannedBefore == false){
						//System.out.println("scannedBefore false");
						
					}
					//System.out.println("add " +intent.getStringExtra("scannedEAN")+"to scanned Components");
					scannedComponents.add(intent.getStringExtra("scannedEAN"));
					scannedBefore = true;
				}
			}
		}
		//System.out.println("before scanned Components check ");
		if (intent.getStringExtra("scannedEAN") != null) {
			for (int h= 0; h < scannedComponents.size(); h++) {
				for (int i = 0; i < needed_sparparts.size(); i++) {
					//System.out.println("i and h: "+ i +" "+needed_sparparts.get(i).getEanNumber() +"| "+ Long.parseLong(scannedComponents.get(h), 10) );
					if(needed_sparparts.get(i).getEanNumber()== Long.parseLong(scannedComponents.get(h), 10)){
						//System.out.println("treffer! " +needed_sparparts.get(i).getEanNumber());
						needed_sparparts.get(i).setPicukuped(true);					}
				}
			}
		}
	}
	
	
	private void populateSpareParts(){
        needed_sparparts = new ArrayList<Component>();
        bikeEAN = ""+getIntent().getExtras().getString("BikeEanNumber");
        
        //Loading data from database
        BikeDataController bikeDataController = new BikeDataController();
        repairorderlist = bikeDataController.getRepairOrders("BikeEAN="+bikeEAN); 
        System.out.println("repairorderlist: "+ repairorderlist.size()+" | name: "+repairorderlist.get(0).getDefectBike().getDescription());
    	tempRepairOrder = repairorderlist.get(0);
        
    	//
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

		if(7613257813441L == tempRepairOrder.getDefectBike().getEanNumber()) {	
			repairPicture.setImageResource(com.gomobile.R.drawable.bike_wheelfront);
			repairDescription.setText(tempRepairOrder.getRepairDescription());
		}else{
			repairPicture.setImageResource(com.gomobile.R.drawable.bike_frame);
			repairDescription.setText(tempRepairOrder.getRepairDescription());		
		}
		repairPicture = (ImageView) findViewById(R.id.repairPic);
    	
    	//Get defect spare parts
    	HashMap<Component, Component> defectcomponents = (HashMap<Component, Component>) tempRepairOrder.getDefectReplacementComponentMap(); 
        Iterator it = defectcomponents.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            //System.out.println("key: "+((Component)pairs.getKey()).getEanNumber() + " |value: " + ((Component)pairs.getValue()).getEanNumber());
            needed_sparparts.add(((Component)pairs.getValue()));
            it.remove(); // avoids a ConcurrentModificationException   
        }
	}
	private void populateView(){
		
	}
	
	public void navigateRight() {
		//Pickup a spare part with scanning the barcode		
		Intent intent = new Intent(this, BarcodeScanner.class);
		intent.putExtra("pickupspareparts", true);
		
		
		intent.putExtra("BikeEanNumber", bikeEAN);
		intent.putExtra("FirstName", prename);
		intent.putExtra("LastName", lastname);
		intent.putExtra("RepairOrder", "id");
		intent.putExtra("BikeName", getIntent().getExtras().getString("BikeName"));
		intent.putStringArrayListExtra("scannedComponents", scannedComponents);
		
		startActivity(intent);
	}

	@Override
	public void navigateLeft() {
		Intent intent = new Intent(this, Overviewer.class);
		
		intent.putExtra("BikeName", getIntent().getExtras().getString("BikeName"));
		intent.putExtra("BikeEanNumber", bikeEAN);
		intent.putExtra("FirstName", prename);
		intent.putExtra("LastName", lastname);
		
		
		startActivity(intent);

	}

	@Override
	public void navigateUp() {

		final Intent repairTodoList = new Intent(this, RepairToDoList.class);
		repairTodoList.putExtra("BikeName", getIntent().getExtras().getString("BikeName"));
		repairTodoList.putExtra("BikeEanNumber", bikeEAN);
		repairTodoList.putExtra("FirstName", prename);
		repairTodoList.putExtra("LastName", lastname);
		startActivity(repairTodoList);

	}

	@Override
	public void navigateDown() {
		System.out.println("++++++++++++++++++++++++++++++++");
		Intent overviewer = new Intent(this, Overviewer.class);
		//todo: check if all spare parts picked up 
		allsparepartspickedup =true;
		//decide bike which should marked as work done
		if(allsparepartspickedup ==true){
			overviewer.putExtra("allsparepartspickedup", true);
			overviewer.putExtra("BikeEanNumber",bikeEAN);
			overviewer.putExtra("FirstName", prename);
			overviewer.putExtra("LastName", lastname);
		}else{
			overviewer.putExtra("allsparepartspickedup", false);
			overviewer.putExtra("BikeEanNumber",bikeEAN);
			overviewer.putExtra("FirstName", prename);
			overviewer.putExtra("LastName", lastname);
		}
			
		startActivity(overviewer);

	}
	

	public Boolean getRepairedCheck() {
		return repairedCheck;
	}

}
