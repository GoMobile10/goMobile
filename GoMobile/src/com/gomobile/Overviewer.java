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
import android.widget.Toast;

import com.gomobile.data.controller.BikeDataController;
import com.gomobile.data.controller.EmployeesDataController;
import com.gomobile.model.Bike;
import com.gomobile.model.Component;
import com.gomobile.model.Employee;
import com.gomobile.model.Order;
import com.gomobile.model.RepairOrder;
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
	List<RepairOrder> repairOrders;
	Bike currentBike; // for working in the view
	ArrayList<Component> pickuplist;
	BikeDataController databaseController; 
	EmployeesDataController employeeController;
	View itemView; 
	ArrayAdapter<Bike> BLVadapter;
	String prename;
	String lastname;
	boolean allsparepartspickedup;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		
		TextView textView = (TextView) findViewById(R.id.FirstName);
		textView.setText(getIntent().getExtras().getString("FirstName"));		
		
		
		prename = new String(getIntent().getExtras().getString("FirstName"));
		
		
		TextView textView2 = (TextView) findViewById(R.id.LastName);
		textView2.setText(getIntent().getExtras().getString("LastName"));
		
		lastname = new String(getIntent().getExtras().getString("LastName"));
		
		
		employeeController = new EmployeesDataController();
		List<Employee> empListe = employeeController.getAllEmployees();
		
		Employee emp = new Employee();
		emp = empListe.get(getIntent().getExtras().getInt("EmployeeCounter"));		
		
		repairOrders = employeeController.getAssignedOrders(emp);
		
		bikesToRepair = new ArrayList<Bike>();
		
		if(repairOrders.get(0) != null){
			
			bikesToRepair.add(repairOrders.get(0).getDefectBike());
		}
		
		
//		for (int i = 0; i < repairOrders.size(); i++) {
//			bikesToRepair.add(repairOrders.get(i).getDefectBike());
//		}	

		
		
		
//		//Create a database controller object to load the repair orders
//		databaseController = new BikeDataController();
//		bikesToRepair = databaseController.repairOrders();
//		
		populateBikeList();
		
		setContentView(createNavigationInfo(R.id.container,this,"back","detail",null,null));
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
		
		if( getIntent().getBooleanExtra("allsparepartspickedup", false) == true){
			allsparepartspickedup=true;
			((RepairListAdapter) BLVadapter).setworkDoneBikeEan(Long.parseLong(getIntent().getStringExtra("BikeEanNumber")));
			Toast.makeText(getApplicationContext(), "Repair Order completed!", Toast.LENGTH_LONG).show();
		}
		
	}
	
	public void startMode() {
		itemView.setBackgroundColor(Color.LTGRAY);
	}
	

	@Override
	public void navigateRight() {
		// paste the data between the two classes Overviewer and Pickuplist
		final Intent PickuplistOfOrder = new Intent(this, Pickuplist.class);
		
		PickuplistOfOrder.putExtra("FirstName", prename);
		PickuplistOfOrder.putExtra("LastName", lastname);
		
		Bike temp = bikesToRepair.get(itemcounter);
//		System.out.println("Ean: "+temp.getEanNumber());
		PickuplistOfOrder.putExtra("Description",temp.getDescription());
		
		PickuplistOfOrder.putExtra("BikeEanNumber",""+temp.getEanNumber());
		PickuplistOfOrder.putExtra("BikeName",temp.getDescription());
		startActivity(PickuplistOfOrder);


	}

	@Override
	public void navigateLeft() {
		startActivity(new Intent(this, Repairer.class));

	}

	@Override
	public void navigateUp() {
		System.out.println("navigateUp - itemcounter: "+itemcounter);
		if (itemcounter <= 0) {
			itemcounter = 0;
			((RepairListAdapter) BLVadapter).setCurrentPosition(itemcounter);
		} else {

			bikeListView.setItemChecked(itemcounter - 1, true);
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
			bikeListView.setItemChecked(itemcounter + 1, true);
			itemcounter = itemcounter + 1;
			((RepairListAdapter) BLVadapter).setCurrentPosition(itemcounter);
			
		}

	}


}


