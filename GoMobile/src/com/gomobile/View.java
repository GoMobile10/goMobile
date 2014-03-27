package com.gomobile;

import com.gomobile.navigation.Navigation;
import com.gomobile.scanner.model.Component;
import com.gomobile.technicalservices.BarcodeScanner;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public abstract class View extends Activity implements SensorEventListener {

	SensorManager sm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sm =(SensorManager)getSystemService(SENSOR_SERVICE);
	}
	
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
	
}
