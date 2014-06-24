package com.gomobile.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an employee of the bike shop.
 * @author Anton
 *
 */
public class Employee {

	private long id;
	private String firstName, lastName;
	private List<RepairOrder> assignedRepairOrders;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	} 
	
	public Employee(long id, String firstName, String lastName){
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setAssignedRepairOrders(new ArrayList<RepairOrder>());
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public List<RepairOrder> getAssignedRepairOrders() {
		return assignedRepairOrders;
	}
	
	public void setAssignedRepairOrders(List<RepairOrder> assignedRepairOrders) {
		this.assignedRepairOrders = assignedRepairOrders;
	}
	
	
}
