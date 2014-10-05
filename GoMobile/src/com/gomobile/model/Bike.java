package com.gomobile.model;

/**
 * This class provides constructors for the class bike
 * @author Tim
 *
 */

public class Bike extends Material implements BikeComponentInterface {

	private String description;
	private String category;
	private int isDefect;

	
	public Bike(long eanNumber, String name, int price, String category) {
		super(eanNumber);
		this.description = name;
		this.setPrice(price);
		this.category = category;
	}

	public Bike(long eanNumber, String name, int price, String category,
			int isDefect) {
		super(eanNumber);
		this.description = name;
		this.setPrice(price);
		this.category = category;
		this.isDefect = isDefect;
	}
	
	public Bike(long eanNumber, String name) {
		super(eanNumber);
		this.description = name;

	}
	
	public Bike(long eanNumber, String name, int price, String category,
			int weight, boolean isAvailable) {
		this(eanNumber, name, price, category);
		this.setAvailable(isAvailable);
		this.setWeight(weight);
	}

	/**
	 * @return the description as String
	 */
	public String toString() {
		return description;
	}

	/**
	 * @return the description of the bike
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the category of the bike
	 */
	public String getCategory() {
		return category;
	}

}
