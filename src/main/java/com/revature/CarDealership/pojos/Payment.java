package com.revature.CarDealership.pojos;

import java.io.Serializable;

public class Payment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7449945184886321299L;

	private int months;
	
	private int payment;

	public Payment(int months, int payment) {
		super();
		this.months = months;
		this.payment = payment;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public int getPaymentAmount() {
		return payment;
	}

	public void setPaymentAmount(int payment) {
		this.payment = payment;
	}
	
	
	

}
