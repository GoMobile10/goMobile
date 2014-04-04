package com.gomobile.data.controller;

import java.sql.SQLException;

import com.gomobile.scanner.model.Bike;
import com.gomobile.technicalservices.MySqlConnector;

public class BikeDataController {

	private final MySqlConnector sqlConnector;
	
	public BikeDataController(){
		sqlConnector = new MySqlConnector();
	}
	
	public Bike getBikeByEAN(long ean){
		
		Bike bike = null;
		
		try{
		sqlConnector.setQueryString("SELECT bike.description, material.price, bike.category FROM bike, material WHERE bike.bikeean = material.ean AND bike.bikeean = " + ean);
		String[][] queryResult = sqlConnector.queryResultToArray();
		
		String name = queryResult[0][0];
		int price = Double.valueOf( queryResult[0][1] ).intValue();
		String type = queryResult[0][2];
		
		bike = new Bike(name, price, type);
		
		}
		catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		
		return bike;
	}

	/**
	 * @return the sqlConnector
	 */
	public MySqlConnector getSqlConnector() {
		return sqlConnector;
	}
}
