package com.gomobile;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



import com.gomobile.data.controller.BikeDataController;
import com.gomobile.model.Bike;
import com.gomobile.model.Component;
import com.gomobile.navigation.ViewWithNavigation;
import com.gomobile.repair.RepairListAdapter;

/**
 * 
 * @author danielschopp, Patrick, Daniel
 *
 */

public class Overviewer extends ViewWithNavigation {
	
	int itemcounter = 0;
	ListView bikeListView;
	List<Bike> bikesToRepair;
	Bike currentBike; // for working in the view
	ArrayList<Component> pickuplist;
	BikeDataController databaseController; 
	View itemView; //
	ArrayAdapter<Bike> BLVadapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Create a database controller object to load the repair orders
		databaseController = new BikeDataController();
		bikesToRepair = databaseController.repairOrders();
		populateBikeList();
		
		setContentView(createNavigationInfo(R.id.container,this,"back","detail","help","save"));
	}
	/**
	 * populate a list with bikes 
	 * using a modified ListAdapter
	 */
	private void populateBikeList() {
		BLVadapter = new RepairListAdapter(Overviewer.this, R.layout.rowlayout_repairlist_item, bikesToRepair );
		bikeListView = (ListView) findViewById(R.id.listView1);
		bikeListView.setAdapter(BLVadapter);
		bikeListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
		bikeListView.setItemChecked(0, true);
		
	}
	
	@Override
	public void navigateLeft() {
		startActivity(new Intent(this, Main.class));

	}
	@Override
	public void navigateRight() {
		// paste the data between the two classes Overviewer and Pickuplist
		final Intent PickuplistOfOrder = new Intent(this, Pickuplist.class);
		PickuplistOfOrder.putExtra("Description",
				(bikesToRepair.get(itemcounter)).getDescription());
		PickuplistOfOrder.putExtra("EanNumber",
				(bikesToRepair.get(itemcounter)).getEanNumber());
		startActivity(PickuplistOfOrder);
	}

	@Override
	public void navigateUp() {
		System.out.println("navigateUp - itemcounter: "+itemcounter);
		if (itemcounter <= 0) {
			itemcounter = 0;
			((RepairListAdapter) BLVadapter).setCurrentPosition(itemcounter);
		} else {
			//bikeListView.setItemChecked(itemcounter - 1, true);
			itemcounter = itemcounter - 1;
			((RepairListAdapter) BLVadapter).setCurrentPosition(itemcounter);
		}

	}

	@Override
	public void navigateDown() {
		System.out.println("navigateDown - itemcounter: "+itemcounter);
		if (itemcounter >= bikesToRepair.size() - 1) {
			itemcounter = bikesToRepair.size() - 1;
			((RepairListAdapter) BLVadapter).setCurrentPosition(itemcounter);
		} else {
			//bikeListView.setItemChecked(itemcounter + 1, true);
			itemcounter = itemcounter + 1;
			((RepairListAdapter) BLVadapter).setCurrentPosition(itemcounter);
			
		}

	}


}


