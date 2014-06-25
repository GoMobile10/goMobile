package com.gomobile;

import android.os.Bundle;

import com.gomobile.navigation.ViewWithNavigation;

public class RepairToDoList extends ViewWithNavigation{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.repair_to_do_list);
				
		setContentView(createNavigationInfo(R.id.repairToDoListId,this,"back","detail","help",null));

	}
	
	
	
	

	@Override
	public void navigateRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void navigateLeft() {
		// TODO Auto-generated method stub
		
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
