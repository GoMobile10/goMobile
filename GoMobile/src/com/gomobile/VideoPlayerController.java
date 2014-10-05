package com.gomobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.MediaController;
import android.widget.VideoView;
import com.gomobile.navigation.ViewWithNavigation;

/**
 * This class is responsible to show a video about the bike
 * @author Tim
 * 
 */

public class VideoPlayerController extends ViewWithNavigation {
	// datebase entry
	final int number = 0;
	VideoView myVideoView = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.video);
		myVideoView = (VideoView) findViewById(R.id.myvideoview);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) myVideoView
				.getLayoutParams();
		params.width = metrics.widthPixels;
		params.height = metrics.heightPixels;
		params.leftMargin = 0;
		myVideoView.setLayoutParams(params);

		myVideoView.setVideoURI(Uri.parse("android.resource://"
				+ getPackageName() + "/" + R.drawable.bikevid));

		myVideoView.setMediaController(new MediaController(this));
		myVideoView.requestFocus();
		myVideoView.start();

	}

	@Override
	public void navigateRight() {
		myVideoView.start();

	}

	@Override
	public void navigateLeft() {
		myVideoView.pause();

	}

	@Override
	public void navigateUp() {
		myVideoView.stopPlayback();
		startActivity(new Intent(this, DetailView.class));

	}

	@Override
	public void navigateDown() {

	}
}