package com.gomobile.navigation;

/**
 * 
 * Representation of an interval excluding the endpoints ]up,down[ = (up,down) = { up < x < down }
 * Includes auto correction feature if down > up => ]down,up[
 * It is also possible to set a zeropoint (essential for the navigation/azimuth)
 * ]up,down[ and z = zeropoint => ]z,up[ and ]down,(-1)*z[
 * 
 * Throws an exception if up == down
 * @author Tim
 *
 */
class Range{
	private float up,down;
	private float zeroPoint;
	private boolean zeroPointIsSet;
	protected float center;
	
	/**
	 * Creates a new interval excluding the endpoints up and down
	 * @param up upper endpoint of the interval
	 * @param down lower endpoint of the interval
	 * @throws Exception if up = down
	 */
	protected Range (float up, float down) throws Exception{
		init(up, down);
	}
	
	/**
	 * Creates a new interval excluding the endpoints up and down with a special zeropoint
	 * Essential for the navigation/azimuth
	 * ]up,down[ and z = zeropoint => ]z,up[ and ]down,(-1)*z[
	 * @param up upper endpoint of the interval
	 * @param down lower endpoint of the interval
	 * @param zeroPoint zeropoint where the interval is split
	 * @throws Exception if up = down
	 */
	protected Range (float up, float down, float zeroPoint) throws Exception{
		this.zeroPoint = zeroPoint;
		zeroPointIsSet = true;
		
		init(up, down);
	}
	
	/**
	 * initializes the lower and upper bound of the interval
	 * also checks if the boundaries are in the right "order" and swap them if needed
	 * @param up
	 * @param down
	 * @throws Exception
	 */
	private void init(float up, float down) throws Exception{
		if(up>down){
			this.up = up;
			this.down = down;
		}else if (up<down){
			this.down = up;
			this.up = down;
		}else{
			throw new Exception("The boundary of the interval have the same value");
		}
	}
	
	/**
	 * checks if x is in the interval(s)
	 * @param x value that is checked if it is in the interval(s)
	 * @return true if in the interval(s)
	 */
	protected boolean isIn(float x){
		if( zeroPointIsSet ){
			
			if((x < zeroPoint && x > up) || (x < down && x > zeroPoint * (-1))){
					return true;
			}
			
		}else if( x > down && x < up){			
			return true;
		}
		
		return false;
	}
}