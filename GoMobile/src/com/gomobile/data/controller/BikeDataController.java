package com.gomobile.data.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

import com.gomobile.model.Bike;
import com.gomobile.technicalservices.MySqlConnector;

/**
 * This class is a controller class and provides data consumption functionality.
 * It allows you to send requests for data about domain model objects and returns objects with equivalent definitions.
 * All requests only require parameters which correspond with fields of the object's classes.
 * @author Anton
 *
 */
public class BikeDataController {

	private final MySqlConnector sqlConnector;

	public BikeDataController(){
		sqlConnector = new MySqlConnector();
	}
	
	public ArrayList<Bike> getBikeList(String[] conditions){
		AsyncTask<String, Integer, ArrayList<Bike>> taskToExecute = new AsyncTask<String, Integer, ArrayList<Bike>>(){

			@Override
			protected ArrayList<Bike> doInBackground(String[] params) {

				String where_condition = "BikeEAN=EAN"; //default join condition
				
				//here params are conditions
				for(int i = 0; i < params.length; i++){
					where_condition = where_condition + " AND " + params[i];
				}

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("where_condition", where_condition));
				sqlConnector.setQueryResultString( sqlConnector.getPHPRequestOutput("get_bike_list.php", nameValuePairs) );

				ArrayList<Bike> resultList = new ArrayList<Bike>();
				

				try{
					String[] resultFieldNames = {"Description", "Price", "Category", "EAN"};
					String[][] queryResult = sqlConnector.queryResultToArray(resultFieldNames);
					Bike nextBike = null;

					int rowCount = queryResult.length;
					
					for(int i = 0; i < rowCount; i++){
						String name = queryResult[i][0];
						int price = Double.valueOf( queryResult[i][1] ).intValue();
						String category = queryResult[i][2];
						Long ean = Long.valueOf(queryResult[i][3]);
						
						nextBike = new Bike(ean, name, price, category);
						resultList.add(nextBike);
					}
					

				}
				catch(ClassNotFoundException cnfe){
					Log.e("BIKE DATA RETRIEVING ERROR", cnfe.getLocalizedMessage());
				}
				catch(SQLException se){
					Log.e("BIKE DATA RETRIEVING ERROR", se.getLocalizedMessage());
				}
				catch(Exception e){
					Log.e("BIKE DATA RETRIEVING ERROR", e.getLocalizedMessage());
				}

				return resultList;
			}

		};

		taskToExecute.execute(conditions);

		try {
			return taskToExecute.get();
		} catch (InterruptedException e) {
			Log.e("BIKE DATA RETRIEVING ERROR", e.getLocalizedMessage());
		} catch (ExecutionException e) {
			Log.e("BIKE DATA RETRIEVING ERROR", e.getLocalizedMessage());
		}

		return null;
	}
	
	// returns all the bikes which have a defect, stored in a List
	public ArrayList<Bike> repairOrders(){
			String[] conditions = {"isDefect='1'"};
			return this.getBikeList(conditions);
	}
		
	/**
	 * Returns the bike with the specified EAN code.
	 * @param ean the EAN of the bike
	 * @return the bike specified by the given EAN or null if the database don't contain an entry for a bike with the given EAN.
	 */
	public Bike getBikeByEAN(long ean){
		AsyncTask<Long, Integer, Bike> taskToExecute = new AsyncTask<Long, Integer, Bike>(){

			@Override
			protected Bike doInBackground(Long[] params) {

				long ean = params[0];

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("ean", ean + ""));
				sqlConnector.setQueryResultString( sqlConnector.getPHPRequestOutput("get_bike_data.php", nameValuePairs) );

				Bike bike = null;

				try{
					String[] resultFieldNames = {"description", "price", "category"};
					String[][] queryResult = sqlConnector.queryResultToArray(resultFieldNames);

					String name = queryResult[0][0];
					int price = Double.valueOf( queryResult[0][1] ).intValue();
					String category = queryResult[0][2];

					bike = new Bike(ean, name, price, category);

				}
				catch(ClassNotFoundException cnfe){
					Log.e("BIKE DATA RETRIEVING ERROR", cnfe.getLocalizedMessage());
				}
				catch(SQLException se){
					Log.e("BIKE DATA RETRIEVING ERROR", se.getLocalizedMessage());
				}
				catch(Exception e){
					Log.e("BIKE DATA RETRIEVING ERROR", e.getLocalizedMessage());
				}

				return bike;
			}

		};

		taskToExecute.execute(new Long[]{ean});

		try {
			return taskToExecute.get();
		} catch (InterruptedException e) {
			Log.e("BIKE DATA RETRIEVING ERROR", e.getLocalizedMessage());
		} catch (ExecutionException e) {
			Log.e("BIKE DATA RETRIEVING ERROR", e.getLocalizedMessage());
		}

		return null;
	}


	/**
	 * @return the sqlConnector
	 */
	public MySqlConnector getSqlConnector() {
		return sqlConnector;
	}
}