package com.gomobile;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.gomobile.navigation.ViewWithNavigation;
import com.gomobile.repair.ToDoListAdapter;

public class RepairToDoList extends ViewWithNavigation{
	ListView repairToDoListItem;
	List<String> repairToDoListData;
	String prename;
	String lastname;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.repair_to_do_list);
		
		prename = new String(getIntent().getExtras().getString("FirstName"));		
		lastname = new String(getIntent().getExtras().getString("LastName"));
		
		repairToDoListData = new ArrayList<String>();
		repairToDoListData.add("#1 Open the screws at the front wheel");
		repairToDoListData.add("#2 Change the defect tube");
		repairToDoListData.add("#3 Put the wheel together");
		repairToDoListData.add("#4 Fill the tube with air");
		repairToDoListData.add("#5 Put the wheel back into the frame");

	
		//ListAdapter listenAdapter = new PickupSparePartsListAdapter(RepairToDoList.this, R.layout.rowlayout_sparepartspickuplist_item ,repairToDoListData);		
		ListAdapter listAdapter = new ToDoListAdapter(RepairToDoList.this, R.layout.rowlayout_todolist_item,repairToDoListData);
		repairToDoListItem = (ListView) findViewById(R.id.repairToDoListItem);
		repairToDoListItem.setAdapter(listAdapter);
		
				
		setContentView(createNavigationInfo(R.id.repairToDoListId,this,"back",null,null,null));

	}

	
	
	@Override
	public void navigateRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void navigateLeft() {
		
		final Intent pickuplist = new Intent(this, Pickuplist.class);
		pickuplist.putExtra("FirstName", prename);
		pickuplist.putExtra("LastName", lastname);
		pickuplist.putExtra("BikeName", getIntent().getExtras().getString("BikeName"));
		pickuplist.putExtra("BikeEanNumber", getIntent().getExtras().getString("BikeEanNumber"));
		startActivity(pickuplist);
		
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
