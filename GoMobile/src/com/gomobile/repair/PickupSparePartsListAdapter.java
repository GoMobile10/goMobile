package com.gomobile.repair;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gomobile.Overviewer;
import com.gomobile.R;
import com.gomobile.model.Bike;
import com.gomobile.model.Component;
import com.gomobile.shoppingcart.ShoppingCartItem;

/**
 * Own ListAdapter to integrate the view "pickuplist_items"
 * An adapter manages the data model and adapts it to the individual rows in the list view.
 *
 */
public class PickupSparePartsListAdapter extends ArrayAdapter<Component> {
	private Context context;
	private int layoutResourceId;
	private List<Component> spareparts;
	private Component currentSparepart;
	
	public PickupSparePartsListAdapter(Context context,int layoutResourceId, List<Component> spareparts) {
		
		super(context, layoutResourceId, spareparts);
		
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.spareparts = spareparts;
	}
	
	// Override method to fill the view with a individulized rowlayout
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(layoutResourceId, parent, false);
			System.out.println("convert view isnt null! ID: " + convertView.getId()+" String: "+convertView.toString());
		}
		// Find the bike to work with
		currentSparepart = (Component) spareparts.get(position);

		// Fill the view items with values
		
		ImageView imageView = (ImageView) convertView.findViewById(R.id.scannedPic);
		TextView description = (TextView) convertView.findViewById(R.id.sparepartDescription);
		
		if (currentSparepart.isPicukuped()==true){
			imageView.setImageResource(com.gomobile.R.drawable.scanned);
		}else{
			imageView.setImageResource(com.gomobile.R.drawable.missing);
		}	
		description.setText(currentSparepart.getDescription() +" " +currentSparepart.getEanNumber());
		return convertView;
	}
}