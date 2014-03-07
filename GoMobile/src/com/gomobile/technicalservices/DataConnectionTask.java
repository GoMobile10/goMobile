package com.gomobile.technicalservices;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

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
		    return new PasswordAuthentication("A_WACHN", "GoMobile2013".toCharArray());
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
