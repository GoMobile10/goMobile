package com.gomobile.repair;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gomobile.Overviewer;
import com.gomobile.R;
import com.gomobile.model.Bike;
import com.gomobile.shoppingcart.ShoppingCartItem;

/**
 * Own ListAdapter to integrate the view "pickuplist_items"
 * An adapter manages the data model and adapts it to the individual rows in the list view.
 *
 */
public class RepairListAdapter extends ArrayAdapter<Bike> {
	private Context context;
	private int layoutResourceId;
	private List<Bike> bikesToRepair;
	private Bike currentBike;
	private View itemView; //needed for navigate down / up
	private int currentposition; // for highlighting
	
	public RepairListAdapter(Context context,int layoutResourceId, List<Bike> bikesToRepair) {
		
		super(context, layoutResourceId, bikesToRepair);
		
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.bikesToRepair = bikesToRepair;
		currentposition=0;
		
	}
	
	// Override method to fill the view with a individulized rowlayout
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(layoutResourceId, parent, false);
		}
		System.out.println("getView|pos: "+position +" convertView: "+convertView.toString() +" viewgroup: "+parent.toString());

		//Initialize for navigation
		itemView = convertView;
		// Find the bike to work with
		currentBike = bikesToRepair.get(position);

		// Fill the view items with values
		ImageView imageView = (ImageView) convertView.findViewById(R.id.repairPicTeaser);
		TextView textDate = (TextView) convertView.findViewById(R.id.date);
		TextView date = (TextView) convertView.findViewById(R.id.repairDescriptionTeaser);
		TextView description = (TextView) convertView.findViewById(R.id.repairNameTeaser);
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
		description.setText(currentBike.getDescription());
		date.setText(currentBike.getCategory());
		if(position == currentposition){
			convertView.setBackgroundColor(Color.LTGRAY);
		}else{
			convertView.setBackgroundColor(Color.TRANSPARENT);
		}
			

		return convertView;
	}
	public View getItemView(){
		return itemView;
	}
	// Through this method the activity can control the highlighted item
	public void setCurrentPosition(int currentposition){
		System.out.println("Set New Highlighted position: "+currentposition);
		this.currentposition = currentposition;
	}
}
