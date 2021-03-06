package com.gomobile.technicalservices;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
* This class retrieves data from a MySQL database.
* @author Anton
*
*/
public class MySqlConnector {

	private String queryResultString;
	private final String SERVER_URL = "http://134.155.56.118/";
	
	public MySqlConnector(){
		queryResultString = "";
	}
	
	/**
	* Returns a table represented by a two-dimensional array.
	* The first dimension identifies the rows and the second one identifies the columns.
	* @param fieldNames the labels of the columns which correspond to the names of the fields of the query result.
	* @return a two dimensional array representation of the query result.
	* @throws ClassNotFoundException
	* @throws SQLException
	*/
	public String[][] queryResultToArray(String[] fieldNames) throws ClassNotFoundException, SQLException{
		int columnCount = -1;
		int rowCount = -1;
		String[][] resultArray = null;
		
		//parses the JSON data
		try{
			JSONArray jArray = new JSONArray(this.getQueryResultString());
			rowCount = jArray.length();
			columnCount = jArray.getJSONObject(0).length();
			resultArray = new String[rowCount][columnCount];
			
			for(int i = 0; i < rowCount; i++){
				JSONObject json_data = jArray.getJSONObject(i);
			
				for(int j = 0; j < fieldNames.length; j++){
					resultArray[i][j] = json_data.getString(fieldNames[j]);
				}
			}
		
		}catch(JSONException e){
			Log.e("JSON PARSING ERROR", "Error parsing JSON data: " + e.getLocalizedMessage());
			resultArray = new String[][]{};			
		}
		
		return resultArray;
	
	}
	
	/**
	* Returns the result of the HTTP post request for the specified PHP file.
	* @param phpFile the name of the PHP file including the ending .php
	* @param nameValuePairs the list of post parameters
	* @return the requested page as string
	*/
	public String getPHPRequestOutput(String phpFile, List<NameValuePair> nameValuePairs){
		String result = "";
		InputStream is = null;
		
		StringBuilder requestUrlBuilder = new StringBuilder(this.SERVER_URL);
		requestUrlBuilder.append(phpFile);
		
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(requestUrlBuilder.toString());
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}catch(Exception e){
			e.printStackTrace();
			Log.e("SERVER REQUEST ERROR", "Error in http connection " + e.getLocalizedMessage());
		}
		
		//converts the HTTP response to a string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			is.close();
			
			result = sb.toString();
			
		}catch(Exception e){
			Log.e("STREAM READING ERROR", "Error converting result " + e.getLocalizedMessage());
		}
		
		Log.i("QUERY RESULT STRING:", result);
		
		return result.replaceAll("<br />", ""); //the result might contain br-tags which must be removed.
	}
	
	
	/**
	* @return the queryString
	*/
	public String getQueryResultString() {
		return queryResultString;
	}
	
	/**
	* @param queryString the queryString to set
	*/
	public void setQueryResultString(String queryString) {
		this.queryResultString = queryString;
	}
}
