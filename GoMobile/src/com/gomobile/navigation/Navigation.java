package com.gomobile.navigation;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

public class Navigation {

	public static final int NAVIGATE_RIGHT = 1;
	public static final int NAVIGATE_LEFT = 2;
	public static final int NAVIGATE_UP = 3;
	public static final int NAVIGATE_DOWN = 4;

	Sensor accelerometer;
	Sensor magnatometer;
	SensorManager sm;
	int aZ_Value;
	int mZ_Value;
	int X_Value;
	int Y_Value;
	int Z_Value;
	float azimuth = 0;

	float accelerometerVals[] = new float[3];
	float magneticVals[] = new float[3];

	float orientationSensorVals[] = new float[3];

	float rotationMatrix[] = new float[16];
	float orientation[] = new float[3];

	boolean navigated = false;

	public int onSensorChanged(SensorEvent event) {

		// --------- MOBILE NAVIGATION VALUES ---------

		int accValue = 4;
		int azimutValue = 40; //changed from 25 to 40 for live demo TE

		// --------- GLAS NAVIGATION VALUES ---------

		// int accValue = 4;
		// int azimutValue = 25;

		// checks witch sensor is active
		switch (event.sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			aZ_Value = (int) event.values[2];
			// magnatic.setText("nZ: "+aZ_Value);
			System.arraycopy(event.values, 0, accelerometerVals, 0, 3);
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
			System.arraycopy(event.values, 0, magneticVals, 0, 3);
			break;

		default:
			break;
		}

		if (null != magneticVals && null != accelerometerVals) {
			SensorManager.getRotationMatrix(rotationMatrix, null,
					accelerometerVals, magneticVals);
			SensorManager.getOrientation(rotationMatrix, orientation);
			azimuth = (float) Math.toDegrees(orientation[0]);
			// this calculation gives me the Azimuth in the same format that
			// OrientationSensor
			azimuth += (azimuth >= 0) ? 0 : 360;

		}

		if (navigated == false) {

			// checks if accelerometer value X goes down
			if (mZ_Value + accValue <= aZ_Value) {
				navigated = true;
				return NAVIGATE_DOWN;
			}
			// checks if accelerometer value X goes up
			if (mZ_Value - accValue >= aZ_Value) {
				navigated = true;
				return NAVIGATE_UP;
			}
			// checks the values if there is a switch between 360 and 0
			if (Z_Value > 340) {
				if (azimuth < azimutValue) {
					navigated = true;
					return NAVIGATE_RIGHT;
				}
				if (Z_Value - 25 > azimuth && azimuth > 200) {
					navigated = true;
					return NAVIGATE_LEFT;
				}
			}
			// checks the values if there is a switch between 0 and 360
			if (Z_Value < 25) {
				if (azimuth > 50 && azimuth < 100) {
					navigated = true;
					return NAVIGATE_RIGHT;
				} else {
				}
				if (azimuth > 300) {
					navigated = true;
					return NAVIGATE_LEFT;
				}
			}
			// checks the normal values
			if (Z_Value >= 25 && Z_Value <= 340) {
				if (Z_Value - azimutValue > azimuth) {
					navigated = true;
					return NAVIGATE_LEFT;
				}
				if (Z_Value + azimutValue < azimuth) {
					navigated = true;
					return NAVIGATE_RIGHT;
				}
			}
		}
		// after navigating move back to the initial angle
		if ((Z_Value + 10 == (int) azimuth || Z_Value - 10 == (int) azimuth)
				&& navigated == true) {
			navigated = false;
		}
		return -1;
	}

	public void setNavigationBase() {
		navigated = false;
		mZ_Value = aZ_Value;
		Z_Value = (int) azimuth;
	}

	// singleton
	private static Navigation instance = null;

	private Navigation() {
	}

	public static synchronized Navigation getInstance() {
		if (instance == null) {
			instance = new Navigation();
		}
		return instance;
	}
	// singleton end
}
