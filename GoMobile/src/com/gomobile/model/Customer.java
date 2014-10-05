package com.gomobile.model;

/**
 * This class provides constructors for the class Customer with getter and setters
 * @author Tim
 *
 */

public class Customer {

	private final int customerId;
	private String firstName, lastName;
	private double size;
	private int weight;
	
	public Customer(int id){
		this.customerId = id;
		this.firstName = null;
		this.lastName = null;
		this.size = -1.0;
		this.weight = -1;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the size
	 */
	public double getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(double size) {
		this.size = size;
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
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
}
