package com.gomobile.model;


import java.util.Calendar;
import java.util.Map;

/**
 * @author Anton & Anna & Ahsan
 */
public class RepairOrder {

	private int orderID, employeeID;
	private Bike defectBike;
	private float cost;
	private String repairDescription;
	private Calendar deliveryDateCalendar;
	private Map <Component,Component> defectReplacementComponentMap;
	
	/**
	 * @return the orderID
	 */
	public int getOrderID() {
		return orderID;
	}
	/**
	 * @param orderID the orderID to set
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	/**
	 * @return the employeeID
	 */
	public int getEmployeeID() {
		return employeeID;
	}
	/**
	 * @param employeeID the employeeID to set
	 */
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	/**
	 * @return the cost
	 */
	public float getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(float cost) {
		this.cost = cost;
	}
	/**
	 * @return the repairDescription
	 */
	public String getRepairDescription() {
		return repairDescription;
	}
	/**
	 * @param repairDescription the repairDescription to set
	 */
	public void setRepairDescription(String repairDescription) {
		this.repairDescription = repairDescription;
	}
	/**
	 * @return the deliveryDateCalendar
	 */
	public Calendar getDeliveryDateCalendar() {
		return deliveryDateCalendar;
	}
	/**
	 * @param deliveryDateCalendar the deliveryDateCalendar to set
	 */
	public void setDeliveryDateCalendar(Calendar deliveryDateCalendar) {
		this.deliveryDateCalendar = deliveryDateCalendar;
	}
	/**
	 * @return the defectBike
	 */
	public Bike getDefectBike() {
		return defectBike;
	}
	/**
	 * @param defectBike the defectBike to set
	 */
	public void setDefectBike(Bike defectBike) {
		this.defectBike = defectBike;
	}
	/**
	 * @return the defectReplacementComponentMap
	 */
	public Map <Component,Component> getDefectReplacementComponentMap() {
		return defectReplacementComponentMap;
	}
	/**
	 * @param defectReplacementComponentMap the defectReplacementComponentMap to set
	 */
	public void setDefectReplacementComponentMap(
			Map <Component,Component> defectReplacementComponentMap) {
		this.defectReplacementComponentMap = defectReplacementComponentMap;
	}
}
