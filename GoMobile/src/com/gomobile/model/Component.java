package com.gomobile.model;

/**
 * This class provides constructors for the class Component
 * 
 * @author Tim
 */

public class Component extends Material implements BikeComponentInterface {

	private String description;
	private boolean pickuped;
	private int averageAssemblingTime;

	public Component(long eanNumber, String description, int price, String type) {
		super(eanNumber);
		this.description = description;
		this.setPrice(price);
		this.pickuped = false;
	}

	public Component(long eanNumber, String description, int price) {
		this(eanNumber, description, price, null);
	}

	/**
	 * @return the description ot the Component
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return true if the component is pickuped
	 */
	public boolean isPicukuped() {
		return pickuped;
	}

	/**
	 * @param pickuped
	 *            status could be true or false
	 */
	public void setPicukuped(boolean pickuped) {
		this.pickuped = pickuped;
	}

	/**
	 * @return the assembling average time
	 */
	public int getAverageAssemblingTime() {
		return averageAssemblingTime;
	}

	/**
	 * @param averageAssemblingTime
	 */
	public void setAverageAssemblingTime(int averageAssemblingTime) {
		this.averageAssemblingTime = averageAssemblingTime;
	}

}
