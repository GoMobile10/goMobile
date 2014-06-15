package com.gomobile.technicalservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.gomobile.data.controller.BikeDataController;
import com.gomobile.model.Bike;

public class DataConnectionTask extends AsyncTask<String, Integer, String> {
    
    private TextView textView;
    
    
    @Override
    protected String doInBackground(String[] url) {

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
	    
	    connection = (HttpURLConnection)(new URL(url[0]).openConnection());
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
//		BikeDataController bikeDataController = new BikeDataController();
//		Long ean = Long.valueOf(url[0]);
//		Bike bike = bikeDataController.getBikeByEAN(ean);
//		String bikeDescription = bike.getDescription() + "\n" + bike.getPrice() + " EUR\n" + bike.getCategory();
		
		return queryResult;
    }

    @Override
    protected void onPostExecute(String result){
    	this.textView.setText(result);
    }
    
    public void setTextView(TextView view){
    	this.textView = view;
    }
}
