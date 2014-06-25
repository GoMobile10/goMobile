package com.gomobile.navigation;

class BoundingBox {

	private Range x,y;
	
	protected BoundingBox(Range x, Range y){
		this.x = x;
		this.y = y;
	}
	
	protected boolean isIn(float azimuth, float roll){
		return x.isIn(azimuth) && y.isIn(roll);
	}
	
}
