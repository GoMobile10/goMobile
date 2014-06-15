package com.gomobile.test;

import com.gomobile.data.controller.BikeDataController;
import com.gomobile.model.Bike;

import junit.framework.TestCase;

/**
* @author Anton
*
*/
public class SAPConnectionTest extends TestCase {

	/**
	* Test method for {@link com.gomobile.data.controller.BikeDataController#getBikeByEAN(long)}.
	*/
	public void testGetBikeByEAN() {
		BikeDataController dataController = new BikeDataController();
		Bike exampleBike = dataController.getBikeByEAN(7613257813441L);
		
		System.out.println("Bike: " + exampleBike.getDescription());
		
		
	}
	

}