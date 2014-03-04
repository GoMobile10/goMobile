package com.gomobile;

import android.annotation.TargetApi;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.gomobile.navigation.Navigation;
import com.gomobile.scanner.model.Component;
import com.gomobile.scanner.model.Part;
import com.gomobile.technicalservices.BarcodeScanner;

public class LowDetailView extends ActionBarActivity implements SensorEventListener  {

	SensorManager sm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
	    String message =  intent.getStringExtra("Test");
		setContentView(R.layout.activity_low_detail_view);
		//navigation
		sm =(SensorManager)getSystemService(SENSOR_SERVICE);
		
		this.display(ScannerController.getInstance().getComponentInUse());
		// Show the Up button in the action bar.
		setupActionBar();
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

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.low_detail_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				// This ID represents the Home or Up button. In the case of this
				// activity, the Up button is shown. Use NavUtils to allow users
				// to navigate up one level in the application structure. For
				// more details, see the Navigation pattern on Android Design:
				//
				// http://developer.android.com/design/patterns/navigation.html#up-vs-back
				//
				NavUtils.navigateUpFromSameTask(this);
				return true;
			case R.id.action_scanner:
				startActivity(new Intent(this, BarcodeScanner.class));
			    return true;
			case R.id.action_detail_view:
				startActivity(new Intent(this, DetailView.class));
			    return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void display(Component comp){
		TextView textView = (TextView) findViewById(R.id.textViewName);
		textView.setText(comp.getName());
		textView = (TextView) findViewById(R.id.textViewPrice);
		textView.setText(String.valueOf(comp.getPrice()));
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
				startActivity(new Intent(this, DetailView.class));
				break;

			case Navigation.NAVIGATE_LEFT :
				startActivity(new Intent(this, BarcodeScanner.class));
				break;
			default : 
				break;
		}
	}

}
