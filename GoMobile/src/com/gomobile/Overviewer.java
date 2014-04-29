package com.gomobile;

import java.util.ArrayList;
import java.util.List;
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

public class Overviewer extends ViewWithNavigation {
	
	int itemcounter = 0;
	// ListView meineListView;
	ListView bikeList;
	List<Bike> assemblingOrders;
	Bike currentBike;
	ArrayList<Component> pickuplist;
	BikeDataController repairList;
	View itemView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		repairList = new BikeDataController();
		assemblingOrders = repairList.repairOrders();

		populateBikeList();

		// ListAdapter listenAdapter = new ArrayAdapter(this,
		// android.R.layout.simple_list_item_1,assemblingOrders);
		// meineListView = (ListView) findViewById(R.id.listView1);
		//
		// meineListView.setAdapter(listenAdapter);
		// meineListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
		//
		//
		// //meineListView.setSelector(android.R.color.darker_gray);
		// meineListView.setSelector(R.drawable.bg);
		// meineListView.setItemChecked(0, true);
		// System.out.println("Selected Item ID: " +
		// meineListView.getSelectedItemId());

	}

	private void populateBikeList() {

		ArrayAdapter<Bike> adapter = new MyListAdapter();
		bikeList = (ListView) findViewById(R.id.listView1);
		bikeList.setAdapter(adapter);
		bikeList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

		bikeList.setItemChecked(0, true);
	}

	// Create own ListAdapter to integrate the view "pickuplist_items"
	private class MyListAdapter extends ArrayAdapter<Bike> {
		public MyListAdapter() {
			super(Overviewer.this, R.layout.activity_pickuplist_items,
					assemblingOrders);

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(
						R.layout.activity_pickuplist_items, parent, false);
			}
			// Find the bike to work with
			currentBike = assemblingOrders.get(position);

			// Fill the view items with values
			ImageView imageView = (ImageView) itemView
					.findViewById(R.id.repairPicTeaser);
			TextView textDate = (TextView) itemView.findViewById(R.id.date);

			switch (position) {
			case 0:
				imageView
						.setImageResource(com.gomobile.R.drawable.bike_wheelfront);
				textDate.setText("30.04.2014");

				break;
			case 1:
				imageView.setImageResource(com.gomobile.R.drawable.bike_frame);
				textDate.setText("01.05.2014");

				break;
			}

			// Fill the view items with values
			// imageView.setImageResource(currentBike.getIcon);
			TextView description = (TextView) itemView
					.findViewById(R.id.repairNameTeaser);
			description.setText(currentBike.getDescription());
			TextView date = (TextView) itemView
					.findViewById(R.id.repairDateTeaser);
			date.setText(currentBike.getCategory());

			return itemView;
		}

	}

	public void startMode() {
		itemView.setBackgroundColor(Color.LTGRAY);
	}

	@Override
	public void navigateRight() {

		// past the data between the two classes Overviewer and Pickuplist
		final Intent PickuplistOfOrder = new Intent(this, Pickuplist.class);
		PickuplistOfOrder.putExtra("Description",
				(assemblingOrders.get(itemcounter)).getDescription());
		PickuplistOfOrder.putExtra("EanNumber",
				(assemblingOrders.get(itemcounter)).getEanNumber());
		startActivity(PickuplistOfOrder);

	}

	@Override
	public void navigateLeft() {
		startActivity(new Intent(this, Main.class));

	}

	@Override
	public void navigateUp() {
		if (itemcounter <= 0) {
			itemcounter = 0;
			itemView.setBackgroundColor(Color.TRANSPARENT);

		} else {
			bikeList.setItemChecked(itemcounter - 1, true);
			itemView.setBackgroundColor(Color.LTGRAY);
			itemcounter = itemcounter - 1;

		}

	}

	@Override
	public void navigateDown() {
		if (itemcounter >= assemblingOrders.size() - 1) {
			itemView.setBackgroundColor(Color.LTGRAY);
			itemcounter = assemblingOrders.size() - 1;
		} else {
			bikeList.setItemChecked(itemcounter + 1, true);
			itemView.setBackgroundColor(Color.TRANSPARENT);
			itemcounter = itemcounter + 1;
		}

	}

}
