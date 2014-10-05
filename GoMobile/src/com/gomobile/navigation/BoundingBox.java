package com.gomobile.navigation;

/**
 * This class defines an area (a kind of box) for the navigation. It shows if the user moves in this area or if the user left the area
 * @author Tim
 *
 */

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
