package com.revature.CarDealership.pojos;

public class Offer {
	
	private int carVin;
	
	private String customer;
	
	private int offer;
	
	private int offerId;

	public int getCarVin() {
		return carVin;
	}

	public void setCarVin(int carVin) {
		this.carVin = carVin;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public int getOffer() {
		return offer;
	}

	public void setOffer(int offer) {
		this.offer = offer;
	}

	public Offer(int carVin, String customer, int offer, int offerId) {
		super();
		this.carVin = carVin;
		this.customer = customer;
		this.offer = offer;
		this.offerId = offerId;
	}
	
	public Offer(int carVin, String customer, int offer) {
		super();
		this.carVin = carVin;
		this.customer = customer;
		this.offer = offer;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	
	
	
	
	
	

}
