package com.gomobile;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.gomobile.data.controller.EmployeesDataController;
import com.gomobile.model.Employee;
import com.gomobile.navigation.ViewWithNavigation;
import com.gomobile.repair.EmployeeListAdapter;


public class Repairer extends ViewWithNavigation {
	
	int itemcounter = 0;
	ListView employeeListView;
	List<Employee> employees;
	EmployeesDataController employeeData;
	View itemView; 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repair);
		
		//Create a database controller object to load all the employees of the bike shop
		
		employeeData = new EmployeesDataController();
		employees = employeeData.getAllEmployees();
		
		setContentView(createNavigationInfo(R.id.repairID,this,"back","detail",null,null));
		
		displayEmployeeList();
	}
	/**
	 * populate a list with bikes 
	 * using a modified ListAdapter
	 */
	private void displayEmployeeList() {
		
		
		EmployeeListAdapter adapter = new EmployeeListAdapter(Repairer.this, 
				R.layout.rowlayout_employeelist_item, employees);
		employeeListView = (ListView) findViewById(R.id.listView2);
		employeeListView.setAdapter(adapter);
		employeeListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
		employeeListView.setItemChecked(0, true);
		
	}
	
	
	@Override
	public void navigateRight() {
		
		final Intent OverviewofEmployee = new Intent(this, Overviewer.class);
		OverviewofEmployee.putExtra("FirstName",
				(employees.get(itemcounter)).getFirstName());
		OverviewofEmployee.putExtra("LastName",
				(employees.get(itemcounter)).getLastName());
		
		OverviewofEmployee.putExtra("EmployeeCounter", itemcounter);
		
		
		startActivity(OverviewofEmployee);
		
		

	}

	@Override
	public void navigateLeft() {
		startActivity(new Intent(this, Main.class));

	}

	@Override
	public void navigateUp() {
		if (itemcounter <= 0) {
			itemcounter = 0;
			//itemView.setBackgroundColor(Color.TRANSPARENT);

		} else {
			employeeListView.setItemChecked(itemcounter - 1, true);
			//itemView.setBackgroundColor(Color.LTGRAY);
			itemcounter = itemcounter - 1;

		}

	}

	@Override
	public void navigateDown() {
		if (itemcounter >= employees.size() - 1) {
			//itemView.setBackgroundColor(Color.LTGRAY);
			itemcounter = employees.size() - 1;
		} else {
			employeeListView.setItemChecked(itemcounter + 1, true);
			//itemView.setBackgroundColor(Color.TRANSPARENT);
			itemcounter = itemcounter + 1;
		}

	}

}
