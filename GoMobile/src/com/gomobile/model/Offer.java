package com.gomobile.model;

/**
 * This class provides constructors for the class Offer
 * @author Patrick
 *
 */

import java.sql.Date;

public class Offer {

	private final int offerId;
	private Date startDate, endDate;
	
	public Offer(int id){
		this.offerId = id;
		this.startDate = null;
		this.endDate = null;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the offerId
	 */
	public int getOfferId() {
		return offerId;
	}
}
