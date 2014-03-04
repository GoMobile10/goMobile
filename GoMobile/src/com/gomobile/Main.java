package com.gomobile;

import com.gomobile.R;
import com.gomobile.navigation.Navigation;
import com.gomobile.technicalservices.BarcodeScanner;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Main extends ActionBarActivity implements SensorEventListener {

	SensorManager sm;
	
	@Override
	protected void onResume() {
	  super.onResume();
	  sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
	    SensorManager.SENSOR_DELAY_FASTEST);
	  sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), 
	    SensorManager.SENSOR_DELAY_FASTEST);
	}
	
	@Override
	protected void onPause() {
	  super.onPause();
	  sm.unregisterListener(this);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		sm = (SensorManager)getSystemService(SENSOR_SERVICE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void startScanner(){
		Intent intent = new Intent(this, BarcodeScanner.class);
	    startActivity(intent);		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

	    if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
	    {
	    	Navigation.getInstance().setNavigationBase();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		switch (Navigation.getInstance().onSensorChanged(event)) {
			case Navigation.NAVIGATE_RIGHT :
				startScanner();
				break;
			default : 
				break;
		}
	}

}
