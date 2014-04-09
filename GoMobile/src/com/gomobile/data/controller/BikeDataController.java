package com.gomobile.data.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.gomobile.scanner.model.Bike;
import com.gomobile.technicalservices.MySqlConnector;

public class BikeDataController {

	private final MySqlConnector sqlConnector;
	
	public BikeDataController(){
		sqlConnector = new MySqlConnector();
	}
	
	public Bike getBikeByEAN(long ean){
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("ean", ean + ""));
		sqlConnector.setQueryResultString( sqlConnector.getPHPRequestOutput("get_bike_data.php", nameValuePairs) );
		
		Bike bike = null;
		
		try{
			String[] resultFieldNames = {"description", "price", "category"};
			String[][] queryResult = sqlConnector.queryResultToArray(resultFieldNames);
			
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
