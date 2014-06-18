package com.gomobile.technicalservices;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gomobile.ComparisionView;
import com.gomobile.LowDetailView;
import com.gomobile.Pickuplist;
import com.gomobile.ScannerController;
import com.gomobile.data.controller.BikeDataController;
import com.gomobile.model.Bike;
import com.gomobile.navigation.ViewWithNavigation;
import com.mirasense.scanditsdk.ScanditSDKAutoAdjustingBarcodePicker;
import com.mirasense.scanditsdk.interfaces.ScanditSDK;
import com.mirasense.scanditsdk.interfaces.ScanditSDKListener;
import com.mysql.jdbc.StringUtils;


public class BarcodeScanner extends Activity implements ScanditSDKListener {
	
//	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	private ScanditSDK mPicker;
	public static final String sScanditSdkAppKey = "OGdjwH4aEeOcTdF93c7Gf4Sdj10VaESFvaMfCqnd4Uw";
	public ScanditSDKAutoAdjustingBarcodePicker picker ;
	private boolean pause = false;
	private boolean showDialog = false;
	private AlertDialog dialog;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		// Instantiate the default barcode picker
		picker = new ScanditSDKAutoAdjustingBarcodePicker(this, sScanditSdkAppKey,ScanditSDKAutoAdjustingBarcodePicker.CAMERA_FACING_BACK) ;
		// Specify the object that will receive the callback events
		picker.getOverlayView().addListener(this);
		mPicker = picker;
		 
		// Add both views to activity, with the scan GUI on top.
        setContentView(picker);
        mPicker = picker;
        
        // Register listener, in order to be notified about relevant events 
        // (e.g. a successfully scanned bar code).
        mPicker.getOverlayView().addListener(this);
        
        // Show a search bar in the scan user interface.
        // mPicker.getOverlayView().showSearchBar(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.barcode_scanner, menu);
		return true;
	} 

	/** Called when the user clicks the Send button */
	public void startScanner(View view) {
	       if(pause != false){
	    	   setContentView(picker);
	    	   onResume();
	       }else{
		        // Add both views to activity, with the scan GUI on top.
		        setContentView(picker);
		        mPicker = picker;
		        
		        // Register listener, in order to be notified about relevant events 
		        // (e.g. a successfully scanned bar code).
		        mPicker.getOverlayView().addListener(this);
		        
		        // Show a search bar in the scan user interface.
		        mPicker.getOverlayView().showSearchBar(true);
	       }
	}
	
    
	@Override
	protected void onResume() {
	    mPicker.startScanning();
	    super.onResume();
	}
	@Override
	protected void onPause() {
	    mPicker.stopScanning();
	    super.onPause();
	    pause = true;
	}
    
    public void didScanBarcode(String barcode, String symbology) {
        // Remove non-relevant characters that might be displayed as rectangles
        // on some devices. Be aware that you normally do not need to do this.
        // Only special GS1 code formats contain such characters.
        String cleanedBarcode = "";
        for (int i = 0 ; i < barcode.length(); i++) {
            if (barcode.charAt(i) > 30) {
                cleanedBarcode += barcode.charAt(i);
            }
        }
        onPause();
    
        BikeDataController bikeDataController = new BikeDataController();
        Long ean = Long.valueOf(cleanedBarcode);
        Bike bike = bikeDataController.getBikeByEAN(ean);
        System.out.println("didScanBarcode: "+ cleanedBarcode);
//        Bike bike = new Bike(1234, "Test", 499, "Bla");
		if(ScannerController.getInstance().setMaterialInUse(bike)){   	
			dialog = displayComparisionView();
			showDialog = true;
			dialog.show();
		}else{
			Intent intent = getIntent();
			System.out.println("Boolean pickupspareparts: "+intent.getBooleanExtra("pickupspareparts", false));
			if( (intent.getBooleanExtra("pickupspareparts", false)) == true){
				//If sparepart pickup ...
				System.out.println("didScanBarcode in pickup spareparts mode");
				intent = new Intent(this, Pickuplist.class);
				intent.putExtra("scannedEAN", cleanedBarcode);
				startActivity(intent);
			}else{
				System.out.println("didScanBarcode: go to low detail view!");
				intent = new Intent(this, LowDetailView.class);
				startActivity(intent);
			}
		}
    }
    
    private AlertDialog displayComparisionView(){
    	 AlertDialog.Builder builder = new AlertDialog.Builder(this);
  		// Add the buttons
  		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
  		           public void onClick(DialogInterface dialog, int id) {
  		        	   startActivity(new Intent(BarcodeScanner.this,ComparisionView.class)); 
  		           }
  		       });
  		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
  		           public void onClick(DialogInterface dialog, int id) {
  		        	   		Intent intent = new Intent(BarcodeScanner.this, LowDetailView.class);
//  		        	   		intent.putExtra("compare", false);
  		        	   		startActivity(intent);
  		           }
  		       });

  		// 2. Chain together various setter methods to set the dialog characteristics
  		builder.setMessage("Do you wanna compare it").setTitle("Compare?");
  		
  		// 3. Get the AlertDialog from create()
  		AlertDialog dialog = builder.create();
  		
  		return dialog;
    }
    
    /** 
     * Called when the user entered a bar code manually.
     * 
     * @param entry The information entered by the user.
     */
    public void didManualSearch(String entry) {
    	Toast.makeText(this, "User entered: " + entry, Toast.LENGTH_LONG).show();
    }
    
    @Override
    public void didCancel() {
    	mPicker.stopScanning();
        finish();
    }
    
    @Override
    public void onBackPressed() {
    	mPicker.stopScanning();
        finish();
    }

}
