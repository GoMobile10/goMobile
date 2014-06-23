package com.gomobile;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gomobile.data.controller.BikeDataController;
import com.gomobile.model.Bike;
import com.gomobile.model.Component;
import com.gomobile.navigation.ViewWithNavigation;
import com.gomobile.repair.RepairListAdapter;

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

		
	}
	
	public void startMode() {
		//itemView.setBackgroundColor(Color.LTGRAY);
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
	public void navigateLeft() {
		startActivity(new Intent(this, Main.class));

	}

	@Override
	public void navigateUp() {
		System.out.println("navigateUp - itemcounter: "+itemcounter);
		System.out.println("navigateUp");
		if (itemcounter <= 0) {
			itemcounter = 0;
			//
			//itemView.setBackgroundColor(Color.TRANSPARENT);
			((RepairListAdapter) BLVadapter).setCurrentPosition(itemcounter);
		} else {
			bikeListView.setItemChecked(itemcounter - 1, true);
			//itemView.setBackgroundColor(Color.LTGRAY);
			itemcounter = itemcounter - 1;
			((RepairListAdapter) BLVadapter).setCurrentPosition(itemcounter);
		}

	}

	@Override
	public void navigateDown() {
		System.out.println("navigateDown - itemcounter: "+itemcounter);
		if (itemcounter >= bikesToRepair.size() - 1) {
			System.out.println("navigateDown if - itemcounter: "+itemcounter);
			//itemView.setBackgroundColor(Color.LTGRAY);
			itemcounter = bikesToRepair.size() - 1;
			((RepairListAdapter) BLVadapter).setCurrentPosition(itemcounter);
		} else {
			System.out.println("navigateDown else - itemcounter: "+itemcounter);
			bikeListView.setItemChecked(itemcounter + 1, true);
			System.out.println();
			
			//itemView.setBackgroundColor(Color.TRANSPARENT);
			itemcounter = itemcounter + 1;
			((RepairListAdapter) BLVadapter).setCurrentPosition(itemcounter);
			
		}

	}

}
