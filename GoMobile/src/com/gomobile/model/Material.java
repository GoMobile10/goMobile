package com.gomobile.model;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class Material {

	private final long eanNumber;
	//TODO never calculate prices with double/float -> use BigDecimal or int
	private double price;
	private int weight;
	private boolean available;
	private String matType;
	
	private Deliverer deliverer;
	private List<Material> compatibleSubComponentList; //List of parts and subcomponents which are compatible with a specific bike
	
	public Material(long eanNumber){
		this.eanNumber = eanNumber;
		this.available = false;
		this.matType = null;
		this.price = -1.0;
		this.weight = -1;
		this.deliverer = null;
		this.setCompatibleSubComponentList(new ArrayList<Material>());
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	/**
	 * @return the matType
	 */
	public String getMatType() {
		return matType;
	}

	/**
	 * @param matType the matType to set
	 */
	public void setMatType(String matType) {
		this.matType = matType;
	}

	/**
	 * @return the eanNumber
	 */
	public long getEanNumber() {
		return eanNumber;
	}

	/**
	 * @return the deliverer
	 */
	public Deliverer getDeliverer() {
		return deliverer;
	}

	/**
	 * @param deliverer the deliverer to set
	 */
	public void setDeliverer(Deliverer deliverer) {
		this.deliverer = deliverer;
	}

	/**
	 * @return the compatibleSubComponentList
	 */
	public List<Material> getCompatibleSubComponentList() {
		return compatibleSubComponentList;
	}

	/**
	 * @param compatibleSubComponentList the compatibleSubComponentList to set
	 */
	public void setCompatibleSubComponentList(
			List<Material> compatibleSubComponentList) {
		this.compatibleSubComponentList = compatibleSubComponentList;
	}
	
	
}
