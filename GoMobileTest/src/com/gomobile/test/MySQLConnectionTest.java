/**
 * 
 */
package com.gomobile.test;

import com.gomobile.data.controller.BikeDataController;
import com.gomobile.scanner.model.Bike;

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
		assertEquals(499, exampleBike.getPrice());
		
		System.out.println("Bike: " + exampleBike.getName());
		System.out.println("Price: " + exampleBike.getPrice());
		System.out.println("Type: " + exampleBike.getType());
		
	}

}
