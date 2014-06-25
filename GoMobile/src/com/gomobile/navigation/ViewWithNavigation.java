package com.gomobile.navigation;

import java.util.HashMap;

import com.gomobile.R;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public abstract class ViewWithNavigation extends Activity implements
		SensorEventListener {

	SensorManager sm;
	int counter = 0;

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
				SensorManager.SENSOR_DELAY_GAME);
		sm.registerListener(this,
				sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
				SensorManager.SENSOR_DELAY_GAME);
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
			try{
			if (counter == 0 || counter ==1){
				RelativeLayout navigationView =(RelativeLayout) findViewById(R.id.naviInfo);
				navigationView.setVisibility(RelativeLayout.INVISIBLE);
				counter = counter +1;
			}
			if (counter == 2){
				RelativeLayout navigationView =(RelativeLayout) findViewById(R.id.naviInfo);
				navigationView.setVisibility(RelativeLayout.VISIBLE);
				counter = 0;
			}
			}catch(Exception e){
				
			}
			
			
			Navigation.getInstance().navigation = false;
			
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

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

	/**
	 * 
	 * @param id
	 *            The FrameLayout id of the current Layout
	 * @param a
	 *            The current Activity
	 * @param left
	 *            Picture name of the Left Icon
	 * @param right
	 *            Picture name of the Right Icon
	 * @param up
	 *            Picture name of the Up Icon
	 * @param down
	 *            Picture name of the Down Icon
	 * @return The new FrameLayout included with navigationInfo
	 */

	public FrameLayout createNavigationInfo(int id, Activity a, String left,
			String right, String up, String down) {

		// Adds two Layouts together to create a view over another view
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.navigation_info, null);

		FrameLayout layoutMain = (FrameLayout) a.findViewById(id);
		RelativeLayout layoutNavigation = (RelativeLayout) view
				.findViewById(R.id.naviInfo);

		layoutMain.addView(layoutNavigation);

		// Hashmap for all the navigation images
		HashMap<String, Integer> images = new HashMap<String, Integer>();
		images.put("back", Integer.valueOf(com.gomobile.R.drawable.back));
		images.put("detail", Integer.valueOf(com.gomobile.R.drawable.detail));
		images.put("help", Integer.valueOf(com.gomobile.R.drawable.help));
		images.put("save", Integer.valueOf(com.gomobile.R.drawable.save));
		images.put("cancel", Integer.valueOf(com.gomobile.R.drawable.cancel));
		images.put("home", Integer.valueOf(com.gomobile.R.drawable.home));
		images.put("person", Integer.valueOf(com.gomobile.R.drawable.person));
		images.put("scan", Integer.valueOf(com.gomobile.R.drawable.scan));
		images.put("compare", Integer.valueOf(com.gomobile.R.drawable.compare));
		images.put("add", Integer.valueOf(com.gomobile.R.drawable.add));

		// init the 4 navigation images
		ImageView leftIcon = (ImageView) findViewById(R.id.pickuplistLEFT);
		ImageView rightIcon = (ImageView) findViewById(R.id.pickuplistRIGHT);
		ImageView upIcon = (ImageView) findViewById(R.id.pickuplistUP);
		ImageView downIcon = (ImageView) findViewById(R.id.pickuplistDOWN);

		// ask if one image is not necessary
		if (left != null) {
			leftIcon.setImageResource(images.get(left).intValue());
		} else {
			leftIcon.setVisibility(ImageView.INVISIBLE);
		}
		if (right != null) {
			rightIcon.setImageResource(images.get(right).intValue());
		} else {
			rightIcon.setVisibility(ImageView.INVISIBLE);
		}
		if (up != null) {
			upIcon.setImageResource(images.get(up).intValue());
		} else {
			upIcon.setVisibility(ImageView.INVISIBLE);
		}
		if (down != null) {
			downIcon.setImageResource(images.get(down).intValue());
		} else {
			downIcon.setVisibility(ImageView.INVISIBLE);
		}

		return layoutMain;
	}

	public abstract void navigateRight();

	public abstract void navigateLeft();

	public abstract void navigateUp();

	public abstract void navigateDown();

}