package com.gomobile.test;

import android.test.ActivityInstrumentationTestCase2;

import com.gomobile.ShowTechSpecs;

public class ShowTechSpecsActivityTest extends ActivityInstrumentationTestCase2<ShowTechSpecs> {
	
	 private ShowTechSpecs mActivity;
	
	public ShowTechSpecsActivityTest(){
		super(ShowTechSpecs.class);
		
	}
	
	@Override
	  protected void setUp() throws Exception {
	    super.setUp();

	    setActivityInitialTouchMode(false);

	    mActivity = getActivity();

//	    mSpinner =
//	      (Spinner) mActivity.findViewById(
//	        com.android.example.spinner.R.id.Spinner01
//	      );

	  }
	
	public void testCondition(){
		assertTrue(true);
	}
	

}
