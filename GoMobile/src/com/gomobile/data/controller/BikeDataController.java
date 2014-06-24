package com.gomobile.data.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

import com.gomobile.model.Bike;
import com.gomobile.model.Component;
import com.gomobile.model.RepairOrder;
import com.gomobile.technicalservices.MySqlConnector;
import com.gomobile.technicalservices.SAPConnector;

/**
 * This class is a controller class and provides data consumption functionality.
 * It allows you to send requests for data about domain model objects and returns objects with equivalent definitions.
 * All requests only require parameters which correspond with fields of the object's classes.
 * @author Anton
 *
 */
public class BikeDataController {

	private final MySqlConnector sqlConnector;
	private final SAPConnector SAPConnector;

	public BikeDataController(){
		sqlConnector = new MySqlConnector();
		SAPConnector = new SAPConnector();
		
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

				System.out.println("Where clause: " + where_condition);
				
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("where_condition", where_condition));
				System.out.println("nameValuePairs: "+ nameValuePairs.toString());
				sqlConnector.setQueryResultString( sqlConnector.getPHPRequestOutput("get_bike_list.php", nameValuePairs) );
				System.out.println("Querry String: " + sqlConnector.getPHPRequestOutput("get_bike_list.php", nameValuePairs));
				ArrayList<Bike> resultList = new ArrayList<Bike>();
				

				try{
					String[] resultFieldNames = {"Description", "Price", "Category", "EAN","isDefect"};
					String[][] queryResult = sqlConnector.queryResultToArray(resultFieldNames);
					Bike nextBike = null;

					int rowCount = queryResult.length;
					
					for(int i = 0; i < rowCount; i++){
						String name = queryResult[i][0];
						int price = Double.valueOf( queryResult[i][1] ).intValue();
						String category = queryResult[i][2];
						Long ean = Long.valueOf(queryResult[i][3]);
						int isDefect = Integer.valueOf( queryResult[i][4] ).intValue();
						System.out.println("isDefect?: "+ isDefect);
						nextBike = new Bike(ean, name, price, category,isDefect);
						resultList.add(nextBike);
					}
					

				}
				catch(ClassNotFoundException cnfe){
				cnfe.printStackTrace();
//					Log.e("BIKE DATA RETRIEVING ERROR", cnfe.getLocalizedMessage());
				}
				catch(SQLException se){
					se.printStackTrace();
//					Log.e("BIKE DATA RETRIEVING ERROR", se.getLocalizedMessage());
				}
				catch(Exception e){
					e.printStackTrace();
//					Log.e("BIKE DATA RETRIEVING ERROR", e.getLocalizedMessage());
				}

				return resultList;
			}

		};

		taskToExecute.execute(conditions);

		try {
			return taskToExecute.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
//			Log.e("BIKE DATA RETRIEVING ERROR", e.getLocalizedMessage());
		} catch (ExecutionException e) {
			e.printStackTrace();
//			Log.e("BIKE DATA RETRIEVING ERROR", e.getLocalizedMessage());
		}

		return null;
	}
	
	// returns all the bikes which have a defect, stored in a List
	public ArrayList<Bike> repairOrders(){
			String[] conditions = {"isDefect=1"};
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
//				SAPConnector.setODATA_SERVICE_URL("http://vm20.hcc.uni-magdeburg.de:8000/sap/opu/odata/sap/Z_MAT_SEARCH_EAN/SearchMaterial(EAN='" + ean +"')/?$format=json");

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("ean", ean + ""));
				sqlConnector.setQueryResultString( sqlConnector.getPHPRequestOutput("get_bike_data.php", nameValuePairs) );

				Bike bike = null;
//				String name = SAPConnector.getJSONDATA().get("Material");
//				long eanfromquery = Long.valueOf(SAPConnector.getJSONDATA().get("EAN"));
//				bike = new Bike(eanfromquery, name);

				try{
					String[] resultFieldNames = {"description", "price", "category"};/*, "weight", "IsAvailable"};*/
					String[][] queryResult = sqlConnector.queryResultToArray(resultFieldNames);

					String name = queryResult[0][0];
					int price = Double.valueOf( queryResult[0][1] ).intValue();
					String category = queryResult[0][2];
					bike = new Bike(ean, name, price, category);
					/*int weight = Integer.valueOf( queryResult[0][3]);
					boolean isAvailable = Integer.valueOf(queryResult[0][4]) == 1;

					bike = new Bike(ean, name, price, category, weight, isAvailable);*/


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

	public List<RepairOrder> getRepairOrders (String condition){
		AsyncTask<String, Integer, List<RepairOrder>> taskToExecute = new AsyncTask<String, Integer, List<RepairOrder>>(){

			@Override
			protected List<RepairOrder> doInBackground(String[] params) {

				String condition = params[0];
				List<RepairOrder> resultList = new ArrayList<RepairOrder>();
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("where_condition", condition));
				String requestResult = sqlConnector.getPHPRequestOutput("get_repairorder_list.php", nameValuePairs);
				sqlConnector.setQueryResultString(requestResult);
				
				try{
					String[][] jsonArray = sqlConnector.queryResultToArray(new String[]{"OrderID", "BikeEAN", "employeeid", "deliverydate", "cost", "repairdescription"});
					int rowCount = jsonArray.length;
					
					for (int i = 0; i < rowCount; i++){
						RepairOrder tempRepairOrder = new RepairOrder();
						tempRepairOrder.setOrderID(Integer.valueOf(jsonArray[i][0]));
						
						Bike bike = new Bike(Long.valueOf(jsonArray[i][1]), null);
						
						tempRepairOrder.setDefectBike(bike);
						tempRepairOrder.setEmployeeID(Integer.valueOf(jsonArray[i][2]));
						
						String[] dateAsArray = jsonArray[i][3].split("-");
						Calendar deliverDateCalendar = Calendar.getInstance();
						deliverDateCalendar.set(Integer.valueOf(dateAsArray[0]), Integer.valueOf(dateAsArray[1]), Integer.valueOf(dateAsArray[2]));
						tempRepairOrder.setDeliveryDateCalendar(deliverDateCalendar);
						
						tempRepairOrder.setCost(Float.valueOf(jsonArray[i][4]));
						tempRepairOrder.setRepairDescription(jsonArray[i][5]);
						resultList.add(tempRepairOrder);
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
		
		taskToExecute.execute(new String[]{condition});

		try {
			List<RepairOrder> resultList = taskToExecute.get();
			
			for(RepairOrder repairOrder : resultList){
				Bike bike = getBikeByEAN(repairOrder.getDefectBike().getEanNumber());
				repairOrder.setDefectBike(bike);
				this.setComponentMap(repairOrder);
			}
			
			return resultList;
		} catch (InterruptedException e) {
			Log.e("BIKE DATA RETRIEVING ERROR", e.getLocalizedMessage());
		} catch (ExecutionException e) {
			Log.e("BIKE DATA RETRIEVING ERROR", e.getLocalizedMessage());
		}

		return null;
	}
	
	/**
	 * Fills the map for a repair order that corresponds to a bike.
	 * Each entry contains a defect component as the key and the wished replacement component as the value.
	 * @param repairOrder
	 */
	private void setComponentMap(RepairOrder repairOrder){
		AsyncTask<RepairOrder, Integer, String[][]> taskToExecute = new AsyncTask<RepairOrder, Integer, String[][]>(){

			@Override
			protected String[][] doInBackground(RepairOrder[] params) {

				RepairOrder repairOrder = params[0];
				
				//TODO: implement logic
				try{
					List<RepairOrder> resultList = new ArrayList<RepairOrder>();
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					nameValuePairs.add(new BasicNameValuePair("where_condition", "defectbikeEAN = " + repairOrder.getDefectBike().getEanNumber()));
					String requestResult = sqlConnector.getPHPRequestOutput("get_repairorder_list.php", nameValuePairs);
					sqlConnector.setQueryResultString(requestResult);
					
					return sqlConnector.queryResultToArray(new String[]{"OrderID", "defectcomponentean", "replacementcomponentean"});
					
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
		
				return null;
			
			}
		};
		
		try{
			taskToExecute.execute(new RepairOrder[]{repairOrder});
			String[][] jsonArray = taskToExecute.get();
			int rowCount = jsonArray.length;
			
			for(int i = 0; i < rowCount; i++){
				Component defectComponent = getComponentByEAN(Long.valueOf(jsonArray[i][1]));
				Component replacementComponent = getComponentByEAN(Long.valueOf(jsonArray[i][2]));
				repairOrder.setDefectReplacementComponentMap(new HashMap<Component, Component>());
				repairOrder.getDefectReplacementComponentMap().put(defectComponent, replacementComponent);
				Log.i("TEST setComponentMap", "Defect component: " + defectComponent.getDescription() + " Replacement coponent: " + replacementComponent.getDescription());
			}
		}
		catch(Exception e){
			Log.e("BIKE DATA RETRIEVING ERROR", e.getLocalizedMessage());
		}

	}
	
	public Component getComponentByEAN(long ean){
		AsyncTask<Long, Integer, Component> taskToExecute = new AsyncTask<Long, Integer, Component>(){

			@Override
			protected Component doInBackground(Long[] params) {

				long ean = params[0];

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("ean", ean + ""));
				sqlConnector.setQueryResultString( sqlConnector.getPHPRequestOutput("get_component_data.php", nameValuePairs) );

				Component component = null;

				try{
					String[] resultFieldNames = {"description", "price", "AverageAssemblingTime"};
					String[][] queryResult = sqlConnector.queryResultToArray(resultFieldNames);

					String description = queryResult[0][0];
					int price = Double.valueOf( queryResult[0][1] ).intValue();
					int averageAssemblingTime = Integer.valueOf(queryResult[0][2]);
					
					component = new Component(ean, description, averageAssemblingTime, null);
					component.setPrice(price);
					
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

				return component;
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
	 * Returns a list of components compatible with the specified bike.
	 * @param bikeEAN the EAN that specifies the bike.
	 * @return a list of compatible components
	 */
	public List<Component> getCompatibleComponents(long bikeEAN){
		AsyncTask<Long, Integer, List<Component>> taskToExecute = new AsyncTask<Long, Integer, List<Component>>(){


			@Override
			protected List<Component> doInBackground(Long[] params) {

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("where_condition", "compability.BikeID = " + params[0]));
				
				sqlConnector.setQueryResultString( sqlConnector.getPHPRequestOutput("get_component_list.php", nameValuePairs) );
				
				
				List<Component> resultList = new ArrayList<Component>();
				
				try{
					String[] resultFieldNames = {"ComponentEAN", "Description", "AverageAssemblingTime"};
					String[][] queryResult = sqlConnector.queryResultToArray(resultFieldNames);
					
					Component nextComponent = null;

					int rowCount = queryResult.length;
					
					for(int i = 0; i < rowCount; i++){
						long ean = Long.valueOf(queryResult[i][0]);
						String description = queryResult[i][1];
						int averageAssemblingTime = Integer.valueOf(queryResult[i][2]);
						
						nextComponent = new Component(ean, description, 0);
						resultList.add(nextComponent);
					}
					

				}
				catch(ClassNotFoundException cnfe){
					Log.e("COMPONENT DATA RETRIEVING ERROR", cnfe.getLocalizedMessage());
				}
				catch(SQLException se){
					Log.e("COMPONENT DATA RETRIEVING ERROR", se.getLocalizedMessage());
				}
				catch(Exception e){
					e.printStackTrace();
//					Log.e("COMPONENT DATA RETRIEVING ERROR", e.printStackTrace(););
				}

				return resultList;
			}

		};

		taskToExecute.execute(bikeEAN);

		try {
			return taskToExecute.get();
		} catch (InterruptedException e) {
			Log.e("COMPONENT DATA RETRIEVING ERROR", e.getLocalizedMessage());
		} catch (ExecutionException e) {
			Log.e("COMPONENT DATA RETRIEVING ERROR", e.getLocalizedMessage());
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
