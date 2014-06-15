package com.gomobile.technicalservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.gomobile.data.controller.BikeDataController;
import com.gomobile.model.Bike;

public class DataConnectionTask extends AsyncTask<String, Integer, Map<String,String>> {
    
    private TextView textView;
    private SAPConnector SAPConnector;
    
    
    @Override
    protected Map<String,String> doInBackground(String[] url) {
    	SAPConnector = new SAPConnector();
    	return SAPConnector.getJSONDATA();

	    }

    @Override
    protected void onPostExecute(Map<String,String> result){
    	String dataString = "";
    	dataString = dataString+result.get("Material")+"\n"+result.get("EAN");
    	this.textView.setText(dataString);
    }
    
    public void setTextView(TextView view){
    	this.textView = view;
    }
}
