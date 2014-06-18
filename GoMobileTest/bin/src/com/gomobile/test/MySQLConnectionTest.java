package com.gomobile.test;

import com.gomobile.data.controller.BikeDataController;
import com.gomobile.model.Bike;

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
//		assertEquals(499.0, exampleBike.getPrice());
		
		System.out.println("Bike: " + exampleBike.getDescription());
		System.out.println("Price: " + exampleBike.getPrice());
		System.out.println("Type: " + exampleBike.getCategory());
		
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

}