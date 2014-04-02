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

		
		//checks witch sensor is active
		   switch (event.sensor.getType()) {
	        case Sensor.TYPE_ACCELEROMETER:
	        	aZ_Value=(int)event.values[2];
//				magnatic.setText("nZ: "+aZ_Value);	
	             System.arraycopy(event.values, 0, accelerometerVals, 0, 3);
	             break;
	        case Sensor.TYPE_MAGNETIC_FIELD:
	              System.arraycopy(event.values, 0, magneticVals, 0, 3);
	              break;
	
	        default:
	              break;
	        }

	        if (null != magneticVals && null != accelerometerVals){
	            SensorManager.getRotationMatrix(rotationMatrix, null, accelerometerVals, magneticVals);
	            SensorManager.getOrientation(rotationMatrix, orientation);
	            azimuth = (float) Math.toDegrees(orientation[0]);
	            //this calculation gives me the Azimuth in the same format that OrientationSensor
	            //azimuth += (azimuth >= 0) ? 0 : 360;
	         
//	            float FALSE_PITCH = (float) Math.toDegrees(orientation[1]);
//	            float FALSE_ROLL = (float) Math.toDegrees(orientation[2]);
	            
//	            answer.setText("AZI: "+(int)azimuth+"\nPIT: "+(int)FALSE_PITCH+"\nROL: "+(int)FALSE_ROLL);
	        }
	        
	    if(navigated == false){
		    		
		    //checks if accelerometer value X goes down   
			if(mZ_Value+3<=aZ_Value){
	//			down.setVisibility(View.VISIBLE);
			}else{
	//			down.setVisibility(View.INVISIBLE);
			}
			//checks if accelerometer value X goes up
			if(mZ_Value-3>=aZ_Value){
	//			up.setVisibility(View.VISIBLE);
			}else{
	//			up.setVisibility(View.INVISIBLE);
			}
			//checks if the value is over 162 because on 180 the value switches to -180
			if(Z_Value>158){
				if(azimuth<(-180+(32-(180-Z_Value)))){
					navigated = true;
					return NAVIGATE_RIGHT;
				}else{
	//				right.setVisibility(View.INVISIBLE);
				}
				if(Z_Value-32>=azimuth && azimuth>0){
					navigated = true;
					return NAVIGATE_LEFT;
					
				}else{
	//				left.setVisibility(View.INVISIBLE);
				}
			}
			if(Z_Value<=158){
				if(Z_Value!=0 && Z_Value+32<=azimuth){
					navigated = true;
					return NAVIGATE_RIGHT;
					
				}else{
	//				right.setVisibility(View.INVISIBLE);
				}
			
				if(Z_Value!=0 && Z_Value-32>=azimuth){
					navigated = true;
					return NAVIGATE_LEFT;
				}else{
	//				left.setVisibility(View.INVISIBLE);
				}
			}
	    }
	  //after navigating move back to the initial angle
	    if((Z_Value+7 == (int)azimuth || Z_Value-7 == (int)azimuth) && navigated == true){
	    	 navigated = false;
	     }
		return -1;
	}
	public void setNavigationBase() {
	    	mZ_Value=aZ_Value;
	    	Z_Value=(int) azimuth;
	}
	
	//singleton
		private static Navigation instance = null;
		
		private Navigation(){}
		
		public static synchronized Navigation getInstance(){
			if (instance == null){
				instance = new Navigation();
			}
			return instance;
		}
		//singleton end
}
