package com.gomobile.test;

import com.gomobile.data.controller.BikeDataController;
import com.gomobile.model.Bike;
import com.gomobile.model.Component;
import com.gomobile.model.RepairOrder;

import junit.framework.TestCase;

/**
* @author Anton
*
*/
public class MySQLConnectionTest extends TestCase {

	/**
	* Test method for {@link com.gomobile.data.controller.BikeDataController#getBikeByEAN(long)}.
	*/
	public void testGetBikeByEAN() {
		BikeDataController dataController = new BikeDataController();
		Bike exampleBike = dataController.getBikeByEAN(7613257813441L);
		assertEquals(499.0, exampleBike.getPrice());
		
		System.out.println("Bike: " + exampleBike.getDescription());
		System.out.println("Price: " + exampleBike.getPrice());
		System.out.println("Type: " + exampleBike.getCategory());
		
	}
	
	/**
	* Test method for {@link com.gomobile.data.controller.BikeDataController#getComponentByEAN(long)}.
	*/
	public void testGetComponentByEAN() {
		BikeDataController dataController = new BikeDataController();
		Component component = dataController.getComponentByEAN(8710259062606L);
		
		System.out.println("Component: " + component.getDescription());
		
	}
	

	/**
	* Test method for {@link com.gomobile.data.controller.BikeDataController#getBikeList(String[])}.
	*/
	public void testGetBikeList() {
		BikeDataController dataController = new BikeDataController();
		String[] noConditions = {};
		
		for(Bike bike : dataController.getBikeList(noConditions)){
			System.out.println("Bike found: " + bike.getDescription());
		}
		
	}
	
	/**
	* Test method for {@link com.gomobile.data.controller.BikeDataController#repairOrders()}.
	*/
	public void testRepairOrders() {
		BikeDataController dataController = new BikeDataController();
		String[] noConditions = {};
		
		for(Bike bike : dataController.repairOrders()){
			System.out.println("Bike to repair: " + bike.getDescription() + " EAN: " + bike.getEanNumber());
		}
				
	}
	
	/**
	* Test method for {@link com.gomobile.data.controller.BikeDataController#getRepairOrders(String)}.
	*/
	public void testGetRepairOrderList(){
		BikeDataController dataController = new BikeDataController();
		
		for(RepairOrder order : dataController.getRepairOrders("1")){
			System.out.println("Repair Order: " + order.getOrderID() + " BikeEAN: " + order.getDefectBike().getEanNumber() + " Delivery date: " + order.getDeliveryDateCalendar().getTime().toString());
			assertNotNull(order.getRepairDescription());
			assertNotNull(order.getDefectReplacementComponentMap());
			assertFalse(order.getDefectReplacementComponentMap().isEmpty());
		}
	}
	

	}