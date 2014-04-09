package com.gomobile.technicalservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class MySqlConnector {

	private String queryResultString;
	
	public MySqlConnector(){
		queryResultString = "";
	}
	
	public String[][] queryResultToArray(String[] fieldNames) throws ClassNotFoundException, SQLException{
		int columnCount = -1;
		int rowCount = -1;
		String[][] resultArray = null;
		
		//parse json data
				try{
				        JSONArray jArray = new JSONArray(this.getQueryResultString());
				        rowCount = jArray.length();
				        columnCount = jArray.getJSONObject(0).length();
				        resultArray = new String[rowCount][columnCount];
				        
				        for(int i = 0; i < rowCount; i++){
				                JSONObject json_data = jArray.getJSONObject(i);
				                
				                for(int j = 0; j < columnCount; j++){
				                	resultArray[i][j] = json_data.getString(fieldNames[j]);
				                }
				        }
				}catch(JSONException e){
				        Log.e("log_tag", "Error parsing data "+e.toString());
				}
				
		return resultArray;
		
	}
	
	private ResultSet getQueryResult() throws ClassNotFoundException, SQLException{
		PreparedStatement statement = getConnection().prepareStatement(this.getQueryResultString());
		ResultSet resultSet = statement.executeQuery();
		
		getConnection().close();
		
		return resultSet;
	}
	
	public String getPHPRequestOutput(String phpFile, List<NameValuePair> nameValuePairs){
		String result = "";
		InputStream is = null;
		
		try{
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httpPost = new HttpPost("http://134.155.56.118/get_bike_data.php");
	        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httpPost);
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
		}catch(Exception e){
		        Log.e("log_tag", "Error in http connection "+e.toString());
		}
		
		//convert response to string
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
		        Log.e("log_tag", "Error converting result "+e.toString());
		}
		 
		System.out.println(result);
		return result;
	}
	
	private Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://134.155.56.118:3306/go_mobile_db", "GoMobileApp", "GoMobile2013");
		
		return connection;
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
