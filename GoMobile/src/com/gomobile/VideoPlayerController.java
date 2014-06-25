package com.gomobile;

import com.gomobile.data.controller.BikeDataController;
import com.gomobile.model.BikeComponentInterface;
import com.gomobile.model.Material;
import com.gomobile.navigation.ViewWithNavigation;
import com.gomobile.technicalservices.BarcodeScanner;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
 
public class VideoPlayerController extends ViewWithNavigation {
//datebase entry	
final int number= 0; 
VideoView myVideoView =null;	


 
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
	   BikeDataController controller = new BikeDataController();
	   long eanCurrent =0;
	 
       super.onCreate(savedInstanceState);
       setContentView(R.layout.video);
       myVideoView = (VideoView)findViewById(R.id.myvideoview);
       
   
//       if(controller.getVideoSourcePath(getIntent().getExtras().getLong("EanNumber")) ==2130837512){
//       }
       myVideoView.setVideoURI(Uri.parse("android.resource://" +getPackageName()+"/"+R.drawable.bikevid));
       
       myVideoView.setMediaController(new MediaController(this));
       myVideoView.requestFocus();
       myVideoView.start();
     
   }


@Override
public void navigateRight() {
myVideoView.stopPlayback();
startActivity(new Intent(this,LowDetailView.class));


	// TODO Auto-generated method stub
	
}


@Override
public void navigateLeft() {
	myVideoView.stopPlayback();
	startActivity(new Intent(this,LowDetailView.class));
	
}


@Override
public void navigateUp() {
	myVideoView.stopPlayback();
	startActivity(new Intent(this,LowDetailView.class));
	
	
}


@Override
public void navigateDown() {
	myVideoView.stopPlayback();
	startActivity(new Intent(this,LowDetailView.class));
	
	
}
}