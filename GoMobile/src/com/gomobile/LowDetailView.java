package com.gomobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.gomobile.navigation.ViewWithNavigation;
import com.gomobile.scanner.model.Component;
import com.gomobile.technicalservices.BarcodeScanner;

public class LowDetailView extends ViewWithNavigation {

	private static boolean compare = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_low_detail_view);
		Bundle intentExtras = getIntent().getExtras();
		if(intentExtras != null && intentExtras.containsKey("compare"))
			compare = getIntent().getExtras().getBoolean("compare");
		
		display(ScannerController.getInstance().getComponentInUse());
	}

	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// switch (item.getItemId()) {
	// case android.R.id.home:
	// // This ID represents the Home or Up button. In the case of this
	// // activity, the Up button is shown. Use NavUtils to allow users
	// // to navigate up one level in the application structure. For
	// // more details, see the Navigation pattern on Android Design:
	// //
	// //
	// http://developer.android.com/design/patterns/navigation.html#up-vs-back
	// //
	// NavUtils.navigateUpFromSameTask(this);
	// return true;
	// case R.id.action_scanner:
	// startActivity(new Intent(this, BarcodeScanner.class));
	// return true;
	// case R.id.action_detail_view:
	// startActivity(new Intent(this, DetailView.class));
	// return true;
	// }
	// return super.onOptionsItemSelected(item);
	// }

	public void display(Component comp) {
		TextView textView = (TextView) findViewById(R.id.textViewName);
		textView.setText(comp.getName());
		textView = (TextView) findViewById(R.id.textViewPrice);
		textView.setText(String.valueOf(comp.getPrice()));
	}

	public void navigateRight() {
		startActivity(new Intent(this, DetailView.class));
	}

	public void navigateLeft() {
		if(compare)
			startActivity(new Intent(this, ComparisionView.class));
		else
			startActivity(new Intent(this, BarcodeScanner.class));
	}

	@Override
	public void navigateUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void navigateDown() {
		// TODO Auto-generated method stub

	}
}
