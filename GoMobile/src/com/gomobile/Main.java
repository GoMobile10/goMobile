package com.gomobile;

import android.content.Intent;
import android.os.Bundle;
import com.gomobile.navigation.ViewWithNavigation;
import com.gomobile.shoppingcart.ShoppingCartView;
import com.gomobile.technicalservices.BarcodeScanner;

/**
 * Start of the app and the navigation. Connection between the RepairView and the CustomerView
 * @author Tim Endlich, Arndt Müller, Daniel Schopp
 *
 */
public class Main extends ViewWithNavigation {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
				
		setContentView(createNavigationInfo(R.id.mainId,this,"scan","person","shop",null));

	}
	public void navigateRight() {

		startActivity(new Intent(this, Repairer.class));
	}

	@Override
	public void navigateLeft() {
		startActivity(new Intent(this, BarcodeScanner.class));

	}

	@Override
	public void navigateUp() {
		
		startActivity(new Intent(this, ShoppingCartView.class));
	}

	@Override
	public void navigateDown() {
		// TODO Auto-generated method stub

	}

}
