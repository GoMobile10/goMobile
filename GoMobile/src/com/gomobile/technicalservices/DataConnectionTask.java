package com.gomobile.technicalservices;

import java.util.Map;
import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Misused for testing.
 * @author Anton
 *
 */
public class DataConnectionTask extends AsyncTask<String, Integer, Map<String,String>> {
    
    private TextView textView;
    private SAPConnector SAPConnector;
    
    
    @Override
    protected Map<String,String> doInBackground(String... url) {
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
