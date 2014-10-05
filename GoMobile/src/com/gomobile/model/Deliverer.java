package com.gomobile.model;

/**
 * This class provides constructors for the class Deliverer with getter and setters
 * @author Tim
 *
 */

public class Deliverer {

	private final int delivererId;
	private String companyName, adress, eMail;
	private int phoneNumber;
	
	public Deliverer(int id){
		this.delivererId = id;
		this.companyName = null;
		this.adress = null;
		this.eMail = null;
		this.phoneNumber = -1000000;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * @param adress the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * @return the eMail
	 */
	public String geteMail() {
		return eMail;
	}

	/**
	 * @param eMail the eMail to set
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	/**
	 * @return the phoneNumber
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the delivererId
	 */
	public int getDelivererId() {
		return delivererId;
	}
}
