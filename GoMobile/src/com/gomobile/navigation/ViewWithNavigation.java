package com.gomobile.navigation;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.KeyEvent;

public abstract class ViewWithNavigation extends Activity implements
		SensorEventListener {

	SensorManager sm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
	}

	@Override
	protected void onResume() {
		super.onResume();
		sm.registerListener(this,
				sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_FASTEST);
		sm.registerListener(this,
				sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
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

		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			Navigation.getInstance().defineRanges();
			Navigation.getInstance().navigation = true;
			return true;
		}

		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			Navigation.getInstance().navigation = false;
			
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
//	@Override
//	public boolean onKeyUp(int keyCode, KeyEvent event) {
//
//		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
//			Navigation.getInstance().navigatedActive = false;
//			
//			return true;
//		}
//		return super.onKeyDown(keyCode, event);
//	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		switch (Navigation.getInstance().onSensorChanged(event)) {
		case Navigation.NAVIGATE_RIGHT:
			this.navigateRight();
			break;
		case Navigation.NAVIGATE_LEFT:
			this.navigateLeft();
			break;
		case Navigation.NAVIGATE_UP:
			this.navigateUp();
			break;
		case Navigation.NAVIGATE_DOWN:
			this.navigateDown();
			break;
		default:
			break;
		}
	}

	public abstract void navigateRight();

	public abstract void navigateLeft();

	public abstract void navigateUp();

	public abstract void navigateDown();

}