package com.gomobile.technicalservices;

import com.gomobile.R;
import com.gomobile.R.layout;
import com.gomobile.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DataConnectionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_data_connection);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.data_connection, menu);
	
	return true;
    }

}
