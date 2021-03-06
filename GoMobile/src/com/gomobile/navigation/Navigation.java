package com.gomobile.navigation;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.util.Log;

/**
 * This Class is responsible to measure the movement of the glass. It use two
 * main sensors ("TYPE_ACCELEROMETER" and "TYPE_MAGNETIC_FIELD"). With these two
 * sensors it is possible the measure of the glass turns to the right or left
 * side or if the glass goes up or down.
 * 
 * Five bounding boxes are created in order to be able to navigate. As soon as
 * the values are within a bounding box a counter is triggered and if the
 * bounding box in the middle is reached in due time (or before) then the
 * navigation direction is returned.
 * 
 * @author Tim and Daniel
 * 
 */

public class Navigation {

	public static final int NAVIGATE_RIGHT = 1;
	public static final int NAVIGATE_LEFT = 2;
	public static final int NAVIGATE_UP = 3;
	public static final int NAVIGATE_DOWN = 4;

	// set true if using glasses
	public boolean navigation = true;
	boolean glasses = false;
	private boolean navigateLeft, navigateRight, navigateUp, navigateDown;
	private long start, end;
	private Range turnRight, turnLeft, turnUp, turnDown, middleHorizontal,
			middleVertical;
	private BoundingBox bturnRight, bturnLeft, bturnUp, bturnDown, middle;

	// magnetic field vector
	private float[] magnet = new float[3];
	// accelerometer vector
	private float[] accel = new float[3];

	private float[] accMagOrientation = new float[3];
	private float[] rotationMatrix = new float[9];
	private float[] correctedRotationMatrix = new float[9];

	public int onSensorChanged(SensorEvent event) {
		if (navigation) {
			switch (event.sensor.getType()) {
			case Sensor.TYPE_ACCELEROMETER:

				// Special mapping for the glasses necessary
				if (glasses) {
					accel[0] = (-1f) * event.values[1];
					accel[1] = event.values[0];
					accel[2] = /* (-1f) */event.values[2];
				} else {
					accel = event.values.clone();
				}

				break;

			case Sensor.TYPE_MAGNETIC_FIELD:

				if (glasses) {
					magnet[0] = (-1f) * event.values[2];
					magnet[1] = (-1f) * event.values[1];
					magnet[2] = event.values[0];
				} else {
					magnet = event.values.clone();
				}

				break;
			}

			if (magnet != null && accel != null) {
				calculateAccMagOrientation();

				return navigate();
			}
		}
		return 0;

	}

	private void defineBoudingBoxes() {
		bturnLeft = new BoundingBox(turnLeft, middleVertical);
		bturnRight = new BoundingBox(turnRight, middleVertical);
		bturnUp = new BoundingBox(middleHorizontal, turnUp);
		bturnDown = new BoundingBox(middleHorizontal, turnDown);
		middle = new BoundingBox(middleHorizontal, middleVertical);
	}

	public void defineRanges() {
		clearNavigation();
		defineRanges((float) Math.toDegrees(accMagOrientation[0]), (float) Math.toDegrees(accMagOrientation[1]));
	}

	private void clearNavigation() {
		navigateDown = navigateLeft = navigateRight = navigateUp = false;
	}
	
	/**
	 * Calculates the ranges for the bounding boxes
	 * @param azimuth
	 * @param roll
	 */
	private void defineRanges(float azimuth, float roll) {
		int alpha, beta;
		float up, down;
		float[] init = { azimuth, roll };
		boolean flag = false; // if +- 180 is crossed

		int[][] alphaBetaValues = { { 1, -1 }, { 1, 1 }, { -1, -1 } };

		// int[][] angles = {{5,5},{60,40},{40,60}};
		int[][] angles = { { 10, 10 }, { 70, 50 }, { 50, 70 } }; // angles for
																	// the
																	// bounding
																	// boxes

		int alphaBetaLength = alphaBetaValues.length;
		for (int k = 0; k < 2; k++) {
			for (int n = 0; n < alphaBetaLength; n++) {
				flag = false;
				alpha = alphaBetaValues[n][0];
				beta = alphaBetaValues[n][1];

				up = init[k] + alpha * angles[n][0];
				down = init[k] + beta * angles[n][1];
				if (alpha * up > 180) {
					up = up - alpha * 360;
					flag = true;
				}
				if (beta * down > 180) {
					down = down - beta * 360;
					if (flag)
						flag = false;
					else
						flag = true;
				}

				Log.d("Range", "Up = " + (float) Math.toRadians(up) + " Down = " + (float) Math.toRadians(down) + "FLAG " + flag);

				try {
					Range range;
					if (flag) {
						range = new Range((float) Math.toRadians(up), (float) Math.toRadians(down), (float) Math.toRadians(180));
					} else {
						range = new Range((float) Math.toRadians(up), (float) Math.toRadians(down));
					}

					switch (alpha + beta + k) {

					case -2: // -1 + -1
						turnLeft = range;
						break;

					case -1: // -1 + -1 (+ 1)
						turnUp = range;
						break;

					case 0: // 1 + -1
						middleHorizontal = range;
						break;

					case 1: // 1 + -1 (+ 1)
						middleVertical = range;
						break;

					case 2: // 1 + 1
						turnRight = range;
						break;

					case 3: // 1 + 1 (+ 1)
						turnDown = range;
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		defineBoudingBoxes();
	}

	public void calculateAccMagOrientation() {

		if (SensorManager.getRotationMatrix(rotationMatrix, null, accel, magnet)) {
			// remapping of the coordinate system
			if (SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_X, SensorManager.AXIS_Z, correctedRotationMatrix)) {

				float calculatedAccMagOrientation[] = new float[3];
				SensorManager.getOrientation(correctedRotationMatrix, calculatedAccMagOrientation);

				// filter azimuth etc.
				for (int i = 0; i < 3; i++) {
					accMagOrientation[i] = calculateFilteredAngle(calculatedAccMagOrientation[i], accMagOrientation[i]);
				}
			}
		}

	}

	/**
	 * This method returns true when somewhere happends a navigation
	 * 
	 * @return true if the glass goes up, down, left or right
	 */

	private int navigate() {
		int direction = 0;
		boolean navigated = navigateDown || navigateLeft || navigateRight || navigateUp;
		 // check if ranges are defined
		if (bturnRight != null && bturnLeft != null && middle != null && bturnUp != null && bturnDown != null) {
			
			if (bturnRight.isIn(accMagOrientation[0], accMagOrientation[1])) {
				if (!navigated) {
					start = SystemClock.elapsedRealtime();
					navigateRight = true;
				}

			} else if (bturnLeft.isIn(accMagOrientation[0], accMagOrientation[1])) {
				if (!navigated) {
					start = SystemClock.elapsedRealtime();
					navigateLeft = true;
				}
			} else if (bturnUp.isIn(accMagOrientation[0], accMagOrientation[1])) {
				if (!navigated) {
					start = SystemClock.elapsedRealtime();
					navigateUp = true;
				}
			} else if (bturnDown.isIn(accMagOrientation[0], accMagOrientation[1])) {
				if (!navigated) {
					start = SystemClock.elapsedRealtime();
					navigateDown = true;
				}
			}
			
			//measure time
			if (middle.isIn(accMagOrientation[0], accMagOrientation[1]) && (navigated)) {
				end = SystemClock.elapsedRealtime();
				if (((end - start) / 1000d) < 1) {

					if (navigateRight) {
						direction = NAVIGATE_RIGHT;
					} else if (navigateLeft) {
						direction = NAVIGATE_LEFT;
					} else if (navigateUp) {
						direction = NAVIGATE_UP;
					} else if (navigateDown) {
						direction = NAVIGATE_DOWN;
					}
				}
				navigateRight = false;
				navigateLeft = false;
				navigateUp = false;
				navigateDown = false;
			}
		}
		return direction;
	}

	/**
	 * filters the angle so that it stays within [-180, 180[
	 * @param tmpAngle
	 * @return "correct" angle
	 */
	private float restrictAngle(float tmpAngle) {
		while (tmpAngle >= 180)
			tmpAngle -= 360;
		while (tmpAngle < -180)
			tmpAngle += 360;
		return tmpAngle;
	}

	/**
	 * 
	 * @param x raw angle value from getOrientation
	 * @param y current filtered angle value
	 * @return the new angle
	 */
	private float calculateFilteredAngle(float x, float y) {
		x = (float) Math.toDegrees(x);
		y = (float) Math.toDegrees(y);
		float alpha = 0.3f; // empirical tests for a factor
		float diff = x - y;

		diff = restrictAngle(diff);
		y += alpha * diff;
		y = restrictAngle(y);

		return (float) Math.toRadians(y);
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
