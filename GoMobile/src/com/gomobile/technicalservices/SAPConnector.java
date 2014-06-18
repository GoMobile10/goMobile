package com.gomobile.technicalservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
* This class retrieves data from a SAP oData Service.
* @author Anton & Anna
*
*/
public class SAPConnector {
	
	private String ODATA_SERVICE_URL;
	
	/**
	 * @return the oDATA_SERVICE_URL
	 */
	public String getODATA_SERVICE_URL() {
		return ODATA_SERVICE_URL;
	}


	/**
	 * @param oDATA_SERVICE_URL the oDATA_SERVICE_URL to set
	 */
	public void setODATA_SERVICE_URL(String oDATA_SERVICE_URL) {
		ODATA_SERVICE_URL = oDATA_SERVICE_URL;
	}

	/**
	 * Send an oData request and return the result.
	 * @param url The URL of the RESTService 
	 * @return queryResult The result of the request as string.
	 */
	public String getODataQueryString(String url){
		String queryResult = "";
		
		HttpURLConnection connection = null;
		
		try {
		    
		    Authenticator.setDefault(new Authenticator() {
			
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
			    Log.i("REQUESTING SCHEM", getRequestingScheme());
			    return new PasswordAuthentication("S_SCHMI", "GoMobile2013".toCharArray());
			}
		    });
		    
		    connection = (HttpURLConnection)(new URL(url).openConnection());
		    connection.setRequestMethod("GET");
		    connection.setDoInput(true);
		    connection.connect();
		    
		    Log.i("RESPONSE MASSAGE", connection.getResponseMessage());
		     
		    InputStream inStream = (InputStream) connection.getInputStream();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
		    StringBuilder stringBuilder = new StringBuilder();
		    String line = "";
			
		    while(line != null){
			line = reader.readLine();
			stringBuilder.append(line + "\n");
		    }
		    
		    queryResult = stringBuilder.toString();
		    inStream.close();
					
		    Log.i("OData Request", queryResult);
		} catch (ClientProtocolException e) {
		    Log.e("Data Connection", e.getMessage(), e);
		    e.printStackTrace();
		} catch (IOException e) {
		    Log.e("Data Connection", e.getMessage(), e);
		    e.printStackTrace();
		} finally{
		    connection.disconnect();
		    
		}
		
		
			//TODO: REFACTORING
//			BikeDataController bikeDataController = new BikeDataController();
//			Long ean = Long.valueOf(url[0]);
//			Bike bike = bikeDataController.getBikeByEAN(ean);
//			String bikeDescription = bike.getDescription() + "\n" + bike.getPrice() + " EUR\n" + bike.getCategory();
			
			return queryResult;
	}
	
	/**
	 * Converts a JSON String into a map.
	 * @return resultMap A Map, which contains the JSON data objects as key value pairs.
	 */
	public Map<String,String> getJSONDATA(){
		
		Map<String,String> resultMap = new HashMap<String, String>();
		
		//parse json data
				try{
					//JSONArray jArray = new JSONArray(this.getODataQueryString(ODATA_SERVICE_URL));
					 
					JSONObject rootobject= new JSONObject(this.getODataQueryString(ODATA_SERVICE_URL));
					JSONObject metadataobject = rootobject.getJSONObject("d");					
					String Material = metadataobject.getString("Material");
					String EAN = metadataobject.getString("EAN");
					resultMap.put("Material", Material);
					resultMap.put("EAN", EAN);
				
				}catch(JSONException e){
					Log.e("JSON PARSING ERROR", "Error parsing data " + e.getLocalizedMessage());
				}
				
				return resultMap;
	}

}
