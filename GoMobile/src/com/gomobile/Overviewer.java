package com.gomobile;

import java.util.ArrayList;

import com.gomobile.model.Order;
import com.gomobile.navigation.ViewWithNavigation;
import com.gomobile.scanner.model.Part;
import com.gomobile.technicalservices.BarcodeScanner;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class Overviewer extends ViewWithNavigation{
int itemcounter = 0;
ListView meineListView;
ArrayList<Order> assemblingOrders;
ArrayList<Part> pickuplist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		System.out.println("Hier!");
		//assemblingOrders = new ArrayList<String>();
		assemblingOrders = new ArrayList<Order>();
		//assemblingOrders.add("AssemblingOrder 1");
		//assemblingOrders.add("AssemblingOrder 2");
		//assemblingOrders.add("AssemblingOrder 3");
		//Generating sample orders
		pickuplist = new ArrayList<Part>();
		pickuplist.add(new Part("Part1",9,"Bremse"));
		pickuplist.add(new Part("Part2",5,"Rahmen"));
		assemblingOrders.add(new Order(1,pickuplist));
		pickuplist.add(new Part("Part3",3,"Sattel"));
		assemblingOrders.add(new Order(2,pickuplist));
		pickuplist.add(new Part("Part4",4,"Sattel"));
		assemblingOrders.add(new Order(3,pickuplist));
		ListAdapter listenAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1,assemblingOrders);
		meineListView = (ListView) findViewById(R.id.listView1);
		
		meineListView.setAdapter(listenAdapter);
		meineListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
		

		//meineListView.setSelector(android.R.color.darker_gray);
		meineListView.setSelector(R.drawable.bg);
		meineListView.setItemChecked(0, true);
		System.out.println("Selected Item ID: " + meineListView.getSelectedItemId());
		
		
		//meineListView.setClickable(false);
		//meineListView.setEnabled(false);
		
		
		/*meineListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		  @Override
		  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
			  System.out.println("Position Click: "+position);
			  //setItemChecked(position, true);
			  //arg1.getFocusables(position);
			  //arg1.setSelected(true);
		  }
		});
		*/
		
	}
	

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}


	@Override
	public void navigateRight() {
		System.out.println("Item / Order number: "+itemcounter+" selected!");
		//todo: Open Detail Orderview or Pickup List
		//startActivity(new Intent(this, Pickuplist.class));

        final Intent PickuplistOfOrder = new Intent(this, Pickuplist.class);
        System.out.println("Ordernumber: "+((Order)assemblingOrders.get(itemcounter)).getOrderNumber());
        PickuplistOfOrder.putExtra("OrderID", ((Order)assemblingOrders.get(itemcounter)).getOrderNumber());
//        //ArrayList<Part> temp_pickuplist =  (ArrayList<Part>)((Order)assemblingOrders.get(itemcounter)).getPickuplist();
//        //System.out.println("temp_pickuplist: "+ temp_pickuplist.size());
//        
//        //PickuplistOfOrder.putParcelableArrayListExtra("PL", temp_pickuplist);
//
        startActivity(PickuplistOfOrder);
		
	}

	@Override
	public void navigateLeft() {
//		startActivity(new Intent(this, Main.class));
		
	}

	@Override
	public void navigateUp() {
		 if(itemcounter <= 0){
	    	 itemcounter=0;
	     }else{
		     meineListView.setItemChecked(itemcounter-1,true);
		     itemcounter = itemcounter - 1;	
	     }
		
	}

	@Override
	public void navigateDown() {
		System.out.println("Itemcounter: " +itemcounter);
		 if(itemcounter >= assemblingOrders.size()-1){
	    	 itemcounter = assemblingOrders.size()-1;
	     }else{
	    	 meineListView.setItemChecked(itemcounter+1,true);
		     itemcounter = itemcounter + 1;
	     }
		
	}


}
