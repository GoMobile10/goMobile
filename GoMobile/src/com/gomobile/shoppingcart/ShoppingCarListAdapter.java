package com.gomobile.shoppingcart;

import java.text.NumberFormat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gomobile.R;
/**
 * Own list adapter to use the ourer custom list layout
 * @author tendlich
 *
 */
public class ShoppingCarListAdapter extends ArrayAdapter<ShoppingCartItem> {

	private Context mContext;
	private int layoutResourceId;
	private ShoppingCartItem shoppingCartItems[];

	public ShoppingCarListAdapter(Context mContext, int layoutResourceId, ShoppingCartItem[] data) {

		super(mContext, layoutResourceId, data);

		this.layoutResourceId = layoutResourceId;
		this.mContext = mContext;
		this.shoppingCartItems = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		//
		if (convertView == null) {
			// inflate the layout
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			convertView = inflater.inflate(layoutResourceId, parent, false);
		}

		// object item based on the position
		ShoppingCartItem shoppingCartItem = shoppingCartItems[position];

		
		TextView textViewItem = (TextView) convertView.findViewById(R.id.title);
		textViewItem.setText(shoppingCartItem.getName());
		textViewItem.setTag(shoppingCartItem.getEAN());
		
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
		double price = shoppingCartItem.getPrice();
		textViewItem = (TextView) convertView.findViewById(R.id.price);
		textViewItem.setText(currencyFormatter.format(price));
		
		textViewItem = (TextView) convertView.findViewById(R.id.amount);
		textViewItem.setText(String.valueOf(shoppingCartItem.getQuantity()));

		return convertView;

	}

}
