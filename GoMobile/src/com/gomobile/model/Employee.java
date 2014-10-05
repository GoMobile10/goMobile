package com.gomobile.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an employee of the bike shop.
 * 
 * @author Arndt, Daniel
 * 
 */
public class Employee {

	private long id;
	private String firstName, lastName;
	private List<RepairOrder> assignedRepairOrders;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(long id, String firstName, String lastName) {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setAssignedRepairOrders(new ArrayList<RepairOrder>());
	}

	/**
	 * @return the ID of the employee
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the firt name of the employee
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the last name of the employee
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the list with all assigned repair orders
	 */
	public List<RepairOrder> getAssignedRepairOrders() {
		return assignedRepairOrders;
	}

	/**
	 * @param assignedRepairOrders
	 */
	public void setAssignedRepairOrders(List<RepairOrder> assignedRepairOrders) {
		this.assignedRepairOrders = assignedRepairOrders;
	}

}
